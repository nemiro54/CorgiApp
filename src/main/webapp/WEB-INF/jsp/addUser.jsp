<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="errorMessage" class="java.lang.String" scope="request"/>

<html>
<head>
    <title>Add user</title>
</head>
<body>
<form action="save" method="post">
    <label for="name">Name</label>
    <input id="name" name="name" type="text">
    <br><br>
    <label for="surname">Surname</label>
    <input id="surname" name="surname" type="text">
    <br><br>
    <label for="age">Age</label>
    <input id="age" name="age" type="number" min="0" value="0">
    <br><br>

    <button type="submit">Add user</button>
    <a href="<c:url value="/users"/> ">
        <button>Cancel</button>
    </a>
    <p><%= errorMessage%></p>
</form>
</body>
</html>
