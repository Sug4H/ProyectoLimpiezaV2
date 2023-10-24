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
import Model.JefeCuadrilla;

public class JefeCuadrillaDAO {
    private Connection connection;

    public JefeCuadrillaDAO() {
        connection = DatabaseManager.getConnection();
    }

    // Método para insertar un nuevo jefe de cuadrilla en la base de datos
    public void insertarJefeCuadrilla(JefeCuadrilla jefe) {
        try {
            String sql = "INSERT INTO JefesCuadrilla (nombre) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, jefe.getNombre());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Método para actualizar un jefe de cuadrilla en la base de datos
    public void actualizarJefeCuadrilla(JefeCuadrilla jefe) {
        try {
            String sql = "UPDATE JefesCuadrilla SET nombre = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, jefe.getNombre());
            statement.setInt(2, jefe.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Método para eliminar un jefe de cuadrilla de la base de datos
    public void eliminarJefeCuadrilla(int jefeId) {
        try {
            String sql = "DELETE FROM JefesCuadrilla WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, jefeId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Método para recuperar una lista de todos los jefes de cuadrilla
    public List<JefeCuadrilla> obtenerTodosLosJefes() {
        List<JefeCuadrilla> jefes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM JefesCuadrilla";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String nombre = result.getString("nombre");
                JefeCuadrilla jefe = new JefeCuadrilla(id, nombre);
                jefes.add(jefe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
          
        }
        return jefes;
    }

   
}
