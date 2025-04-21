<%-- 
    Document   : register
    Created on : 21 abr 2025, 18:55:05
    Author     : wilme
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro | Cineplanet</title>
    <link rel="stylesheet" href="styles_log.css">
</head>
<body>
    <div class="container">
        <form class="login-form" action="cartelera.html">
            <img src="https://www.greatplacetowork.com.pe/images/certification/companies/cineplanet/logo.png" alt="Logo MASS" class="logo">
            <h1>Crear Cuenta</h1>
            <div class="input-group">
                <input type="text" placeholder="Nombre de usuario" required>
            </div>
            <div class="input-group">
                <input type="email" placeholder="Correo electrónico" required>
            </div>
            <div class="input-group">
                <input type="text" placeholder="telefono" required>
            </div>
            <div class="input-group">
                <input type="password" placeholder="Contraseña" required>
            </div>
            <div class="input-group">
                <input type="password" placeholder="Confirmar contraseña" required>
            </div>

            <div class="btn-group">
                <button type="submit" class="btn primary">Registrarse</button>
                <button type="button" class="btn secondary"
                    onclick="window.location.href='Login.html'">Iniciar Sesion</button>
            </div>
        </form>
    </div>
</body>
</html>