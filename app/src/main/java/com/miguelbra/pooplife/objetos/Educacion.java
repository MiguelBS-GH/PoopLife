package com.miguelbra.pooplife.objetos;

public class Educacion {

     int id, precio, comida_consume, nivel;
     String nombre, tipo;
     boolean estudiado, disponible;

    public Educacion(int id, String nombre, String tipo, int nivel, boolean estudiado, int precio, int comida_consume, boolean disponible) {
        this.id = id;
        this.precio = precio;
        this.nombre = nombre;
        this.tipo = tipo;
        this.nivel = nivel;
        this.estudiado = estudiado;
        this.comida_consume = comida_consume;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean isEstudiado() {
        return estudiado;
    }

    public void setEstudiado(boolean estudiado) {
        this.estudiado = estudiado;
    }

    public int getComida_consume() {
        return comida_consume;
    }

    public void setComida_consume(int comida_consume) {
        this.comida_consume = comida_consume;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
