
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
<form action = "/users" method="post">
    <input required type="text" name="name" placeholder="Имя">
    <input required type="text" name="password" placeholder="Пароль">
    <input type="submit" value="Добавить">
</form>
</body>
</html>
