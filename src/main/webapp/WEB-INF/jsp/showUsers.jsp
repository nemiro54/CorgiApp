<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show all users</title>
</head>
<body>
<div>
    <h2>Users: </h2>

    <c:choose>
        <c:when test="${users != null && !users.isEmpty()}">
            <table>
                <thead>
                <tr>
                    <th><b>Name</b></th>
                    <th><b>Surname</b></th>
                    <th><b>Age</b></th>
                    <th><b>Show</b></th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.surname}"/></td>
                        <td><c:out value="${user.age}"/></td>
                        <td><a href="/users/details?id=${user.id}">Show</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
        </c:when>
        <c:otherwise>
            <H3>There aren no users.</H3>
            <br>
        </c:otherwise>
    </c:choose>
    <br>

    <div>
        <a href="<c:url value="/users/new"/> ">
            <button>Add new user</button>
        </a>
    </div>
</div>

</body>
</html>