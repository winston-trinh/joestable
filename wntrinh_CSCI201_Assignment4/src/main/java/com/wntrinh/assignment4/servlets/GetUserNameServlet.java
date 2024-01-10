package com.wntrinh.assignment4.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wntrinh.assignment4.JDBCConnector.JDBCConnector;

@WebServlet("/getUserName")
public class GetUserNameServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

	    	String email = "";
	        
	        // Check for the "email" cookie
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("email".equals(cookie.getName())) {
	                    // The "email" cookie exists
	                    email = cookie.getValue();
	                    break;
	                }
	            }
	        }
	        
	        try {
	        	String username = JDBCConnector.getUserName(email);
	            
	            if (username != null) {
	            	response.getWriter().write(username);
	            } else {
	            	response.getWriter().write("Invalid username or password!");
	            }
	        } catch (SQLIntegrityConstraintViolationException e) {
	            // Duplicate entry error
	            response.getWriter().write("Username or email already exists.");
	        } catch (SQLException | ClassNotFoundException e) {
	            // Handle other exceptions
	            e.printStackTrace();
	            response.getWriter().write("Internal server error occurred");
	        }
	    }
}
