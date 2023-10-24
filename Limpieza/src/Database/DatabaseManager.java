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
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static Connection connection = null;
    private static final String DB_URL = "jdbc:mysql://localhost:3306/proyectodos"; // Cambia esta URL según tu configuración
    private static final String DB_USER = "root"; // Cambia el nombre de usuario
    private static final String DB_PASSWORD = ""; // Cambia la contraseña

    // Método para obtener una instancia de conexión a la base de datos
    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Cargar el controlador JDBC
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establecer la conexión a la base de datos
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Método para cerrar la conexión a la base de datos
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
