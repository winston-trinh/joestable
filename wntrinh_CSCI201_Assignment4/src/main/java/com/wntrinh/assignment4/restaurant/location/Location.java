package com.wntrinh.assignment4.restaurant.location;

public class Location {
	private String[] display_address;
	
	// Constructor
    public Location(String[] display_address) {
        this.display_address = display_address;
    }

    // Default constructor
    public Location() {
        // Default constructor with no parameters
    	this.display_address = new String[0];
    }

    // Getter
    public String[] getAddress() {
        return display_address;
    }
}
