/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Erick H
 */

import java.util.Date;

public class ActividadLimpieza {
    private int id; // Identificador único de la actividad
    private Date fecha; // Fecha de la actividad
    private String hora; // Hora de la actividad
    private String descripcion; // Descripción de la actividad
    private String evidenciaImagen; // Ruta de la imagen de evidencia
    private int id_colonia; // ID de la colonia relacionada
    private int id_jefeCuadrilla; // ID del jefe de cuadrilla asignado

    // Constructor
    public ActividadLimpieza(int id, Date fecha, String hora, String descripcion, String evidenciaImagen, int id_colonia, int id_jefeCuadrilla) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.evidenciaImagen = evidenciaImagen;
        this.id_colonia = id_colonia;
        this.id_jefeCuadrilla = id_jefeCuadrilla;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEvidenciaImagen() {
        return evidenciaImagen;
    }

    public void setEvidenciaImagen(String evidenciaImagen) {
        this.evidenciaImagen = evidenciaImagen;
    }

    public int getId_colonia() {
        return id_colonia;
    }

    public void setId_colonia(int id_colonia) {
        this.id_colonia = id_colonia;
    }

    public int getId_jefeCuadrilla() {
        return id_jefeCuadrilla;
    }

    public void setId_jefeCuadrilla(int id_jefeCuadrilla) {
        this.id_jefeCuadrilla = id_jefeCuadrilla;
    }


    @Override
    public String toString() {
        return "ActividadLimpieza{" +
               "id=" + id +
               ", fecha=" + fecha +
               ", hora='" + hora + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", evidenciaImagen='" + evidenciaImagen + '\'' +
               ", id_colonia=" + id_colonia +
               ", id_jefeCuadrilla=" + id_jefeCuadrilla +
               '}';
    }
}
