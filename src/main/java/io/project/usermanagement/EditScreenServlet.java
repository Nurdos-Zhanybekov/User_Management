package io.project.usermanagement;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    private static final String query = "SELECT * FROM users WHERE id=?";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        response.setContentType("text/html");

        int id = Integer.parseInt(request.getParameter("id"));
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                pw.println("<form action='editUser?id=" + rs.getInt("id") + "' method='post'");
                pw.println("<table align=center>");
                pw.println("<tr>");
                pw.println("<td>Name</td>");
                pw.println("<td><input type='text' name='userName' value='" + rs.getString("name") + "'></td>");
                pw.println("</tr>");
                pw.println("<td>Email</td>");
                pw.println("<td><input type='text' name='userEmail' value='" + rs.getString("email") + "'></td>");
                pw.println("</tr>");
                pw.println("<td>Mobile No</td>");
                pw.println("<td><input type='text' name='userMobile' value='" + rs.getString("mobile_number") + "'></td>");
                pw.println("</tr>");
                pw.println("<td>Date of Birth</td>");
                pw.println("<td><input type='date' name='userBirthDate' value='" + rs.getString("birth_date") + "'></td>");
                pw.println("</tr>");
                pw.println("<td>Gender</td>");
                pw.println("<td><input type='text' name='userGender' value='" + rs.getString("gender") + "'></td>");
                pw.println("</tr>");
                pw.println("<tr>");
                pw.println("<td><input type='submit' value='Edit'></td>");
                pw.println("<td><input type='reset' value='Cancel'></td>");
                pw.println("</tr>");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}