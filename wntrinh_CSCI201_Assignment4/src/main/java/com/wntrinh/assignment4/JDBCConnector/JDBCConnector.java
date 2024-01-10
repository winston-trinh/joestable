package com.wntrinh.assignment4.JDBCConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wntrinh.assignment4.favoritetorest.FavoriteToRestaurant;
import com.wntrinh.assignment4.reservation.Reservation;
import com.wntrinh.assignment4.restaurant.Restaurant;
import com.wntrinh.assignment4.restaurant.categories.Category;
import com.wntrinh.assignment4.restaurant.location.Location;
import com.wntrinh.assignment4.restaurantSQL.RestaurantSQL;

public class JDBCConnector {
	public static void insertNewUser(String username, String password, String email) throws ClassNotFoundException, SQLException {
    	Connection conn = null;
    	PreparedStatement st = null;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root"); // Maybe update
    		st = conn.prepareStatement("INSERT INTO `JoesTable`.`Users` (username, password, email) VALUES (?, ?, ?)");
            st.setString(1, username);
            st.setString(2, password);
            st.setString(3, email);
            st.executeUpdate();
    	} finally {
    		try {
    			if (st != null) {
    				st.close();
    			}
    			if (conn != null) {
    				conn.close();
    			}
    		} catch (SQLException sqle) {
    			System.out.println("SQLException: " + sqle.getMessage());
    		}
    	}
    }
	
	public static String getUser(String username, String password) throws ClassNotFoundException, SQLException {
    	Connection conn = null;
    	PreparedStatement st = null;
    	ResultSet rs = null;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root"); // Maybe update
    		 st = conn.prepareStatement("SELECT * FROM `JoesTable`.`Users` WHERE username = ? AND password = ?");
	        st.setString(1, username);
	        st.setString(2, password);
	        rs = st.executeQuery();
	        
	        // Check if the query returned any rows
	        if (rs.next()) {
	            // If a row is found, return the email from the ResultSet
	            return rs.getString("email");
	        } else {
	            // If no matching user is found, return null or an empty string, or handle it as needed
	        	return "";
	        }
    	} finally {
    		try {
    			if (rs != null) {
                    rs.close();
                }
    			if (st != null) {
    				st.close();
    			}
    			if (conn != null) {
    				conn.close();
    			}
    		} catch (SQLException sqle) {
    			System.out.println("SQLException: " + sqle.getMessage());
    		}
    	}
    }
	
	public static String getUserName(String email) throws ClassNotFoundException, SQLException {
    	Connection conn = null;
    	PreparedStatement st = null;
    	ResultSet rs = null;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root"); // Maybe update
	        
	        st = conn.prepareStatement("SELECT username FROM `JoesTable`.`Users` WHERE email = ?");
	        st.setString(1, email);
	        rs = st.executeQuery();

	        // Check if the query returned any rows
	        if (rs.next()) {
	            // If a row is found, return the username from the ResultSet
	            return rs.getString("username");
	        } else {
	            // If no matching user is found, return null or an empty string, or handle it as needed
	            return null;
	        }
    	} finally {
    		try {
    			if (rs != null) {
                    rs.close();
                }
    			if (st != null) {
    				st.close();
    			}
    			if (conn != null) {
    				conn.close();
    			}
    		} catch (SQLException sqle) {
    			System.out.println("SQLException: " + sqle.getMessage());
    		}
    	}
    }
	
	public static boolean isUserFavorite(String email, String restaurantID) throws ClassNotFoundException, SQLException {
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    boolean isFavorite = false;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");
	        st = conn.prepareStatement("SELECT * FROM `JoesTable`.`Favorites` WHERE email = ? AND restaurant_id = ?");
	        st.setString(1, email);
	        st.setString(2, restaurantID);
	        rs = st.executeQuery();

	        // Check if the query returned any rows
	        if (rs.next()) {
	            // If a row is found, it means the user has a favorite, so set isFavorite to true
	            isFavorite = true;
	        }
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("SQLException: " + sqle.getMessage());
	        }
	    }

	    return isFavorite;
	}
	
	public static void insertNewRestaurant(String restaurantId, String name, String address, String phone, String cuisine,
            String price, double rating, String url, String imageUrl) throws SQLException, ClassNotFoundException {
        
		Connection conn = null;
        PreparedStatement st = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");

            // Check if the restaurant already exists
            if (!checkRestaurant(restaurantId)) {
                // Insert into Restaurants table
                st = conn.prepareStatement(
                        "INSERT INTO `JoesTable`.`Restaurants` VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
                st.setString(1, restaurantId);
                st.setString(2, name);
                st.setString(3, address);
                st.setString(4, phone);
                st.setString(5, cuisine);
                st.setString(6, price);
                st.setDouble(7, rating);
                st.setString(8, url);
                st.setString(9, imageUrl);
                st.executeUpdate();
            }
        } finally {
            // Close resources
        	// Close resources in the finally block
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
	
	public static boolean checkRestaurant(String restaurantId) throws SQLException {
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");

	        st = conn.prepareStatement("SELECT COUNT(*) FROM `JoesTable`.`Restaurants` WHERE restaurant_id = ?");
	        st.setString(1, restaurantId);
	        rs = st.executeQuery();

	        // Check if the query returned any rows
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0; // If count is greater than 0, the restaurant exists
	        }
	    } catch (ClassNotFoundException e) {
	        // Handle ClassNotFoundException
	        e.printStackTrace();
	    } finally {
	        // Close resources
	    	// Close resources manually
	        if (rs != null) {
	            try {
	                rs.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (st != null) {
	            try {
	                st.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (conn != null) {
	            try {
	                conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    // Return false in case of an exception
	    return false;
	}
	
	public static void addToFavorites(String email, String restaurantID) throws ClassNotFoundException, SQLException {
	    Connection conn = null;
	    PreparedStatement st = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");

	        // Check if the entry already exists in Favorites table
	        if (!isUserFavorite(email, restaurantID)) {
	            // Insert into Favorites table
	            st = conn.prepareStatement("INSERT INTO `JoesTable`.`Favorites` (email, restaurant_id) VALUES (?, ?)");
	            st.setString(1, email);
	            st.setString(2, restaurantID);
	            st.executeUpdate();
	        }
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("SQLException: " + sqle.getMessage());
	        }
	    }
	}
	
	public static void removeFromFavorites(String email, String restaurantID) throws ClassNotFoundException, SQLException {
	    Connection conn = null;
	    PreparedStatement st = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");

	        // Check if the entry already exists in Favorites table
	        if (isUserFavorite(email, restaurantID)) {
	            // Remove from Favorites table
	            st = conn.prepareStatement("DELETE FROM `JoesTable`.`Favorites` WHERE email = ? AND restaurant_id = ?");
	            st.setString(1, email);
	            st.setString(2, restaurantID);
	            st.executeUpdate();
	            System.out.println("Successfully removed...");
	        }
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("SQLException: " + sqle.getMessage());
	        }
	    }
	}
	
	public static List<FavoriteToRestaurant> getFavoritesByEmail(String email) throws ClassNotFoundException, SQLException {
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    List<FavoriteToRestaurant> restaurantIDs = new ArrayList<>();

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");

	        st = conn.prepareStatement("SELECT restaurant_id, fav_id FROM `JoesTable`.`Favorites` WHERE email = ?");
	        st.setString(1, email);
	        rs = st.executeQuery();

	        // Process the result set
	        while (rs.next()) {
	            String restaurantID = rs.getString("restaurant_id");
	            int favID = rs.getInt("fav_id");
	            FavoriteToRestaurant favorite = new FavoriteToRestaurant(restaurantID, favID);
	            restaurantIDs.add(favorite);
	        }
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("SQLException: " + sqle.getMessage());
	        }
	    }
	    return restaurantIDs;
	}
	
	public static List<RestaurantSQL> getFavoriteRestaurantsByEmail(String email) throws ClassNotFoundException, SQLException {
	    List<FavoriteToRestaurant> restaurantIDs = getFavoritesByEmail(email);
	    List<RestaurantSQL> favoriteRestaurants = new ArrayList<>();

	    for (FavoriteToRestaurant favoriteToRestaurant : restaurantIDs) {
	        // Assuming getRestaurantById is a method that retrieves RestaurantSQL by ID
	        RestaurantSQL restaurant = getRestaurantById(favoriteToRestaurant.getRestaurantID(), favoriteToRestaurant.getFavID());

	        if (restaurant != null) {
	            favoriteRestaurants.add(restaurant);
	        }
	    }

	    return favoriteRestaurants;
	}
	
	private static RestaurantSQL getRestaurantById(String restaurantID, int favID) throws ClassNotFoundException, SQLException {
	    Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    RestaurantSQL restaurant = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");

	        st = conn.prepareStatement("SELECT * FROM `JoesTable`.`Restaurants` WHERE restaurant_id = ?");
	        st.setString(1, restaurantID);
	        rs = st.executeQuery();

	        if (rs.next()) {
	            // Retrieve restaurant information from the result set
	            String name = rs.getString("name");
	            String address = rs.getString("address");
	            String phone = rs.getString("phone");
	            String cuisine = rs.getString("cuisine");
	            String price = rs.getString("price");
	            double rating = rs.getDouble("rating");
	            String url = rs.getString("url");
	            String imageUrl = rs.getString("image_url");

	            // Create a Restaurant object
	            restaurant = new RestaurantSQL(restaurantID, name, address, phone, cuisine, rating, price, url, imageUrl, favID);
	            	
	        }
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("SQLException: " + sqle.getMessage());
	        }
	    }

	    return restaurant;
	}
	
	public static void addReservation(String email, String restaurantID, String reservationDate, String reservationTime, String reservationNotes) throws ClassNotFoundException, SQLException {
	    Connection conn = null;
	    PreparedStatement st = null;

	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");

	        // Insert into Reservations table
	        st = conn.prepareStatement("INSERT INTO `JoesTable`.`Reservations` (email, restaurant_id, date, time, reservation_notes) VALUES (?, ?, ?, ?, ?)");
	        st.setString(1, email);
	        st.setString(2, restaurantID);
	        st.setString(3, reservationDate);
	        st.setString(4, reservationTime);
	        st.setString(5, reservationNotes);
	        st.executeUpdate();
	    } finally {
	        try {
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("SQLException: " + sqle.getMessage());
	        }
	    }
	}
	
	public static List<Reservation> getReservationsByEmail(String email) throws SQLException, ClassNotFoundException {
		Connection conn = null;
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    List<Reservation> reservations = new ArrayList<>();
	    
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/JoesTable?user=root&password=root");
	        st = conn.prepareStatement("SELECT * FROM `JoesTable`.`Reservations` WHERE email = ?");
	        st.setString(1, email);
	        rs = st.executeQuery();
	        
	        while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(rs.getInt("reservation_id"));
                reservation.setEmail(rs.getString("email"));
                reservation.setRestaurantId(rs.getString("restaurant_id"));
                reservation.setDate(rs.getDate("date"));
                reservation.setTime(rs.getTime("time"));
                reservation.setReservationNotes(rs.getString("reservation_notes"));
                reservations.add(reservation);
            }
	    } finally {
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (st != null) {
	                st.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException sqle) {
	            System.out.println("SQLException: " + sqle.getMessage());
	        }
	    }
	    return reservations;
	}
	
	public static RestaurantSQL getReservationRestaurants(String restID, int reservationID) throws ClassNotFoundException, SQLException {

        RestaurantSQL restaurant = getRestaurantById(restID, reservationID);

        if (restaurant == null) {
        	RestaurantSQL nullRest = new RestaurantSQL();
            return nullRest;
        }
		return restaurant;
	}

}
