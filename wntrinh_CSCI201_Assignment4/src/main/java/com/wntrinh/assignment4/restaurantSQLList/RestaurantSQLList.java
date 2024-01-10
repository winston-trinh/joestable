package com.wntrinh.assignment4.restaurantSQLList;

import java.util.ArrayList;
import com.wntrinh.assignment4.restaurantSQL.RestaurantSQL;

public class RestaurantSQLList {

    private ArrayList<RestaurantSQL> restaurants;
    
    public RestaurantSQLList() {
        this.restaurants = new ArrayList<RestaurantSQL>();
    }

    public ArrayList<RestaurantSQL> getRestaurants() {
        return restaurants;
    }
    
    public void addRestaurant(RestaurantSQL restaurant_param) {
    	this.restaurants.add(restaurant_param);
    }
    
    public RestaurantSQL restaurantAt(int index) {
    	return this.restaurants.get(index);
    }
    
    public int numRestaurants() {
        return restaurants.size();
    }
}
