<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión | Cineplanet</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/styles_log.css" />
</head>

<body>
    <div class="container">
        <form action="${pageContext.request.contextPath}/login" method="post">

            <img src="https://www.greatplacetowork.com.pe/images/certification/companies/cineplanet/logo.png"
                 alt="Logo Cineplanet" class="logo">
            <h1>Bienvenido a Cineplanet</h1>

            <div class="input-group">
                <label for="email" class="usuario">Correo</label>
                <input type="text" id="email" name="email" placeholder="Correo" required>
            </div>

            <div class="input-group">
                <label for="password" class="contra">Contraseña</label>
                <input type="password" id="password" name="password" placeholder="Contraseña" required>
            </div>

            <c:if test="${not empty error}">
                <div class="error-message">${error}</div>
            </c:if>

            <div class="btn-group">
                <button type="submit" class="btn primary">Ingresar</button>
                <button type="button" class="btn secondary"
                        onclick="window.location.href='${pageContext.request.contextPath}/register'">
                    Registrarse
                </button>
            </div>

        </form>
    </div>
</body>

</html>
