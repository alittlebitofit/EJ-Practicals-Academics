package com.requestdispatcher;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.RequestDispatcher;


//@WebServlet("/checkPassword")
public class RequestDispatcherServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String password = req.getParameter("pass");
		RequestDispatcher rd;
		
		if(password.equals("Servlet")) {
			rd = req.getRequestDispatcher("Welcome");
			rd.forward(req, resp);
			
		} else {
			out.println("<h1 style='color:red;'>Password incorrect</h1>");
			rd = req.getRequestDispatcher("index.html");
			rd.include(req, resp);
		}
		
	}
	
	
}
