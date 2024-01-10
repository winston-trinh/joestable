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

@WebServlet("/processSignUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*// If we want to return a response with the form data in json format
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");*/

        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        try {
            JDBCConnector.insertNewUser(username, password, email);
            
            String contextPath = request.getContextPath();
            String redirectURL = contextPath + "/pages/home.html";
            
            Cookie userCookie = new Cookie("email", email);

            userCookie.setMaxAge(3600); // Expires in 1 hour
            userCookie.setPath("/");

            response.addCookie(userCookie);            
            response.sendRedirect(redirectURL);
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry error
            response.getWriter().write("Username or email already exists.");
        } catch (SQLException | ClassNotFoundException e) {
            // Handle other exceptions
            e.printStackTrace();
            response.getWriter().write("Internal server error occurred");
        }

        // Construct JSON string
        String json = "{"
            + "\"Email\":\"" + email + "\","
            + "\"Username\":\"" + username + "\","
            + "\"Password\":\"" + password + "\""
            + "}";
        
        /*// Write JSON to response
        response.getWriter().write(json);*/
    }
}
