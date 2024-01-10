package com.wntrinh.assignment4.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.wntrinh.assignment4.JDBCConnector.JDBCConnector;
import com.wntrinh.assignment4.restaurantSQL.RestaurantSQL;

@WebServlet("/getFavorites")
public class GetFavoritesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve form parameters
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
        	List<RestaurantSQL> data = JDBCConnector.getFavoriteRestaurantsByEmail(email);
        	
        	// Convert the data to a JSON representation
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);

            // Set the content type and write the JSON data to the response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonData);
        	
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry error
            response.getWriter().write("Error retrieving favorites");
        } catch (SQLException | ClassNotFoundException e) {
            // Handle other exceptions
            e.printStackTrace();
            response.getWriter().write("Internal server error occurred");
        }
    }
}
