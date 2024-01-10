package com.wntrinh.assignment4.restaurantlist;

import com.wntrinh.assignment4.restaurant.Restaurant;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;


public class RestaurantList {
    @SerializedName("businesses")
    private ArrayList<Restaurant> restaurants;
    
    public RestaurantList() {
        this.restaurants = new ArrayList<Restaurant>();
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
    
    public void addRestaurant(Restaurant restaurant_param) {
    	this.restaurants.add(restaurant_param);
    }
    
    public Restaurant restaurantAt(int index) {
    	return this.restaurants.get(index);
    }
    
    
    public int numRestaurants() {
        return restaurants.size();
    }

}
