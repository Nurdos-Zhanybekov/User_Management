package io.project.usermanagement;

import java.io.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private static final String query = "DELETE FROM users WHERE id=?";

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            int count = ps.executeUpdate();

            if (count == 1) {
                pw.println("User Deleted Successfully");
            } else {
                pw.println("User is not Deleted");
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }

        pw.println("<a href='home.jsp'>Home</a>");
    }

    public void destroy() {
    }
}