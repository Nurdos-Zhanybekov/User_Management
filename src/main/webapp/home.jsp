<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 15.07.2025
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Management</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body class="container-fluid card" style="width: 40rem">
<form action="register" method="post">
    <h2 class="bg-danger text-white text-center">Registration Form</h2>
    <table class="table table-hover">
        <tr>
            <td>Name</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="userEmail"></td>
        </tr>
        <tr>
            <td>Mobile Number</td>
            <td><input type="text" name="userMobile"></td>
        </tr>
        <tr>
            <td>Date of Birth</td>
            <td><input type="date" id="birthday" name="birthDate"></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td><input type="radio" id="male" name="userGender" value="male">Male</td>
            <td><input type="radio" id="female" name="userGender" value="female">Female</td>
        </tr>
        <tr>
            <td><input type="submit" value="Register" style="color: #005cbf; background: white; border-color: #005cbf"></td>
            <td><input type="reset" value="Cancel" style="color: #005cbf; background: white; border-color: #005cbf"></td>
        </tr>
    </table>
    <a href="usersList">User List</a>
</form>
</body>
</html>
