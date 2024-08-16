package com.multi.data.controller.ex02;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * SendRedirect란? 
 * - Client에게 특정 페이지 url을 다시 요청하라는 명령으로 응답 받은 페이지가 다시 요청됨
 * - 서버 입장에서는 새로운 페이지 요청으로 사용자 파라메터와 Attribute가 저장되지 않음. 
 * - 다음 페이지 호출은 get방식으로 호출됨!
 */

@WebServlet("/sendredirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("SendRedirectServlet - doPost");
		req.setAttribute("MSG", "SendRedirectServlet의 메세지입니다."); // 전달 안될 예정!
		req.setAttribute("MSG_DATE", new Date());
		req.setAttribute("MSG_LIST", new ArrayList<>(Arrays.asList(1,2,3,4,5)));
		
		// resp 다시 요청할 페이지를 적어 전달한다. -> 응답메세지로 전달됨
		resp.sendRedirect("joinMember.do"); // joinMember.do를 다시 페이지로 요청하라는 명령
		// 301번 코드로 클라이언트로 전달됨
	}

}


















