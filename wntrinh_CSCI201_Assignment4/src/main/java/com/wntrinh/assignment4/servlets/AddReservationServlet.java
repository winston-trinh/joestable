package com.wntrinh.assignment4.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wntrinh.assignment4.JDBCConnector.JDBCConnector;

@WebServlet("/addReservation")
public class AddReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String restaurant_id = request.getParameter("restaurant-id");
    	String reservation_date = request.getParameter("reservationDate");
    	String reservation_time = request.getParameter("reservationTime");
    	String reservation_notes = request.getParameter("reservationNotes");
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
        	System.out.println(restaurant_id + " " + reservation_time + " "+ reservation_time + " " + reservation_notes);
            JDBCConnector.addReservation(email, restaurant_id, reservation_date, reservation_time, reservation_notes);

        } catch (SQLException | ClassNotFoundException e) {
            // Handle other exceptions
            e.printStackTrace();
            response.getWriter().write("Error occurred when adding reservation into database.");
        }
        
    }
}
