package com.Marissa.AjaxDemo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AjaxServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        int num3 = Integer.valueOf(num1) + Integer.valueOf(num2);

        PrintWriter out = response.getWriter();

        System.out.println("AjaxServlet.doGet()");

        out.println(num3);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("AjaxServlet.doPost()");

        this.doGet(request, response);
    }
}