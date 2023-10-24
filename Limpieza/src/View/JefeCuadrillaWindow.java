/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Erick H
 */

import Controller.JefeCuadrillaController;
import Model.JefeCuadrilla;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class JefeCuadrillaWindow {
    private JFrame frame;
    private JefeCuadrillaController jefeCuadrillaController;

    private JTextField nombreField;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JList<JefeCuadrilla> jefeCuadrillaList;
    private DefaultListModel<JefeCuadrilla> jefeCuadrillaListModel;

    public JefeCuadrillaWindow(JefeCuadrillaController jefeCuadrillaController) {
        this.jefeCuadrillaController = jefeCuadrillaController;
        frame = new JFrame("Gestión de Jefes de Cuadrilla");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

        agregarButton = new JButton("Agregar");
        actualizarButton = new JButton("Actualizar");
        eliminarButton = new JButton("Eliminar");

        inputPanel.add(nombreLabel);
        inputPanel.add(nombreField);
        inputPanel.add(agregarButton);
        inputPanel.add(actualizarButton);
        inputPanel.add(eliminarButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        jefeCuadrillaListModel = new DefaultListModel<>();
        jefeCuadrillaList = new JList<>(jefeCuadrillaListModel);
        JScrollPane scrollPane = new JScrollPane(jefeCuadrillaList);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualizarListaJefesCuadrilla();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarJefeCuadrilla();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarJefeCuadrilla();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarJefeCuadrilla();
            }
        });

        jefeCuadrillaList.addListSelectionListener(e -> actualizarCampos());

        frame.setVisible(true);
    }

    private void actualizarListaJefesCuadrilla() {
        List<JefeCuadrilla> jefesCuadrilla = jefeCuadrillaController.obtenerTodosLosJefes();
        jefeCuadrillaListModel.clear();
        for (JefeCuadrilla jefeCuadrilla : jefesCuadrilla) {
            jefeCuadrillaListModel.addElement(jefeCuadrilla);
        }
    }

    private void agregarJefeCuadrilla() {
        String nombre = nombreField.getText();
        JefeCuadrilla jefeCuadrilla = new JefeCuadrilla(0, nombre);
        jefeCuadrillaController.agregarJefeCuadrilla(jefeCuadrilla);
        actualizarListaJefesCuadrilla();
        limpiarCampos();
    }

    private void actualizarJefeCuadrilla() {
        int selectedIndex = jefeCuadrillaList.getSelectedIndex();
        if (selectedIndex != -1) {
            JefeCuadrilla jefeCuadrilla = jefeCuadrillaListModel.getElementAt(selectedIndex);
            String nombre = nombreField.getText();
            jefeCuadrilla.setNombre(nombre);
            jefeCuadrillaController.actualizarJefeCuadrilla(jefeCuadrilla);
            actualizarListaJefesCuadrilla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona un jefe de cuadrilla para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarJefeCuadrilla() {
        int selectedIndex = jefeCuadrillaList.getSelectedIndex();
        if (selectedIndex != -1) {
            JefeCuadrilla jefeCuadrilla = jefeCuadrillaListModel.getElementAt(selectedIndex);
            jefeCuadrillaController.eliminarJefeCuadrilla(jefeCuadrilla.getId());
            actualizarListaJefesCuadrilla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona un jefe de cuadrilla para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCampos() {
        int selectedIndex = jefeCuadrillaList.getSelectedIndex();
        if (selectedIndex != -1) {
            JefeCuadrilla jefeCuadrilla = jefeCuadrillaListModel.getElementAt(selectedIndex);
            nombreField.setText(jefeCuadrilla.getNombre());
        }
    }

    private void limpiarCampos() {
        nombreField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JefeCuadrillaController jefeCuadrillaController = new JefeCuadrillaController();
            new JefeCuadrillaWindow(jefeCuadrillaController);
        });
    }
}
