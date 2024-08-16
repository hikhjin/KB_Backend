package com.multi.data.controller.ex02;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/joinMember.do")
public class JoinMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("JoinMemberServlet - doGet 요청");
		
		// 서블릿 요청 처리하는 패턴
		// 1. encoding 셋팅하기
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// 2. 파라메터 처리하기 - getParameter, getParameterValues
		Member member = new Member();
		try {
			member.setId(req.getParameter("id"));
			member.setPwd(req.getParameter("pwd"));
			member.setName(req.getParameter("name"));
			member.setAge(Integer.parseInt(req.getParameter("age")));
			member.setAddress(req.getParameter("address"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error!");
			resp.getWriter().print("<h1>Error!</h1>");
			return;
		}
		System.out.println(member);
		
		// 3. 응답페이지 type 셋팅하기
		resp.setContentType("text/html;charset=utf-8"); // 웹브라우저에 표시할 형식을 알려주는 파라메터
		
		// 4. 페이지 만들어서 응답하기
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		
		String msg = (String)req.getAttribute("MSG");
		if(msg != null) {
			sb.append("<h2>이전 페이지에서 메세지가 전달됨!</h2>");
			sb.append("<h2>msg : " + msg + "</h2>");
			Date date = (Date) req.getAttribute("MSG_DATE");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dateStr = sdf.format(date);
			sb.append("<h2>date : " + dateStr + "</h2>");
			List list = (List) req.getAttribute("MSG_LIST");
			sb.append("<h2>list : " + list + "</h2>");
		}
		
		sb.append("<h1>회원가입 결과</h1>");
		sb.append(String.format("<h3>아이디 : %s<h3>", member.getId()));
		sb.append(String.format("<h3>비밀번호 : %s<h3>", member.getPwd()));
		sb.append(String.format("<h3>이름 : %s<h3>", member.getName()));
		sb.append(String.format("<h3>나이 : %d<h3>", member.getAge()));
		sb.append(String.format("<h3>주소 : %s<h3>", member.getAddress()));
		sb.append("</body>");
		sb.append("</html>");
		resp.getWriter().append(sb.toString());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}











