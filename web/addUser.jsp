<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Добавить пользователя</title>
</head>
<body>
<h3>Новый пользователь</h3>
<form action = "/addUser" method="post">
    <label>Имя</label><br>
    <input name="name"/><br><br>
    <label>Пароль</label><br>
    <input name="password" /><br><br>
    <input type="submit" value="Сохранить" />
</form>
</body>
</html>

