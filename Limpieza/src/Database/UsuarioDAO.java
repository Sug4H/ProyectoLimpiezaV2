/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author Erick H
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Usuario;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        connection = DatabaseManager.getConnection();
    }

    // Método para insertar un nuevo usuario en la base de datos
    public void insertarUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO Usuarios (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un usuario en la base de datos
    public void actualizarUsuario(Usuario usuario) {
        try {
            String sql = "UPDATE Usuarios SET username = ?, password = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getUsername());
            statement.setString(2, usuario.getPassword());
            statement.setInt(3, usuario.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un usuario de la base de datos
    public void eliminarUsuario(int usuarioId) {
        try {
            String sql = "DELETE FROM Usuarios WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, usuarioId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para recuperar un usuario por nombre de usuario
    public Usuario obtenerUsuarioPorUsername(String username) {
        try {
            String sql = "SELECT * FROM Usuarios WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                int id = result.getInt("id");
                String password = result.getString("password");
                return new Usuario(id, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Usuario no encontrado
    }

    // Método para recuperar todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Usuarios";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                Usuario usuario = new Usuario(id, username, password);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

}
