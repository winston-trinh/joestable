package com.wntrinh.assignment4.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.wntrinh.assignment4.JDBCConnector.JDBCConnector;
import com.wntrinh.assignment4.reservation.Reservation;
import com.wntrinh.assignment4.reservationInfo.ReservationInfo;
import com.wntrinh.assignment4.restaurantSQL.RestaurantSQL;

@WebServlet("/getReservations")
public class GetReservationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //@Override
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
        	List<Reservation> reservations = JDBCConnector.getReservationsByEmail(email);
        	
        	ArrayList<RestaurantSQL> restaurants = new ArrayList<RestaurantSQL>();
        	for (int i = 0; i < reservations.size(); ++i) {
        		RestaurantSQL temp = JDBCConnector.getReservationRestaurants(reservations.get(i).getRestaurantId(), reservations.get(i).getReservationId());
        		restaurants.add(temp);
        	}
        	
        	ArrayList<ReservationInfo> reservationInfo = new ArrayList<>();
        	
        	for (int i = 0; i < reservations.size(); ++i) {
        		RestaurantSQL restaurant = restaurants.get(i);
        		Reservation reservation = reservations.get(i);
        		ReservationInfo reservedRestaurant = new ReservationInfo(reservation.getReservationId(), restaurant.getName(), reservation.getRestaurantId(), reservation.getDate(), reservation.getTime(), reservation.getReservationNotes(),
        	                    										restaurant.getAddress(), restaurant.getPhone(), restaurant.getCuisine(), restaurant.getPrice(), restaurant.getRating(), restaurant.getUrl(), restaurant.getImage_url());
        		reservationInfo.add(reservedRestaurant);
        	}
        	        	

            Gson gson = new Gson();
            String jsonData = gson.toJson(reservationInfo);

            // Set the content type and write the JSON data to the response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonData);
            
        } catch (SQLException | ClassNotFoundException e) {
            // Handle SQL and ClassNotFound exceptions
            e.printStackTrace();
            response.getWriter().write("Internal server error occurred");
        }
    }
}
