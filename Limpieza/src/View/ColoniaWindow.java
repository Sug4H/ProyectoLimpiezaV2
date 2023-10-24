/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Erick H
 */



import Controller.ColoniaController;
import Model.Colonia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ColoniaWindow {
    private JFrame frame;
    private ColoniaController coloniaController;

    private JTextField codigoPostalField;
    private JTextField nombreField;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JList<Colonia> coloniaList;
    private DefaultListModel<Colonia> coloniaListModel;

    public ColoniaWindow(ColoniaController coloniaController) {
        this.coloniaController = coloniaController;
        frame = new JFrame("Gestión de Colonias");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));

        JLabel codigoPostalLabel = new JLabel("Código Postal:");
        codigoPostalField = new JTextField();

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();

        agregarButton = new JButton("Agregar");
        actualizarButton = new JButton("Actualizar");
        eliminarButton = new JButton("Eliminar");

        inputPanel.add(codigoPostalLabel);
        inputPanel.add(codigoPostalField);
        inputPanel.add(nombreLabel);
        inputPanel.add(nombreField);
        inputPanel.add(new JLabel(""));
        inputPanel.add(agregarButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        coloniaListModel = new DefaultListModel<>();
        coloniaList = new JList<>(coloniaListModel);
        JScrollPane scrollPane = new JScrollPane(coloniaList);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualizarListaColonias();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarColonia();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarColonia();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarColonia();
            }
        });

        coloniaList.addListSelectionListener(e -> actualizarCampos());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(agregarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void actualizarListaColonias() {
        List<Colonia> colonias = coloniaController.obtenerTodasLasColonias();
        coloniaListModel.clear();
        for (Colonia colonia : colonias) {
            coloniaListModel.addElement(colonia);
        }
    }

    private void agregarColonia() {
        String codigoPostal = codigoPostalField.getText();
        String nombre = nombreField.getText();
        Colonia colonia = new Colonia(0, codigoPostal, nombre);
        coloniaController.agregarColonia(colonia);
        actualizarListaColonias();
        limpiarCampos();
    }

    private void actualizarColonia() {
        int selectedIndex = coloniaList.getSelectedIndex();
        if (selectedIndex != -1) {
            Colonia colonia = coloniaListModel.getElementAt(selectedIndex);
            String codigoPostal = codigoPostalField.getText();
            String nombre = nombreField.getText();
            colonia.setCodigoPostal(codigoPostal);
            colonia.setNombre(nombre);
            coloniaController.actualizarColonia(colonia);
            actualizarListaColonias();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona una colonia para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarColonia() {
        int selectedIndex = coloniaList.getSelectedIndex();
        if (selectedIndex != -1) {
            Colonia colonia = coloniaListModel.getElementAt(selectedIndex);
            coloniaController.eliminarColonia(colonia.getId());
            actualizarListaColonias();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona una colonia para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCampos() {
        int selectedIndex = coloniaList.getSelectedIndex();
        if (selectedIndex != -1) {
            Colonia colonia = coloniaListModel.getElementAt(selectedIndex);
            codigoPostalField.setText(colonia.getCodigoPostal());
            nombreField.setText(colonia.getNombre());
        }
    }

    private void limpiarCampos() {
        codigoPostalField.setText("");
        nombreField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ColoniaController coloniaController = new ColoniaController();
            new ColoniaWindow(coloniaController);
        });
    }
}
