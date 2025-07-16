package io.project.usermanagement;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final String query = "INSERT INTO users(name, email, mobile_number, birth_date, gender) VALUES (?, ?, ?, ?, ?)";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String mobileNumber = request.getParameter("userMobile");
        LocalDate date = LocalDate.parse(request.getParameter("birthDate"));
        String gender = request.getParameter("userGender");

        try(Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobileNumber);
            ps.setDate(4, Date.valueOf(date));
            ps.setString(5, gender);

            int count = ps.executeUpdate();
            if(count == 1){
                printWriter.println("<h2>User added successfully</h2>");
            }else{
                printWriter.println("<h2>User is not added successfully</h2>");
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }

        printWriter.println("<a href='home.jsp'>Home</a>");
        printWriter.println("<br>");
        printWriter.println("<a href='usersList'>Users List</a>");
    }

    public void destroy() {
    }
}