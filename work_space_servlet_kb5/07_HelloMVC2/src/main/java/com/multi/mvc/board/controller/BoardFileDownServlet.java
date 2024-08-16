package com.multi.mvc.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board/fileDown")
public class BoardFileDownServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파일을 읽어서 전송하는 기능을 구현, 돌려주는 응답이 페이지가 아닌 파일스트림 형태가 된다!
		
		// 1. 파라메터 읽어오기
		String originName = req.getParameter("originName");
		String reName = req.getParameter("reName");
		System.out.println(originName + ", " + reName);
		
		// 2. 파일 경로 찾아서 오기
		String path = getServletContext().getRealPath("/resources/upload/board");
		File downFile = new File(path, reName); // 20221201_151033_21_223.jpg
		
		// 3. 파일 스트림 생성
		FileInputStream fis = new FileInputStream(downFile);
		BufferedInputStream bis = new BufferedInputStream(fis); // 읽을꺼
		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream()); // Client 보낼꺼
		
		// 4. http 헤서 셋팅, file 전송용 헤더 만들기, 파일 이름 인코딩
		String header = req.getHeader("user-agent");
    	boolean isMSIE = header.contains("MSIE") || header.contains("Trident");
    	
    	String downName = null;
    	// 브라우저에 따른 인코딩 방식 처리
    	if (isMSIE) {
    		downName = URLEncoder.encode(originName, "UTF-8").replaceAll("\\+", "%20");
    	} else {    		
    		downName = new String(originName.getBytes("UTF-8"), "ISO-8859-1");
    	}
    	
    	// octec-stream은 문자열 처리가 아닌 binary 파일 전송을 뜻함
    	resp.setContentType("application/octec-stream");
    	// header는 파일 첨부를 뜻하여 다운로드 창이 띄어지도록 변경됨
    	resp.setHeader("Content-Disposition", "attachment;filename=" + downName);
    	
    	// 5. 파일 전송부
    	int read = 0;
    	while((read = bis.read()) != -1) {
    		bos.write(read);
    	}
    	
    	bos.close();
    	bis.close();
    	fis.close();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
