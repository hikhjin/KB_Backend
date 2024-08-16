package com.multi.data.controller.ex01;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// .do가 붙으면 Controller(Servlet, Spring)계열에서 처리하는 요청을 명시적으로 나타낼때 활용
@WebServlet("/login.do")
public class GetPostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 파라미터 가져오는 방법
        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        System.out.println("id: " + id + " pwd: " + pwd);

        // 응답페이지 만들기
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h3>");
        out.printf("사용자 ID : %s <br>", id);
        out.printf("사용자 PW : %s <br>", pwd);
        out.println("</h3>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost 호출");
        req.setCharacterEncoding("UTF-8"); // post에서는 반드시 인코딩 처리가 필요하다!!
        doGet(req, resp);
    }
}
