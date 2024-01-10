package com.wntrinh.assignment4.restaurant;

import com.wntrinh.assignment4.restaurant.categories.Category;
import com.wntrinh.assignment4.restaurant.location.Location;

import java.util.ArrayList;

public class Restaurant {
    // Data members for the model
    private String name;
    private Location location;
    private String display_phone;
    private ArrayList<Category> categories;
    private String price;
    private String image_url;
    private String url;
    private double rating;
    private boolean isFavorite;

    // Constructors
    public Restaurant(String name, Location location, String display_phone, ArrayList<Category> categories, double rating, String price) {
        this.name = name;
        this.location = location;
        this.display_phone = display_phone;
        this.categories = categories;
        this.rating = rating;
        this.price = price;
    }

    // Default constructor
    public Restaurant() {
        this.name = "";
        this.location = new Location();
        this.display_phone = "";
        this.categories = new ArrayList<Category>();
        this.rating = 0.0;
        this.price = "";
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplay_address() {
    	StringBuilder address = new StringBuilder();
        
        for (int i = 0; i < location.getAddress().length; ++i) {
            
            // If not the last element, add a comma and space
            if (i == location.getAddress().length - 1) {
                address.append(location.getAddress()[i]);
            } else {
            	address.append(location.getAddress()[i] + " ");
            }
        }

        return address.toString();
    }

    public String getDisplay_phone() {
        return display_phone;
    }

    public void setDisplay_phone(String display_phone) {
        this.display_phone = display_phone;
    }

    public ArrayList<Category> getCuisine() {
        return categories;
    }

    public void setCuisine(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
