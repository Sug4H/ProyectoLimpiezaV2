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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Controller.JefeCuadrillaController;
import Controller.ActividadLimpiezaController;

public class LoginWindow {
    private JFrame frame;
    private UsuarioController usuarioController;
    private ColoniaController coloniaController;
    private JefeCuadrillaController jefeCuadrillaController;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private ActividadLimpiezaController actividadLimpiezaController;
    private JButton closeButton;

    public LoginWindow(UsuarioController usuarioController, ColoniaController coloniaController, JefeCuadrillaController jefeCuadrillaController, ActividadLimpiezaController actividadLimpiezaController) {
        this.coloniaController = coloniaController;
        this.usuarioController = usuarioController;
        this.jefeCuadrillaController = jefeCuadrillaController; 
        this.actividadLimpiezaController = actividadLimpiezaController;
        frame = new JFrame("Iniciar Sesión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Usuario:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();

        loginButton = new JButton("Iniciar Sesión");
        closeButton = new JButton("Cerrar");

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
           loginPanel.add(new JLabel(""));  // Espacio en blanco
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel(""));  // Espacio en blanco
        loginPanel.add(loginButton);
        loginPanel.add(new JLabel(""));  // Espacio en blanco
        loginPanel.add(closeButton);

        frame.add(loginPanel, BorderLayout.CENTER);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean autenticado = usuarioController.autenticarUsuario(username, password);
                if (autenticado) {
                    openMainWindow(usuarioController.getUsuarioActual());  // Pasa el usuario actual a la ventana principal
                } else {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión fallido. Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
 
           closeButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // Cierra la ventana de inicio de sesión
        }
    });
        frame.setVisible(true);
    }

    private void openMainWindow(Usuario usuario) {
        frame.setVisible(false);
        SwingUtilities.invokeLater(() -> {
            new MainWindow(usuarioController, usuario, coloniaController, jefeCuadrillaController, actividadLimpiezaController);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UsuarioController usuarioController = new UsuarioController();
            ColoniaController coloniaController = new ColoniaController();
            JefeCuadrillaController jefeCuadrillaController = new JefeCuadrillaController();
            ActividadLimpiezaController actividadLimpiezaController = new ActividadLimpiezaController();
            new LoginWindow(usuarioController, coloniaController, jefeCuadrillaController, actividadLimpiezaController);
        });
    }
}
