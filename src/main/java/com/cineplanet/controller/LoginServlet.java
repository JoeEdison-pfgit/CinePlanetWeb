package com.cineplanet.controller;

import com.cineplanet.dao.UserDAO;
import com.cineplanet.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String ATTR_USER     = "user";
    private static final String JSP_LOGIN     = "/WEB-INF/jsp/login.jsp";
    private static final String URL_CARTELERA = "/cartelera";

    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            userDao = new UserDAO();
        } catch (Exception e) {
            throw new ServletException("Error inicializando UserDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Si ya está autenticado, redirige a cartelera
        if (req.getSession().getAttribute(ATTR_USER) != null) {
            resp.sendRedirect(req.getContextPath() + URL_CARTELERA);
            return;
        }
        // Sino, muestra el formulario
        req.getRequestDispatcher(JSP_LOGIN).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log("🍿 LoginServlet.doPost() invoked");

        String email = Optional.ofNullable(req.getParameter("email"))
                                  .orElse("").trim();
        String password = Optional.ofNullable(req.getParameter("password"))
                                  .orElse("");

        // Validación de campos vacíos
        if (email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "Usuario y contraseña son obligatorios");
            req.getRequestDispatcher(JSP_LOGIN).forward(req, resp);
            return;
        }

        try {
            User usuario = userDao.validate(email, password);
            if (usuario != null) {
                // Login correcto: guardamos en sesión y redirigimos
                log("✔ Login exitoso para usuario=" + email);
                HttpSession session = req.getSession();
                session.setAttribute(ATTR_USER, usuario);
                resp.sendRedirect(req.getContextPath() + URL_CARTELERA);
            } else {
                // Credenciales inválidas
                log("✘ Credenciales inválidas para usuario=" + email);
                req.setAttribute("error", "Usuario o contraseña incorrectos");
                req.getRequestDispatcher(JSP_LOGIN).forward(req, resp);
            }
        } catch (Exception ex) {
            log("Error validando usuario", ex);
            throw new ServletException("Error al validar usuario", ex);
        }
    }
}
