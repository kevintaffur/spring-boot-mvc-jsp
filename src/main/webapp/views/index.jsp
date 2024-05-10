<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Spring Boot MVC using JSP</title>
    </head>
    <body>
        Welcome my friend...
        <form action="aliens" method="post">
            Enter your id: <input type="text" name="id"></br>
            Enter your name: <input type="text" name="name"></br>
            <input type="submit">
        </form>
        <hr>
        <form action="aliens-by-name" method="get">
            Enter your name: <input type="text" name="name"></br>
            <input type="submit">
        </form>
    </body>
</html>