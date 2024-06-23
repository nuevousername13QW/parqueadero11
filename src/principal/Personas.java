/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package principal;

import java.math.BigInteger;

/**
 *
 * @author Trabajo
 */
public class Personas {
    private BigInteger id;
    private String Nombre;
    private BigInteger Telefono;

    // Getters y Setters

    public BigInteger getid() {
        return id;
    }

    public void setid(BigInteger id) {
        this.id = id;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public BigInteger getTelefono() {
        return Telefono;
    }

    public void setTelefono(BigInteger Telefono) {
        this.Telefono = Telefono;
    }
}
