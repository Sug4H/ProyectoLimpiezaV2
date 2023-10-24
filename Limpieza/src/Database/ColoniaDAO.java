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
import Model.Colonia;

public class ColoniaDAO {
    private Connection connection;

    public ColoniaDAO() {
        connection = DatabaseManager.getConnection();
    }

    // Método para insertar una nueva colonia en la base de datos
    public void insertarColonia(Colonia colonia) {
        try {
            String sql = "INSERT INTO Colonias (codigo_postal, nombre) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, colonia.getCodigoPostal());
            statement.setString(2, colonia.getNombre());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Método para actualizar una colonia en la base de datos
    public void actualizarColonia(Colonia colonia) {
        try {
            String sql = "UPDATE Colonias SET codigo_postal = ?, nombre = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, colonia.getCodigoPostal());
            statement.setString(2, colonia.getNombre());
            statement.setInt(3, colonia.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Método para eliminar una colonia de la base de datos
    public void eliminarColonia(int coloniaId) {
        try {
            String sql = "DELETE FROM Colonias WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, coloniaId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Método para recuperar una lista de todas las colonias
    public List<Colonia> obtenerTodasLasColonias() {
        List<Colonia> colonias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Colonias";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String codigoPostal = result.getString("codigo_Postal");
                String nombre = result.getString("nombre");
                Colonia colonia = new Colonia(id, codigoPostal, nombre);
                colonias.add(colonia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
          
        }
        return colonias;
    }

}
