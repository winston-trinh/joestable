package com.wntrinh.assignment4.reservationInfo;

import java.sql.Date;
import java.sql.Time;

public class ReservationInfo {
	private int reservationId;
    private String name;
    private String restaurantId;
    private Date date;
    private Time time;
    private String reservationNotes;
    private String address;
    private String phone;
    private String cuisine;
    private String price;
    private String url;
    private String image_url;
    private double rating;
    
    public ReservationInfo(int reservationId, String name, String restaurantId, Date date, Time time, String reservationNotes,
                           String address, String phone, String cuisine, String price, double rating, String url, String image_url) {
        this.reservationId = reservationId;
        this.name = name;
        this.restaurantId = restaurantId;
        this.date = date;
        this.time = time;
        this.reservationNotes = reservationNotes;
        this.address = address;
        this.phone = phone;
        this.cuisine = cuisine;
        this.price = price;
        this.rating = rating;
        this.url = url;
        this.image_url = image_url;
    }
    
    public ReservationInfo() {
		this.reservationId = -1;
		this.name = "";
		this.restaurantId = "";
		this.date = new Date((long)0.0);
		this.time = new Time((long)0.0);
		this.reservationNotes = "";
		this.address = "";
		this.phone = "";
		this.cuisine = "";
		this.price = "";
		this.rating = -1.0;
		this.url = "";
        this.image_url = "";
	}
    
    public int getReservationId() {
        return reservationId;
    }

    public String getName() {
        return name;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public String getReservationNotes() {
        return reservationNotes;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }
    
    public String getUrl() {
        return url;
    }
    
    public String getImageUrl() {
        return image_url;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public void setReservationNotes(String reservationNotes) {
        this.reservationNotes = reservationNotes;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public void setImageUrl(String image_url) {
        this.image_url = image_url;
    }

    @Override
    public String toString() {
        return "ReservationInfo{" +
                "reservationId=" + reservationId +
                ", name='" + name + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", reservationNotes='" + reservationNotes + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", price='" + price + '\'' +
                ", rating=" + rating +
                '}';
    }
}
