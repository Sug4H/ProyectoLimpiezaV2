/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Erick H
 */

import Database.JefeCuadrillaDAO;
import Model.JefeCuadrilla;
import java.util.List;

public class JefeCuadrillaController {
    private JefeCuadrillaDAO jefeCuadrillaDAO;

    public JefeCuadrillaController() {
        jefeCuadrillaDAO = new JefeCuadrillaDAO();
    }

    // Método para agregar un nuevo jefe de cuadrilla
    public void agregarJefeCuadrilla(JefeCuadrilla jefe) {
        jefeCuadrillaDAO.insertarJefeCuadrilla(jefe);
    }

    // Método para actualizar un jefe de cuadrilla existente
    public void actualizarJefeCuadrilla(JefeCuadrilla jefe) {
        jefeCuadrillaDAO.actualizarJefeCuadrilla(jefe);
    }

    // Método para eliminar un jefe de cuadrilla
    public void eliminarJefeCuadrilla(int jefeId) {
        jefeCuadrillaDAO.eliminarJefeCuadrilla(jefeId);
    }

    // Método para obtener todos los jefes de cuadrilla
    public List<JefeCuadrilla> obtenerTodosLosJefes() {
        return jefeCuadrillaDAO.obtenerTodosLosJefes();
    }

    // Otros métodos de lógica de negocio según las necesidades de tu aplicación
}
