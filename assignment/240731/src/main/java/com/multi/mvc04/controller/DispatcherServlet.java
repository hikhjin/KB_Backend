package com.multi.mvc04.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private ProductController memberController = new ProductController();
    private CustomerController boardController = new CustomerController();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/web04/product_insert")) {
            memberController.insert(request, response);
        } else if (uri.equals("/web04/product_delete")) {
            memberController.delete(request, response);
        } else if (uri.equals("/web04/customer_insert")) {
            boardController.insert(request, response);
        } else if (uri.equals("/web04/customer_delete")) {
            boardController.delete(request, response);
        }
    }
}
