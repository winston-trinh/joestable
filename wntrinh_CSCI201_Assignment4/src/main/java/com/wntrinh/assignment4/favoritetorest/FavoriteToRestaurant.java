package com.wntrinh.assignment4.favoritetorest;

public class FavoriteToRestaurant {
    private String restaurantID;
    private int favID;

    public FavoriteToRestaurant(String restaurantID, int favID) {
        this.restaurantID = restaurantID;
        this.favID = favID;
    }

    public String getRestaurantID() {
        return restaurantID;
    }

    public int getFavID() {
        return favID;
    }
}
