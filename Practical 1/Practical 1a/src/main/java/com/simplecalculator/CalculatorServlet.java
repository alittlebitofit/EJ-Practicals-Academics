package com.simplecalculator;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */

//@WebServlet("CalculatorServlet")
public class CalculatorServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		try
		{
			double num1 = Double.parseDouble(req.getParameter("num1"));
			double num2 = Double.parseDouble(req.getParameter("num2"));
			
			String operation = req.getParameter("operation");
			
			double result = 0;
			
			switch(operation)
			{
				case "add":
					result = num1 + num2;
					break;
				case "subtract":
					result = num1 - num2;
					break;
				case "multiply":
					result = num1 * num2;
					break;
				case "divide":
					if(num2 != 0)
						result = num1 / num2;
					else {
						out.println("<h3>Cannot divide by zero</h3>");
						return;
					}
					break;
			}
			
			out.println("<h3>Result: " + result + "</h3>");
		}
		catch(NumberFormatException e)
		{
			out.println("<h3>Invalid input</h3>");
		}
		
	}

}
