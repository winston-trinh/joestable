package com.wntrinh.assignment4.reservation;

import java.sql.Date;
import java.sql.Time;

public class Reservation {
	private int reservationId;
    private String email;
    private String restaurantId;
    private Date date;
    private Time time;
    private String reservationNotes;
    
    public int getReservationId() {
        return reservationId;
    }

    public String getEmail() {
        return email;
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

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationId=" + reservationId +
                ", email='" + email + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", reservationNotes='" + reservationNotes + '\'' +
                '}';
    }
}
