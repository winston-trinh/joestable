package com.wntrinh.assignment4.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.wntrinh.assignment4.YelpAPI.YelpAPI;
import com.wntrinh.assignment4.restaurantlist.RestaurantList;

@WebServlet("/processSearch")
public class RestaurantSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // If we want to return a response with the form data in json format
        response.setContentType("application/xml");
        response.setCharacterEncoding("UTF-8");

        // Retrieve form parameters
        String restaurantName = request.getParameter("restaurant-name");
        String searchOption = request.getParameter("search-option");
        String latitudeStr = request.getParameter("latitude");
        String longitudeStr = request.getParameter("longitude");
        
        Double latitude;
        Double longitude;
        try {
        	latitude = Double.valueOf(latitudeStr);
        	longitude = Double.valueOf(longitudeStr);
        	YelpAPI yelpAPI = new YelpAPI();
            String data = yelpAPI.makeAPICall(restaurantName, latitude, longitude, searchOption);
            // Parse the JSON data using Gson
            RestaurantList restaurantList = parseData(data);
            // System.out.println(data);
            
            /*for (int i = 0; i < restaurantList.getRestaurants().size(); ++i) {
            	System.out.println("\nRestaurant data:");
                System.out.println("name: " + restaurantList.restaurantAt(i).getName());
                System.out.println("display_phone: " + restaurantList.restaurantAt(i).getDisplay_phone());
                System.out.println("price: " + restaurantList.restaurantAt(i).getPrice());
                System.out.println("rating: " + restaurantList.restaurantAt(i).getRating());
                System.out.println("address: " + restaurantList.restaurantAt(i).getDisplay_address());
                System.out.println("cuisine: " + restaurantList.restaurantAt(i).getCuisine().get(0).getTitle());
                
                // Strip url of any extra characters
                for (int j = 0; j < restaurantList.restaurantAt(i).getUrl().length(); ++j) {
                	if (restaurantList.restaurantAt(i).getUrl().charAt(j) == '?') {
                		String newUrl = restaurantList.restaurantAt(i).getUrl().substring(0, j);
                		restaurantList.restaurantAt(i).setUrl(newUrl);
                		break;
                	}
                }
                System.out.println("url: " + restaurantList.restaurantAt(i).getUrl());
                System.out.println("image_url: " + restaurantList.restaurantAt(i).getImage_url());
            }*/
            
            // Serialize the restaurantList object to XML format
            // String xmlData = serializeToXml(restaurantList);

            // Write the XML data to the response
            // response.getWriter().write(xmlData);
            response.getWriter().write(data);
            // response.getWriter().write(data);
        } catch (NumberFormatException err) {
            System.err.println("Invalid number format");
        }

        // Construct JSON string
        String json = "{"
            + "\"Restaurant Name\":\"" + restaurantName + "\","
            + "\"Search Option\":\"" + searchOption + "\","
            + "\"Latitude\":\"" + latitudeStr + "\","
            + "\"Longitude\":\"" + longitudeStr + "\""
            + "}";
        
        // Make a path to reroute user after submitting form
        String contextPath = request.getContextPath();
        String redirectURL = contextPath + "/pages/home.html"; // CHANGE THIS LATER

        // Route to file
        // response.sendRedirect(redirectURL);
    }
    
    protected String serializeToXml(RestaurantList restaurantList) {

        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<RestaurantList>");
        for (int i = 0; i < restaurantList.getRestaurants().size(); ++i) {
            xmlBuilder.append("<Restaurant>");
            xmlBuilder.append("<Name>").append(restaurantList.restaurantAt(i).getName()).append("</Name>");
            xmlBuilder.append("<DisplayPhone>").append(restaurantList.restaurantAt(i).getDisplay_phone()).append("</DisplayPhone>");
            xmlBuilder.append("<Price>").append(restaurantList.restaurantAt(i).getPrice()).append("</Price>");
            xmlBuilder.append("<Rating>").append(restaurantList.restaurantAt(i).getRating()).append("</Rating>");
            xmlBuilder.append("<Address>").append(restaurantList.restaurantAt(i).getDisplay_address()).append("</Address>");
            xmlBuilder.append("<Cuisine>").append(restaurantList.restaurantAt(i).getCuisine().get(0).getTitle()).append("</Cuisine>");
            xmlBuilder.append("<Url>").append(restaurantList.restaurantAt(i).getUrl()).append("</Url>");
            xmlBuilder.append("<ImageUrl>").append(restaurantList.restaurantAt(i).getImage_url()).append("</ImageUrl>");
            xmlBuilder.append("</Restaurant>");
        }
        xmlBuilder.append("</RestaurantList>");
        
        return xmlBuilder.toString();
    }
    
    protected RestaurantList parseData(String data) {
    	Gson gson = new Gson();
		RestaurantList restaurantList = gson.fromJson(data.toString(), RestaurantList.class);
		return restaurantList;
    }
}
