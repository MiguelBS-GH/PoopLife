package com.miguelbra.pooplife.objetos;

public class Trabajo {

    int id, sueldo, educacion;
    String nombre, tipo_trabajo, empresa;

    public Trabajo(int id, String nombre, String empresa, int sueldo, int educacion, String tipo_trabajo) {
        this.id = id;
        this.sueldo = sueldo;
        this.nombre = nombre;
        this.tipo_trabajo = tipo_trabajo;
        this.empresa = empresa;
        this.educacion = educacion;
    }

    public int getId() {
        return id;
    }

    public int getSueldo() {
        return sueldo;
    }

    public void setSueldo(int sueldo) {
        this.sueldo = sueldo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_trabajo() {
        return tipo_trabajo;
    }

    public void setTipo_trabajo(String tipo_trabajo) {
        this.tipo_trabajo = tipo_trabajo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getEducacion() {
        return educacion;
    }

    public void setEducacion(int educacion) {
        this.educacion = educacion;
    }
}
