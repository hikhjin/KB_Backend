package com.multi.mvc04.controller;

import com.multi.mvc04.dao.CustomerDAO;
import com.multi.mvc04.dao.ProductDAO;
import com.multi.mvc04.vo.CustomerVO;
import com.multi.mvc04.vo.ProductVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomerController {
    private CustomerDAO customerDAO = new CustomerDAO();

    public void insert(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            CustomerVO customer = new CustomerVO();
            customer.setId(request.getParameter("id"));
            customer.setName(request.getParameter("name"));
            customer.setEmail(request.getParameter("email"));
            customerDAO.insertCustomer(customer);
            request.setAttribute("customer", customer);
            RequestDispatcher rd = request.getRequestDispatcher("views/customer_result.jsp");
            rd.forward(request, response);
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String id = request.getParameter("id");
            customerDAO.deleteCustomer(id);
            request.setAttribute("customer_id", id);
            RequestDispatcher rd = request.getRequestDispatcher("views/customer_result.jsp");
            rd.forward(request, response);
        }
    }