<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Список пользователей</title>
</head>
<body>
<center>
    <h1>Пользователи</h1>
    <h2>
        <a href="/new">Добавить нового пользователя</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">Список всех пользователей</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="3">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>password</th>
        </tr>
        <c:forEach var="user" items="${listUsers}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${iser.name}" /></td>
                <td><c:out value="${user.password}" /></td>
                <td>
                    <a href="/edit?id=<c:out value='${user.id}' />">Изменить</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/delete?id=<c:out value='${user.id}' />">Удалить</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
