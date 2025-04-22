package com.cineplanet.dao;

import com.cineplanet.model.User;
import com.cineplanet.controller.conexion;

import java.sql.*;

public class UserDAO {

    /** Valida login por email y password */
    public User validate(String email, String password) throws SQLException {
        String sql = "SELECT usuario_id, nombre, email, password, telefono "
                   + "FROM usuario WHERE email = ? AND password = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("usuario_id"));
                    u.setNombre(rs.getString("nombre"));
                    u.setEmail(rs.getString("email"));
                    u.setPassword(rs.getString("password"));
                    u.setTelefono(rs.getString("telefono"));
                    return u;
                }
            }
        }
        return null;
    }

    /** Crea un nuevo usuario y devuelve la entidad con su ID generado */
    public User create(User user) throws SQLException {
        String sql = "INSERT INTO usuario (nombre, email, password, telefono) "
                   + "VALUES (?, ?, ?, ?)";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            // 1. Nombre
            ps.setString(1, user.getNombre());
            // 2. Email
            ps.setString(2, user.getEmail());
            // 3. Password (ya hasheada)
            ps.setString(3, user.getPassword());
            // 4. Teléfono
            ps.setString(4, user.getTelefono());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                throw new SQLException("Crear usuario falló, no se insertó ninguna fila.");
            }

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    user.setId(keys.getInt(1));
                } else {
                    throw new SQLException("Crear usuario falló, no se obtuvo ID generado.");
                }
            }

        } catch (SQLIntegrityConstraintViolationException dup) {
            // Error de clave única en email
            throw new SQLException("El correo '" + user.getEmail() + "' ya está registrado.", dup);
        }

        return user;
    }
}
