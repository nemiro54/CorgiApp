<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Show all users</title>
</head>
<body>

<div>
    <h2>Users: </h2>

    <table>
        <thead>
        <tr>
            <th><b>Id</b></th>
            <th><b>Name</b></th>
            <th><b>Surname</b></th>
            <th><b>Age</b></th>
            <th><b>Show</b></th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${users}" var="users">
            <c:choose>
                <c:when test="${users != null}">
                    <tr>
                        <td><c:out value="${users.getName()}"/>
                        <td><c:out value="${users.getSurname()}"/>
                        <td><c:out value="${users.getAge()}"/>
                        <td><a href="/users/show?id=${users.id}">Show</a>
                    </tr>
                </c:when>
                <c:otherwise>
                    <H3>There aren't users.</H3>
                    <br>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </tbody>
    </table>
    <br>

    <div>
        <a href="<c:url value="/users/new"/> ">
            <button>Add new user</button>
        </a>
    </div>
</div>

</body>
</html>