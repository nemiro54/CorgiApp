<jsp:useBean id="id" scope="request" type="java.lang.Long"/>
<jsp:useBean id="name" scope="request" type="java.lang.String"/>
<jsp:useBean id="surname" scope="request" type="java.lang.String"/>
<jsp:useBean id="age" scope="request" type="java.lang.Integer"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>User's details</title>
</head>
<body>
<p>Id: <%=id%></p>
<p>Name: <%=name%></p>
<p>Surname: <%=surname%></p>
<p>Age: <%=age%></p>
<p><a href="/users/search">Go back</a></p>
</body>
</html>
