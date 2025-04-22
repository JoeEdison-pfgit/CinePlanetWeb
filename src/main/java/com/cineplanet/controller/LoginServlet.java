package com.cineplanet.controller;

import com.cineplanet.dao.UserDAO;
import com.cineplanet.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 * Servlet encargado de mostrar y procesar el formulario de login.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    @Override
    public void init() throws ServletException {
        try {
            userDao = new UserDAO();
        } catch (NamingException e) {
            throw new ServletException("No se pudo inicializar UserDAO", e);
        }
    }

    /**
     * doGet: simplemente reenvía al JSP de login para mostrar el formulario.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Si ya está autenticado, redirigimos directamente a cartelera
        if (req.getSession().getAttribute("user") != null) {
            resp.sendRedirect(req.getContextPath() + "/cartelera");
            return;
        }
        // Mostrar página de login
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
           .forward(req, resp);
    }

    /**
     * doPost: recibe credenciales, valida y decide redirección o re-muestra login con error.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User usuario = userDao.validate(username, password);

            if (usuario != null) {
                // Credenciales correctas
                req.getSession().setAttribute("user", usuario);
                resp.sendRedirect(req.getContextPath() + "/cartelera");
            } else {
                // Credenciales inválidas
                req.setAttribute("error", "Usuario o contraseña incorrectos");
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp")
                   .forward(req, resp);
            }

        } catch (SQLException ex) {
            // Error de BD
            throw new ServletException("Error al validar usuario en base de datos", ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para autenticar usuarios";
    }
}
