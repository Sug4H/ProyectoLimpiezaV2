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
import java.util.Date;
import Model.ActividadLimpieza;

public class ActividadLimpiezaDAO {
    private Connection connection;

    public ActividadLimpiezaDAO() {
        connection = DatabaseManager.getConnection();
    }

    // Método para insertar una nueva actividad de limpieza en la base de datos
    public void insertarActividadLimpieza(ActividadLimpieza actividad) {
        try {
            String sql = "INSERT INTO ActividadesLimpieza (fecha, hora, descripcion, evidenciaImagen, id_colonia, id_jefeCuadrilla) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(actividad.getFecha().getTime()));
            statement.setString(2, actividad.getHora());
            statement.setString(3, actividad.getDescripcion());
            statement.setString(4, actividad.getEvidenciaImagen());
            statement.setInt(5, actividad.getId_colonia());
            statement.setInt(6, actividad.getId_jefeCuadrilla());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Método para actualizar una actividad de limpieza en la base de datos
    public void actualizarActividadLimpieza(ActividadLimpieza actividad) {
        try {
            String sql = "UPDATE ActividadesLimpieza SET fecha = ?, hora = ?, descripcion = ?, evidenciaImagen = ?, id_colonia = ?, id_jefeCuadrilla = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(actividad.getFecha().getTime()));
            statement.setString(2, actividad.getHora());
            statement.setString(3, actividad.getDescripcion());
            statement.setString(4, actividad.getEvidenciaImagen());
            statement.setInt(5, actividad.getId_colonia());
            statement.setInt(6, actividad.getId_jefeCuadrilla());
            statement.setInt(7, actividad.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
  
        }
    }

    // Método para eliminar una actividad de limpieza de la base de datos
    public void eliminarActividadLimpieza(int actividadId) {
        try {
            String sql = "DELETE FROM ActividadesLimpieza WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, actividadId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
    }

    // Método para recuperar una lista de todas las actividades de limpieza
    public List<ActividadLimpieza> obtenerTodasLasActividades() {
        List<ActividadLimpieza> actividades = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ActividadesLimpieza";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                Date fecha = result.getDate("fecha");
                String hora = result.getString("hora");
                String descripcion = result.getString("descripcion");
                String evidenciaImagen = result.getString("evidenciaImagen");
                int id_colonia = result.getInt("id_colonia");
                int id_jefeCuadrilla = result.getInt("id_jefeCuadrilla");

                ActividadLimpieza actividad = new ActividadLimpieza(id, fecha, hora, descripcion, evidenciaImagen, id_colonia, id_jefeCuadrilla);
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return actividades;
    }

    
}
