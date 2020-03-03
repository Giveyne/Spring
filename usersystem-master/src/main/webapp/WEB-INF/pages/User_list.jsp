<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title></title>
</head>
<body>

<jsp:useBean id="UserServiceImpl" class="ru.igor.system.service.UserServiceImpl"> </jsp:useBean>

<table>
    <tr>
    <th> Id </th>
    <th> Name </th>
    <th> Password </th>
    </tr>

        <c:forEach var="user" items="${users}" >
           <tr>
                <td> <a href = "/user/${user.id}"><c:out  value="${user.id}"/> </a></td>
                <td> <c:out value="${user.name}" /></td>
                <td> <c:out value="${user.password}" /></td>
               <td> <a href = "/delete/${user.id}">Delete</a></td>
               <td> <a href = "/update/${user.id}">Update</a></td>
            </tr>
        </c:forEach>

    <a href = "/adduser"> Add user </a>
</table>
</body>
</html>