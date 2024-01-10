package com.wntrinh.assignment4.restaurant.categories;

public class Category {
	private String alias;
	private String title;
	
	// Constructor
    public Category(String alias, String title) {
        this.alias = alias;
        this.title = title;
    }

    // Default constructor
    public Category() {
        // Default constructor with no parameters
    	this.alias = "";
        this.title = "";
    }

    // Getters and setters
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
