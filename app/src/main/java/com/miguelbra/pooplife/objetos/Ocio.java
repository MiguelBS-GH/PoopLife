package com.miguelbra.pooplife.objetos;

public class Ocio {
    int id, cantidad, estado_regenera, comida_consume, precio;
    String nombre;
    double prob_romperse;

    // Contructor para inventario
    public Ocio(int id, String nombre, int cantidad, int estado_regenera, int comida_consume, double prob_romperse) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.estado_regenera = estado_regenera;
        this.comida_consume = comida_consume;
        this.prob_romperse = prob_romperse;
    }

    // Contructor para tienda
    public Ocio(int id, String nombre, int precio){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        this.nombre = name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getEstado_regenera() {
        return estado_regenera;
    }

    public void setEstado_regenera(int estado_regenera) {
        this.estado_regenera = estado_regenera;
    }

    public int getComida_consume() {
        return comida_consume;
    }

    public void setComida_consume(int comida_consume) {
        this.comida_consume = comida_consume;
    }

    public double getProb_romperse() {
        return prob_romperse;
    }

    public void setProb_romperse(double prob_romperse) {
        this.prob_romperse = prob_romperse;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
