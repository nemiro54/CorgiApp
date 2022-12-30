<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="errorMessage" class="java.lang.String" scope="request"/>
<jsp:useBean id="user" class="com.andersen.corgiapp.entity.User" scope="request"/>

<html>
<head>
    <title>Edit user</title>
</head>
<body>
<form action="update" method="post">
    <input name="id" value="${user.id}" type="hidden">
    <br><br>
    <label for="name">Name</label>
    <input id="name" name="name" type="text" value="${user.name}">
    <br><br>
    <label for="surname">Surname</label>
    <input id="surname" name="surname" type="text" value="${user.surname}">
    <br><br>
    <label for="age">Age</label>
    <input id="age" name="age" type="number" min="0" value="${user.age}">
    <br><br>
    <button type="submit">Save changes</button>

    <a href="<c:url value="/users/details?id=${user.id}"/> ">
        <input type="button" value="Cancel"/>
    </a>
    <p><%= errorMessage%>
    </p>
</form>
</body>
</html>