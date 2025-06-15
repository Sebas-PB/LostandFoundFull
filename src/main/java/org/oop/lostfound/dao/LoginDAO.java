package org.oop.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.oop.lostfound.model.User;

public class LoginDAO
{
    private Connection connection; 

    public LoginDAO(Connection connection) {
        this.connection = connection;
    }

    // Mengecek login berdasarkan username/email + password
    public User checkLogin(String usernameOrEmail, String user_password)
    {
    String sql = "SELECT * FROM account WHERE (username = ? OR email = ?) AND user_password = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql))
    {
        stmt.setString(1, usernameOrEmail);
        stmt.setString(2, usernameOrEmail);
        stmt.setString(3, user_password);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next())
            {
                return new User(rs.getInt("id_account"), rs.getString("username"));
            }
        }
    } catch (SQLException e)
    {
        e.printStackTrace();
    }
    return null;
    }
}