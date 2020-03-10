<%--
  Created by IntelliJ IDEA.
  User: Тимофей
  Date: 03.03.2020
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form name = "user" action = "/adduserF" method="post">
    <p>Name</p>
    <input title = "Name" type = "text" name ="name">
    <p>Password</p>
    <input title = "Password" type = "text" name ="password">
    <input type = "submit" value="Add user to base">
</form>
</body>
</html>
