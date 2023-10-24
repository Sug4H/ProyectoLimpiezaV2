/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Erick H
 */

import Database.ColoniaDAO;
import Model.Colonia;
import java.util.List;

public class ColoniaController {
    private ColoniaDAO coloniaDAO;

    public ColoniaController() {
        coloniaDAO = new ColoniaDAO();
    }

    // Método para agregar una nueva colonia
    public void agregarColonia(Colonia colonia) {
        coloniaDAO.insertarColonia(colonia);
    }

    // Método para actualizar una colonia existente
    public void actualizarColonia(Colonia colonia) {
        coloniaDAO.actualizarColonia(colonia);
    }

    // Método para eliminar una colonia
    public void eliminarColonia(int coloniaId) {
        coloniaDAO.eliminarColonia(coloniaId);
    }

    // Método para obtener todas las colonias
    public List<Colonia> obtenerTodasLasColonias() {
        return coloniaDAO.obtenerTodasLasColonias();
    }

    // Otros métodos de lógica de negocio según las necesidades de tu aplicación
}
