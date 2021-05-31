package com.miguelbra.pooplife.objetos;

public class Vehiculo {
    int id, velocidad, precio;
    String nombre;

    public Vehiculo(int id, String nombre, int velocidad) {
        this.id = id;
        this.nombre = nombre;
        this.velocidad = velocidad;
    }

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

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
