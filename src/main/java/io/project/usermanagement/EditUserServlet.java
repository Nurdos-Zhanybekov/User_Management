package io.project.usermanagement;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/editUser")
public class EditUserServlet extends HttpServlet {
    private static final String query = "UPDATE users SET name=?, email=?, mobile_number=?, birth_date=?, gender=? WHERE id=?";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String mobileNumber = request.getParameter("userMobileNumber");
        LocalDate date = LocalDate.parse(request.getParameter("userBirthDate"));
        String gender = request.getParameter("userGender");
        int id = Integer.parseInt(request.getParameter("id"));

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobileNumber);
            ps.setDate(4, Date.valueOf(date));
            ps.setString(5, gender);
            ps.setInt(6, id);
            int count = ps.executeUpdate();

            if(count == 1){
                pw.println("User Edited Successfully");
            }else{
                pw.println("User is not Edited");
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }

        pw.println("<a href='home.jsp'>Home</a>");
    }

    public void destroy() {
    }
}