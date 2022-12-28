<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
    <form action="save" method="post">
        <label for="name">Имя</label>
        <input id="name" name="name" type="text">
        <br><br>
        <label for="surname">Фамилия</label>
        <input id="surname" name="surname" type="text">
        <br><br>
        <label for="age">Возраст</label>
        <input id="age" name="age" type="number" min="0">
        <br><br>
        <button type="submit">Добавить пользователя</button>
        <a href="<c:url value="/users"/> ">
            <button>Отмена</button>
        </a>
    </form>
</body>
</html>
