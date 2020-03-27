<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>

<body>
<form:form Name="name" action = "/adduser" method="post">
<fieldset>
    <table cellspacing="0">
        <tr>
            <td>
                <p>Name</p>
                <input path = "name" type = "text" name ="name"/>
                <small id="name_msg">No spaces, please.</small><br/>
                <form:errors path="name" cssClass="error" />
            </td>
        </tr>
        <tr>
            <td>
                <p>Password</p>
                <input path= "password" type = "text" name ="password" />
                <small>6 characters or more (be tricky!)</small><br/>
                <form:errors path="password"/>
            </td>
        </tr>
        <tr>
            <th></th>
            <td><input name="commit" type="submit"
                       value="I accept. Create my account." /></td>
        </tr>
</form:form>
</body>
</html>

