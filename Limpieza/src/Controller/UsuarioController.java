/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author Erick H
 */

import Database.UsuarioDAO;
import Model.Usuario;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioActual;  // Agrega una propiedad para almacenar el usuario actual

    public UsuarioController() {
        usuarioDAO = new UsuarioDAO();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarioDAO.insertarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public void eliminarUsuario(int usuarioId) {
        usuarioDAO.eliminarUsuario(usuarioId);
    }

    public boolean autenticarUsuario(String username, String password) {
        Usuario usuario = usuarioDAO.obtenerUsuarioPorUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            usuarioActual = usuario;  // Establece el usuario actual después de una autenticación exitosa
            return true;
        }
        return false;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
