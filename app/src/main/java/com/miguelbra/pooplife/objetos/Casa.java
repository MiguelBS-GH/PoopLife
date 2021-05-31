package com.miguelbra.pooplife.objetos;

public class Casa {

    int id, tamaño, precio;
    String direccion;

    public Casa(int id, String direccion, int tamaño) {
        this.id = id;
        this.direccion = direccion;
        this.tamaño = tamaño;
    }
    public Casa(int id, int precio, String direccion) {
        this.id = id;
        this.direccion = direccion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
