package com.wntrinh.assignment4.servlets;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.wntrinh.assignment4.JDBCConnector.JDBCConnector;

@WebServlet("/processLogIn")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*// If we want to return a response with the form data in json format
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");*/

        // Retrieve form parameters
        String username = request.getParameter("login-username");
        String password = request.getParameter("login-password");
        
        try {
            String email = JDBCConnector.getUser(username, password);
            
            if (!(email.isEmpty())) {
            	Cookie userCookie = new Cookie("email", email);
            	userCookie.setMaxAge(3600); // Expires in 1 hour
                userCookie.setPath("/");
                response.addCookie(userCookie); 
            } else {
            	response.getWriter().write("Invalid username or password!");
            }          
        } catch (SQLIntegrityConstraintViolationException e) {
            // Duplicate entry error
            response.getWriter().write("Invalid username or password!");
        } catch (SQLException | ClassNotFoundException e) {
            // Handle other exceptions
            e.printStackTrace();
            response.getWriter().write("Internal server error occurred");
        }
        
        /*// Write JSON to response
        response.getWriter().write(json);*/
    }
}

