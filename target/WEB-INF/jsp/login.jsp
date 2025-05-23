<%-- 
    Document   : login.jsp
    Created on : 21 abr 2025, 18:52:14
    Author     : wilme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio de Sesión | Cineplanet</title>
    <link rel="stylesheet" href="/resources/css/styles_log.css">
</head>

<body>
    <div class="container">
        <form class="login-form" action="cartelera.html" method="get">
            <img src="https://www.greatplacetowork.com.pe/images/certification/companies/cineplanet/logo.png" alt="Logo MASS"
                class="logo">
            <h1>Bienvenido a Cineplanet</h1>
            <div class="input-group">
                <input type="text" placeholder="Usuario" required>
            </div>
            <div class="input-group">
                <input type="password" placeholder="Contraseña" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn primary">Ingresar</button>
                <button type="button" class="btn secondary"
                    onclick="window.location.href='register.html'">Registrarse</button>

            </div>
        </form>
    </div>
</body>

</html>