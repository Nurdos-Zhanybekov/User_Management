package io.project.usermanagement;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/usersList")
public class UsersListServlet extends HttpServlet {
    private static String query = "SELECT * FROM users";


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/html");

        try(Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            printWriter.println("<table border='1' align='center'>");
            printWriter.println("<tr>");
            printWriter.println("<th>User Id</th>");
            printWriter.println("<th>User Name</th>");
            printWriter.println("<th>User Email</th>");
            printWriter.println("<th>User Mobile No</th>");
            printWriter.println("<th>Gender</th>");
            printWriter.println("<th>Date of Birth</th>");
            printWriter.println("<th>Edit</th>");
            printWriter.println("<th>Delete</th>");
            printWriter.println("</tr>");

            while(rs.next()){
                printWriter.println("<tr>");
                printWriter.println("<td>" + rs.getInt("id") + "</td>");
                printWriter.println("<td>" + rs.getString("name") + "</td>");
                printWriter.println("<td>" + rs.getString("email") + "</td>");
                printWriter.println("<td>" + rs.getDouble("mobile_number") + "</td>");
                printWriter.println("<td>" + rs.getString("gender") + "</td>");
                printWriter.println("<td>" + rs.getString("birth_date") + "</td>");
                printWriter.println("<td><a href='editScreen?id=" + rs.getInt("id") + "'>Edit</a></td>");
                printWriter.println("<td><a href='deleteurl?id=" + rs.getInt("id") + "'>Delete</a></td>");
                printWriter.println("</tr>");
            }

            printWriter.println("</table>");
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());;
        }

        printWriter.println("<a href='home.jsp'>Home</a>");
    }

    public void destroy() {
    }
}