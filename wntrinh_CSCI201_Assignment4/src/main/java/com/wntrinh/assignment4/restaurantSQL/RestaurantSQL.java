package com.wntrinh.assignment4.restaurantSQL;


public class RestaurantSQL {
	// Data members for the model
	private String restaurant_id;
    private String name;
    private String address;
    private String phone;
    private String cuisine;
    private String price;
    private String image_url;
    private String url;
    private double rating;
    private int fav_id;

    // Constructors
    public RestaurantSQL(String restaurant_id, String name, String address, String phone, String cuisine, double rating, String price, String url, String image_url, int fav_id) {
    	this.restaurant_id = restaurant_id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.cuisine = cuisine;
        this.rating = rating;
        this.price = price;
        this.image_url = image_url;
        this.url = url;
        this.fav_id = fav_id;
    }

    // Default constructor
    public RestaurantSQL() {
    	this.restaurant_id = "";
        this.name = "";
        this.address = "";
        this.phone = "";
        this.cuisine = "";
        this.rating = 0.0;
        this.price = "";
        this.image_url = "";
        this.url = "";
        this.fav_id = 0;
    }

    // Getters and setters
    public String getID() {
        return restaurant_id;
    }

    public void setID(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
    	return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setDisplay_phone(String phone) {
        this.phone = phone;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
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
    
    public int getFavID() {
        return fav_id;
    }

}
