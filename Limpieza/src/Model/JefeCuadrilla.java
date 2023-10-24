/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Erick H
 */

public class JefeCuadrilla {
    private int id; // Identificador único del jefe de cuadrilla
    private String nombre; // Nombre del jefe de cuadrilla

    // Constructor
    public JefeCuadrilla(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public String toString() {
        return "JefeCuadrilla{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}
