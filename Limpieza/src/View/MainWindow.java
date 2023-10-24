/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Erick H
 */


import Controller.UsuarioController;
import Model.Usuario;
import Controller.ColoniaController;
import javax.swing.*;
import java.awt.*;
import Controller.JefeCuadrillaController;
import Controller.ActividadLimpiezaController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {
    private JFrame frame;
    private UsuarioController usuarioController;
    private Usuario usuario;
    private ColoniaController coloniaController;
    private JefeCuadrillaController jefeCuadrillaController;
    private ActividadLimpiezaController actividadLimpiezaController;

    public MainWindow(UsuarioController usuarioController, Usuario usuario, ColoniaController coloniaController, JefeCuadrillaController jefeCuadrillaController, ActividadLimpiezaController actividadLimpiezaController) {
        this.usuarioController = usuarioController;
        this.usuario = usuario;
        this.coloniaController = coloniaController;
        this.actividadLimpiezaController = actividadLimpiezaController;
        this.jefeCuadrillaController = jefeCuadrillaController;
        frame = new JFrame("Ventana Principal");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Crear un JLabel para mostrar el nombre del usuario logueado
        JLabel usuarioLabel = new JLabel("Usuario: " + usuario.getUsername());
        frame.add(usuarioLabel, BorderLayout.NORTH);

        // Crear botones para ir a las otras ventanas
        JButton coloniaButton = new JButton("Gestión de Colonias");
        JButton jefeCuadrillaButton = new JButton("Gestión de Jefes de Cuadrilla");
        JButton actividadLimpiezaButton = new JButton("Gestión de Actividades de Limpieza");

        // Agregar acciones a los botones para abrir las otras ventanas
        coloniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana de gestión de colonias
                new ColoniaWindow(coloniaController);
            }
        });

        jefeCuadrillaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana de gestión de jefes de cuadrilla
                new JefeCuadrillaWindow(jefeCuadrillaController);
            }
        });

        actividadLimpiezaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes abrir la ventana de gestión de actividades de limpieza
                new ActividadLimpiezaWindow(actividadLimpiezaController, jefeCuadrillaController, coloniaController);
            }
        });

        // Botón para cerrar sesión
        JButton cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra todas las ventanas abiertas
                for (Window ventana : Window.getWindows()) {
                    ventana.dispose();
                }
                frame.dispose();
                // Puedes redirigir a la ventana de inicio de sesión si lo deseas.
            }
        });

        // Agregar botones a la ventana
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(coloniaButton);
        buttonPanel.add(jefeCuadrillaButton);
        buttonPanel.add(actividadLimpiezaButton);
        buttonPanel.add(cerrarSesionButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioController usuarioController = new UsuarioController();
            Usuario usuario = usuarioController.getUsuarioActual();
            ColoniaController coloniaController = new ColoniaController();
            JefeCuadrillaController jefeCuadrillaController = new JefeCuadrillaController();
            ActividadLimpiezaController actividadLimpiezaController = new ActividadLimpiezaController();
            new MainWindow(usuarioController, usuario, coloniaController, jefeCuadrillaController, actividadLimpiezaController);
        });
    }
}
