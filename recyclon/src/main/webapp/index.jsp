<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bienvenida</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            background-color: white;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        h1 {
            color: #2c3e50;
        }
        .date {
            font-style: italic;
            color: #7f8c8d;
        }
        .button {
            display: inline-block;
            background-color: #3498db;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }
        .button:hover {
            background-color: #2980b9;
        }
    </style>
</head>
<body>
    <c:set var="now" value="<%= new java.util.Date() %>" />
    <div class="container">
        <h1>¡Bienvenido a nuestra aplicación!</h1>
        <p>Nos alegra verte por aquí. Esta es una página de ejemplo que muestra cómo usar JSP y JSTL en Jakarta EE 10.</p>
        <p class="date">Hoy es: <fmt:formatDate value="${now}" pattern="EEEE, d 'de' MMMM 'de' yyyy"/></p>
        <p>
            <a href="${pageContext.request.contextPath}/hola" class="button">Ir al Servlet de saludo</a>
        </p>
    </div>    
</body>
</html>