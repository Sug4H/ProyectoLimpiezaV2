/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Erick H
 */


public class Colonia {
    private int id; // Identificador único de la colonia
    private String codigoPostal; // Código postal de la colonia
    private String nombre; // Nombre de la colonia

    // Constructor
    public Colonia(int id, String codigoPostal, String nombre) {
        this.id = id;
        this.codigoPostal = codigoPostal;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    @Override
    public String toString() {
        return "Colonia{" +
               "id=" + id +
               ", codigoPostal='" + codigoPostal + '\'' +
               ", nombre='" + nombre + '\'' +
               '}';
    }
}
