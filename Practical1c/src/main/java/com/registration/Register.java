package com.registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String country = req.getParameter("country");

		if (!username.equals("") && !password.equals("") && !email.equals("") && !country.equals("")) {

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
				DatabaseMetaData dbmd = con.getMetaData();
				ResultSet rs = dbmd.getCatalogs();

				boolean dbFound = false;

				while (rs.next()) {
					String dbName = rs.getString(1);
					if (dbName.equals("practicals_db")) {
						dbFound = true;
						break;
					}
				}
				PreparedStatement ps;

				if (!dbFound) {
					ps = con.prepareStatement("CREATE DATABASE practicals_db;");
					ps.executeUpdate();
				}
				
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practicals_db", "root", "root");

				if (!dbmd.getTables(null, null, "userdata", null).isBeforeFirst()) {
					ps = con.prepareStatement("CREATE TABLE userdata(username VARCHAR(100) PRIMARY KEY, password VARCHAR(100), email VARCHAR(100), country VARCHAR(100));");
					ps.executeUpdate();
				}


				ps = con.prepareStatement("INSERT INTO userdata VALUES(?,?,?,?);");

				ps.setString(1, username);
				ps.setString(2, password);
				ps.setString(3, email);
				ps.setString(4, country);

				int row = ps.executeUpdate();

				out.println("<h1>" + row + " inserted successfully.</h1>");
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		}

	}

}
