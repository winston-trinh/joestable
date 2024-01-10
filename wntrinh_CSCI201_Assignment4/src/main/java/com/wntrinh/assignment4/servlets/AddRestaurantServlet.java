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

@WebServlet("/addRestaurant")
public class AddRestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve form parameters
        String restaurant_id = request.getParameter("restaurant-id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String cuisine = request.getParameter("cuisine");
        String price = request.getParameter("price");
        String ratingStr = request.getParameter("rating");
        double rating = Double.parseDouble(ratingStr);
        String url = request.getParameter("url");
        String imageUrl = request.getParameter("imageUrl");
        
        try {
            boolean restaurantExists = JDBCConnector.checkRestaurant(restaurant_id);
            
            if (!restaurantExists) {
            	JDBCConnector.insertNewRestaurant(restaurant_id, name, address, phone, cuisine, price, rating, url, imageUrl);
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
