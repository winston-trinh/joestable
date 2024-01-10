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

@WebServlet("/checkFavorite")
public class FavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String restaurant_id = request.getParameter("restaurant-id");
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
            boolean hasFavorite = JDBCConnector.isUserFavorite(email, restaurant_id);
            
            if (hasFavorite) {
            	response.getWriter().write("Send restaurant data back here.");
            } else {
            	response.getWriter().write("No favorites found.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle other exceptions
            e.printStackTrace();
            response.getWriter().write("Internal server error occurred");
        }
        
    }
}
