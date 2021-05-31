package com.miguelbra.pooplife.objetos;

public class Comida {
    int id, cantidad, comida_regenera, precio;
    String nombre;

    public Comida(int id, String nombre, int comida_regenera) {
        this.id = id;
        this.nombre = nombre;
        this.comida_regenera = comida_regenera;
    }

    public int getId() {
        return id;
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

    public int getComida_regenera() {
        return comida_regenera;
    }

    public void setComida_regenera(int comida_regenera) {
        this.comida_regenera = comida_regenera;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
