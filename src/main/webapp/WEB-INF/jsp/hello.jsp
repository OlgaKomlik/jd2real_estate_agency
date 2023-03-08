
<%--
  Created by IntelliJ IDEA.
  User: Noirix
  Date: 09.02.2023
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title>Hello</title>
</head>
<body>
${personName}

<div>
    <h1>System Persons</h1>
</div>
<div>
    <table>
        <tr>
            <td>Person Id</td>
            <td>Person Name</td>
            <td>Person Surname</td>
            <td>Birth date</td>
            <td>Passport Number</td>
            <td>Phone number</td>
            <td>Created</td>
            <td>Changed</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td>${person.id}</td>
                <td>${person.name}</td>
                <td>${person.surname}</td>
                <td>${person.birthDate}</td>
                <td>${person.passportNum}</td>
                <td>${person.phoneNum}</td>
                <td>${person.created}</td>
                <td>${person.changed}</td>
                <td><button>Edit</button></td>
                <td><button>Delete</button></td>
            </tr>
        </c:forEach>
    </table>
</div>


</body>
</html>
