package com.miguelbra.pooplife.objetos;

public class Personaje {

    int id;
    String nombre;
    int salud;
    int dinero;
    int comida;
    int estado_animo;
    int prob_enfermedad;
    int id_trabajo;
    int matematicas;
    int letras;
    int agricola;
    int informatica;

    public Personaje(int id, String nombre, int salud, int dinero, int comida, int estado_animo, int prob_enfermedad, int id_trabajo, int matematicas, int letras, int agricola, int informatica) {
        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.dinero = dinero;
        this.comida = comida;
        this.estado_animo = estado_animo;
        this.prob_enfermedad = prob_enfermedad;
        this.id_trabajo = id_trabajo;
        this.matematicas = matematicas;
        this.letras = letras;
        this.agricola = agricola;
        this.informatica = informatica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public int getComida() {
        return comida;
    }

    public void setComida(int comida) {
        this.comida = comida;
    }

    public int getEstado_animo() {
        return estado_animo;
    }

    public void setEstado_animo(int estado_animo) {
        this.estado_animo = estado_animo;
    }

    public int getProb_enfermedad() {
        return prob_enfermedad;
    }

    public void setProb_enfermedad(int prob_enfermedad) {
        this.prob_enfermedad = prob_enfermedad;
    }

    public int getId_trabajo() {
        return id_trabajo;
    }

    public void setId_trabajo(int id_trabajo) {
        this.id_trabajo = id_trabajo;
    }

    public int getMatematicas() {
        return matematicas;
    }

    public void setMatematicas(int matematicas) {
        this.matematicas = matematicas;
    }

    public int getLetras() {
        return letras;
    }

    public void setLetras(int letras) {
        this.letras = letras;
    }

    public int getAgricola() {
        return agricola;
    }

    public void setAgricola(int agricola) {
        this.agricola = agricola;
    }

    public int getInformatica() {
        return informatica;
    }

    public void setInformatica(int informatica) {
        this.informatica = informatica;
    }
}
