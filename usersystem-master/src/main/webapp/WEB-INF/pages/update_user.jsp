
<%--
  Created by IntelliJ IDEA.
  User: Тимофей
  Date: 03.03.2020
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<%--@elvariable id="user" type="ru.igor.system.model.User"--%>
<form:form modelAttribute="user"  action = "/update" method="post">
    <p>Id</p>
    <input readonly path = "id" type = "text" name ="id" value="${user.id}"/>

    <p>Name</p>
    <input path = "name" type = "text" name ="name" value="${user.name}"/>
    <form:errors path="name" cssClass="error" />
    <p>Password</p>
    <input path= "password" type = "text" name ="password" value="${user.password}"/>
    <form:errors path="password"/>
    <input type = "submit" value="Update user in base">
</form:form>
</body>
</html>
