/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

/**
 *
 * @author Erick H
 */




import Model.ActividadLimpieza;
import Model.Colonia;
import Model.JefeCuadrilla;
import Controller.ActividadLimpiezaController;
import Controller.ColoniaController;
import Controller.JefeCuadrillaController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ActividadLimpiezaWindow {
    private JFrame frame;
    private ActividadLimpiezaController actividadLimpiezaController;
    private JefeCuadrillaController jefeCuadrillaController;
    private ColoniaController coloniaController;
    private JTextField fechaField;
    private JTextField horaField;
    private JTextArea descripcionArea;
    private JTextField evidenciaImagenField;
    private JComboBox<Colonia> coloniaComboBox;
    private JComboBox<JefeCuadrilla> jefeCuadrillaComboBox;
    private JButton agregarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JList<ActividadLimpieza> actividadList;
    private DefaultListModel<ActividadLimpieza> actividadListModel;

    public ActividadLimpiezaWindow(ActividadLimpiezaController actividadLimpiezaController, JefeCuadrillaController jefeCuadrillaController, ColoniaController coloniaController) {
        this.actividadLimpiezaController = actividadLimpiezaController;
        this.jefeCuadrillaController = jefeCuadrillaController;
        this.coloniaController = coloniaController;
        frame = new JFrame("Gestión de Actividades de Limpieza");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel fechaHoraPanel = new JPanel();
        fechaHoraPanel.setLayout(new FlowLayout());
        JLabel fechaLabel = new JLabel("Fecha:");
        fechaField = new JTextField();
        fechaField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        fechaField.setEditable(false);
        fechaHoraPanel.add(fechaLabel);
        fechaHoraPanel.add(fechaField);

        JLabel horaLabel = new JLabel("Hora:");
        horaField = new JTextField();
        horaField.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        fechaHoraPanel.add(horaLabel);
        fechaHoraPanel.add(horaField);

        JPanel descripcionPanel = new JPanel();
        descripcionPanel.setLayout(new FlowLayout());
        JLabel descripcionLabel = new JLabel("Descripción:");
        descripcionArea = new JTextArea(3, 20);
        JScrollPane descripcionScrollPane = new JScrollPane(descripcionArea);
        descripcionPanel.add(descripcionLabel);
        descripcionPanel.add(descripcionScrollPane);

        JPanel evidenciaImagenPanel = new JPanel();
        evidenciaImagenPanel.setLayout(new FlowLayout());
        JLabel evidenciaImagenLabel = new JLabel("Evidencia Imagen:");
        evidenciaImagenField = new JTextField(15);
        JButton seleccionarImagenButton = new JButton("Seleccionar Imagen");
        evidenciaImagenPanel.add(evidenciaImagenLabel);
        evidenciaImagenPanel.add(evidenciaImagenField);
        evidenciaImagenPanel.add(seleccionarImagenButton);

        JPanel coloniaPanel = new JPanel();
        coloniaPanel.setLayout(new FlowLayout());
        JLabel coloniaLabel = new JLabel("Colonia:");
        coloniaComboBox = new JComboBox<>();
        cargarColonias();
        coloniaPanel.add(coloniaLabel);
        coloniaPanel.add(coloniaComboBox);

        JPanel jefeCuadrillaPanel = new JPanel();
        jefeCuadrillaPanel.setLayout(new FlowLayout());
        JLabel jefeCuadrillaLabel = new JLabel("Jefe de Cuadrilla:");
        jefeCuadrillaComboBox = new JComboBox<>();
        cargarJefesCuadrilla();
        jefeCuadrillaPanel.add(jefeCuadrillaLabel);
        jefeCuadrillaPanel.add(jefeCuadrillaComboBox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        agregarButton = new JButton("Agregar");
        actualizarButton = new JButton("Actualizar");
        eliminarButton = new JButton("Eliminar");
        buttonPanel.add(agregarButton);
        buttonPanel.add(actualizarButton);
        buttonPanel.add(eliminarButton);

        inputPanel.add(fechaHoraPanel);
        inputPanel.add(descripcionPanel);
        inputPanel.add(evidenciaImagenPanel);
        inputPanel.add(coloniaPanel);
        inputPanel.add(jefeCuadrillaPanel);
        inputPanel.add(buttonPanel);

        frame.add(inputPanel, BorderLayout.NORTH);

        actividadListModel = new DefaultListModel<>();
        actividadList = new JList<>(actividadListModel);
        JScrollPane scrollPane = new JScrollPane(actividadList);
        frame.add(scrollPane, BorderLayout.CENTER);

        actualizarListaActividades();

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarActividadLimpieza();
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarActividadLimpieza();
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarActividadLimpieza();
            }
        });

        seleccionarImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
                    evidenciaImagenField.setText(imagePath);
                }
            }
        });

        frame.setVisible(true);
    }

    private void cargarColonias() {
        List<Colonia> colonias = coloniaController.obtenerTodasLasColonias();
        coloniaComboBox.removeAllItems();
        for (Colonia colonia : colonias) {
            coloniaComboBox.addItem(colonia);
        }
    }

    private void cargarJefesCuadrilla() {
        List<JefeCuadrilla> jefesCuadrilla = jefeCuadrillaController.obtenerTodosLosJefes();
        jefeCuadrillaComboBox.removeAllItems();
        for (JefeCuadrilla jefe : jefesCuadrilla) {
            jefeCuadrillaComboBox.addItem(jefe);
        }
    }

    private void actualizarListaActividades() {
        List<ActividadLimpieza> actividades = actividadLimpiezaController.obtenerTodasLasActividades();
        actividadListModel.clear();
        for (ActividadLimpieza actividad : actividades) {
            actividadListModel.addElement(actividad);
        }
    }

    private void agregarActividadLimpieza() {
        String fechaString = fechaField.getText();
        String horaString = horaField.getText();
        String descripcion = descripcionArea.getText();
        String evidenciaImagen = evidenciaImagenField.getText();
        Colonia colonia = (Colonia) coloniaComboBox.getSelectedItem();
        JefeCuadrilla jefeCuadrilla = (JefeCuadrilla) jefeCuadrillaComboBox.getSelectedItem();

        if (colonia == null || jefeCuadrilla == null) {
            JOptionPane.showMessageDialog(frame, "Selecciona una colonia y un jefe de cuadrilla válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        try {
            Date fecha = dateFormat.parse(fechaString);
            Date hora = timeFormat.parse(horaString);

            ActividadLimpieza actividad = new ActividadLimpieza(0, fecha, horaString, descripcion, evidenciaImagen, colonia.getId(), jefeCuadrilla.getId());
            actividadLimpiezaController.agregarActividad(actividad);
            actualizarListaActividades();
            limpiarCampos();
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(frame, "Error al convertir fecha u hora.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarActividadLimpieza() {
        int selectedIndex = actividadList.getSelectedIndex();
        if (selectedIndex != -1) {
            ActividadLimpieza actividad = actividadListModel.getElementAt(selectedIndex);

            String fechaString = fechaField.getText();
            String horaString = horaField.getText();
            String descripcion = descripcionArea.getText();
            String evidenciaImagen = evidenciaImagenField.getText();
            Colonia colonia = (Colonia) coloniaComboBox.getSelectedItem();
            JefeCuadrilla jefeCuadrilla = (JefeCuadrilla) jefeCuadrillaComboBox.getSelectedItem();

            if (colonia == null || jefeCuadrilla == null) {
                JOptionPane.showMessageDialog(frame, "Selecciona una colonia y un jefe de cuadrilla válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

            try {
                Date fecha = dateFormat.parse(fechaString);
                Date hora = timeFormat.parse(horaString);

                actividad.setFecha(fecha);
                actividad.setHora(horaString);
                actividad.setDescripcion(descripcion);
                actividad.setEvidenciaImagen(evidenciaImagen);
                actividad.setId_colonia(colonia.getId());
                actividad.setId_jefeCuadrilla(jefeCuadrilla.getId());

                actividadLimpiezaController.actualizarActividad(actividad);
                actualizarListaActividades();
                limpiarCampos();
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(frame, "Error al convertir fecha u hora.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona una actividad de limpieza para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarActividadLimpieza() {
        int selectedIndex = actividadList.getSelectedIndex();
        if (selectedIndex != -1) {
            ActividadLimpieza actividad = actividadListModel.getElementAt(selectedIndex);
            actividadLimpiezaController.eliminarActividad(actividad.getId());
            actualizarListaActividades();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(frame, "Selecciona una actividad de limpieza para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarCampos() {
        int selectedIndex = actividadList.getSelectedIndex();
        if (selectedIndex != -1) {
            ActividadLimpieza actividad = actividadListModel.getElementAt(selectedIndex);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            fechaField.setText(dateFormat.format(actividad.getFecha()));
            horaField.setText(timeFormat.format(actividad.getHora()));
            descripcionArea.setText(actividad.getDescripcion());
            evidenciaImagenField.setText(actividad.getEvidenciaImagen());

            for (int i = 0; i < coloniaComboBox.getItemCount(); i++) {
                if (coloniaComboBox.getItemAt(i).getId() == actividad.getId_colonia()) {
                    coloniaComboBox.setSelectedIndex(i);
                    break;
                }
            }

            for (int i = 0; i < jefeCuadrillaComboBox.getItemCount(); i++) {
                if (jefeCuadrillaComboBox.getItemAt(i).getId() == actividad.getId_jefeCuadrilla()) {
                    jefeCuadrillaComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void limpiarCampos() {
        fechaField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        horaField.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        descripcionArea.setText("");
        evidenciaImagenField.setText("");
        coloniaComboBox.setSelectedIndex(-1);
        jefeCuadrillaComboBox.setSelectedIndex(-1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ColoniaController coloniaController = new ColoniaController();
            JefeCuadrillaController jefeCuadrillaController = new JefeCuadrillaController();
            ActividadLimpiezaController actividadLimpiezaController = new ActividadLimpiezaController();

            new ActividadLimpiezaWindow(actividadLimpiezaController, jefeCuadrillaController, coloniaController);
        });
    }
}
