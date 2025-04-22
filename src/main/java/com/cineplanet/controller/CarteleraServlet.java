package com.cineplanet.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CarteleraServlet", urlPatterns = {"/cartelera"})
public class CarteleraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
    log("🛠 doGet /cartelera – forward a cartelera.jsp");
    req.getRequestDispatcher("/WEB-INF/jsp/cartelera.jsp").forward(req, resp);
}

}
