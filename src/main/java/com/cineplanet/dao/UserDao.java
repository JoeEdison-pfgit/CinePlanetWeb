package com.cineplanet.dao;

import com.cineplanet.model.User;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private final DataSource dataSource;

    public UserDAO() throws NamingException {
        // Busca el DataSource configurado en Tomcat (context.xml + web.xml)
        this.dataSource = (DataSource)
            new InitialContext().lookup("java:comp/env/jdbc/CineDb");
    }

    public User validate(String username, String password) throws SQLException {
        String sql = "SELECT id, username, password FROM users WHERE username = ? AND password = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null;
    }
}
