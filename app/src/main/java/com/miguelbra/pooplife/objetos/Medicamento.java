package com.miguelbra.pooplife.objetos;

public class Medicamento {
    int id;
    String nombre;
    int cantidad;
    int salud_recuperada;
    int precio;

    public Medicamento(int id, String nombre, int salud_recuperada) {
        this.id = id;
        this.nombre = nombre;
        this.salud_recuperada = salud_recuperada;
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getSalud_recuperada() {
        return salud_recuperada;
    }

    public void setSalud_recuperada(int salud_recuperada) {
        this.salud_recuperada = salud_recuperada;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
