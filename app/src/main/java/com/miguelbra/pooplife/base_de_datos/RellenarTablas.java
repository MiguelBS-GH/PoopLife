package com.miguelbra.pooplife.base_de_datos;

import android.database.sqlite.SQLiteDatabase;

public class RellenarTablas {

    public static void rellenar(SQLiteDatabase db){
        rellenarDatos( db );
        rellenarInventario( db );
    }

    public static void rellenarDatos(SQLiteDatabase db){
        rellenar_Comida( db );
        rellenar_Medicamento( db );
        rellenar_Ocio( db );
        rellenar_Vehiculo( db );
        rellenar_Casa( db );
        rellenar_Educacion( db );
        rellenar_Trabajo( db );
    }
    public static void rellenarInventario(SQLiteDatabase db){
        rellenarInventario_Comida( db );
        rellenarInventario_Medicamento( db );
        rellenarInventario_Ocio( db );
        rellenarInventario_Vehiculo( db );
        rellenarInventario_Casa( db );
        rellenarInventario_Educacion( db );
    }

    public static void rellenar_Comida(SQLiteDatabase db){
        Utilidades.insertarComida( db, 1, "Manzana", 10, 4 );
        Utilidades.insertarComida( db, 2, "Pera", 7, 3 );
        Utilidades.insertarComida( db, 3, "Platano", 12, 3 );
        Utilidades.insertarComida( db, 4, "Chuletón", 80, 20 );
        Utilidades.insertarComida( db, 5, "Paella", 75, 18 );
        Utilidades.insertarComida( db, 6, "Puré", 60, 14 );
        Utilidades.insertarComida( db, 7, "Filete", 40, 10 );
        Utilidades.insertarComida( db, 8, "Ensalada", 80, 20 );
        Utilidades.insertarComida( db, 9, "Patatas fritas", 18, 6 );
        Utilidades.insertarComida( db, 10, "Chuches", 5, 2 );
        Utilidades.insertarComida( db, 11, "Hamburguesa", 70, 15 );
        Utilidades.insertarComida( db, 12, "Sandwich", 35, 9 );
        Utilidades.insertarComida( db, 13, "Pan", 20, 6 );
        Utilidades.insertarComida( db, 14, "Alubias", 50, 16 );
        Utilidades.insertarComida( db, 15, "Tortilla", 50, 17 );
    }

    public static void rellenarInventario_Comida(SQLiteDatabase db){
        //rellenar_Comida( db );

        Utilidades.insertarComidaEnInventario( db, 1, 11);
        Utilidades.insertarComidaEnInventario( db, 2, 20);
        Utilidades.insertarComidaEnInventario( db, 3, 13);
        Utilidades.insertarComidaEnInventario( db, 4, 2);
        Utilidades.insertarComidaEnInventario( db, 5, 5);
        Utilidades.insertarComidaEnInventario( db, 6, 3);
        Utilidades.insertarComidaEnInventario( db, 7, 15);
        Utilidades.insertarComidaEnInventario( db, 8, 14);
        Utilidades.insertarComidaEnInventario( db, 9, 4);
        Utilidades.insertarComidaEnInventario( db, 10, 1);
        Utilidades.insertarComidaEnInventario( db, 11, 16);
        Utilidades.insertarComidaEnInventario( db, 12, 14);
        Utilidades.insertarComidaEnInventario( db, 13, 4);
        Utilidades.insertarComidaEnInventario( db, 14, 3);
        Utilidades.insertarComidaEnInventario( db, 15, 6);
    }

    public static void rellenar_Medicamento(SQLiteDatabase db){
        Utilidades.insertarMedicamento( db, 1, "Paracetamol", BaseDeDatos.Medicamento.TIPO_ENFERMEDAD, 90, 40, 60 );
        Utilidades.insertarMedicamento( db, 2, "Ibuprofeno", BaseDeDatos.Medicamento.TIPO_ENFERMEDAD, 95, 50, 60 );
        Utilidades.insertarMedicamento( db, 3, "Tirita", BaseDeDatos.Medicamento.TIPO_HERIDA, 85, 20, 30 );
        Utilidades.insertarMedicamento( db, 4, "Venda", BaseDeDatos.Medicamento.TIPO_HERIDA, 98, 35, 45 );
    }

    public static void rellenarInventario_Medicamento(SQLiteDatabase db){
        //rellenar_Medicamento( db );

        Utilidades.insertarMedicamentoEnInventario( db, 1, 100 );
        Utilidades.insertarMedicamentoEnInventario( db, 2, 100 );
        Utilidades.insertarMedicamentoEnInventario( db, 3, 100 );
        Utilidades.insertarMedicamentoEnInventario( db, 4, 100 );
    }

    public static void rellenar_Ocio(SQLiteDatabase db){
        Utilidades.insertarOcio( db, 1, "Ordenador", 0.1,900, 50, 5 );
        Utilidades.insertarOcio( db, 2, "Videoconsola", 0.4,400, 25, 10 );
        Utilidades.insertarOcio( db, 3, "Balón de futbol", 1,18, 40, 30 );
        Utilidades.insertarOcio( db, 4, "Balón de baloncesto", 1,18, 40, 30 );
        Utilidades.insertarOcio( db, 5, "Palas de Ping Pong", 0.8,25, 30, 20 );
        Utilidades.insertarOcio( db, 6, "Dardos y Diana", 0.6,100, 20, 15 );
        Utilidades.insertarOcio( db, 7, "Televisón", 0.2,450, 25, 5 );
        Utilidades.insertarOcio( db, 8, "Telescopio", 0.5,240, 20, 10 );
        Utilidades.insertarOcio( db, 9, "Novela de fantasia", 0.4,24, 20, 5 );
        Utilidades.insertarOcio( db, 10, "Novela de Ciencia Ficción", 0.4,24, 20, 5 );
        Utilidades.insertarOcio( db, 11, "Novela romántica", 0.4,24, 20, 5 );
        Utilidades.insertarOcio( db, 12, "Novela de misterio", 0.4,24, 20, 5 );
        Utilidades.insertarOcio( db, 13, "Parchís", 0.8,40, 25, 10 );
        Utilidades.insertarOcio( db, 14, "Guitarra", 0.4,200, 30, 15 );
        Utilidades.insertarOcio( db, 15, "Flauta", 0.4,60, 25, 15 );
        Utilidades.insertarOcio( db, 16, "Batería", 0.4,300, 30, 30 );
        Utilidades.insertarOcio( db, 17, "Piano", 0.3,500, 30, 15 );
    }

    public static void rellenarInventario_Ocio(SQLiteDatabase db){
        //rellenar_Ocio( db );

        Utilidades.insertarOcioEnInventario( db, 1, 100 );
        Utilidades.insertarOcioEnInventario( db, 2, 100 );
        Utilidades.insertarOcioEnInventario( db, 3, 100 );
        Utilidades.insertarOcioEnInventario( db, 4, 100 );
        Utilidades.insertarOcioEnInventario( db, 5, 100 );
        Utilidades.insertarOcioEnInventario( db, 6, 100 );
        Utilidades.insertarOcioEnInventario( db, 7, 100 );
        Utilidades.insertarOcioEnInventario( db, 8, 100 );
        Utilidades.insertarOcioEnInventario( db, 9, 100 );
        Utilidades.insertarOcioEnInventario( db, 10, 100 );
        Utilidades.insertarOcioEnInventario( db, 11, 100 );
        Utilidades.insertarOcioEnInventario( db, 12, 100 );
        Utilidades.insertarOcioEnInventario( db, 13, 100 );
        Utilidades.insertarOcioEnInventario( db, 14, 100 );
        Utilidades.insertarOcioEnInventario( db, 15, 100 );
        Utilidades.insertarOcioEnInventario( db, 16, 100 );
        Utilidades.insertarOcioEnInventario( db, 17, 100 );
    }

    public static void rellenar_Vehiculo(SQLiteDatabase db){
        Utilidades.insertarVehiculo( db, 1, "Pacia Pandero", 0.9, 10000, 90);
        Utilidades.insertarVehiculo( db, 2, "Panault Pio", 0.4, 13000, 100);
        Utilidades.insertarVehiculo( db, 3, "Panault Penic", 0.4, 13000, 105);
        Utilidades.insertarVehiculo( db, 4, "Pia Peed", 0.5, 18000, 120);
        Utilidades.insertarVehiculo( db, 5, "Pudi p3", 0.4, 33000, 135);
        Utilidades.insertarVehiculo( db, 6, "Paudi p8", 0.3, 150000, 185);
        Utilidades.insertarVehiculo( db, 7, "Percedes Serie P", 0.35, 55000, 150);
        Utilidades.insertarVehiculo( db, 8, "Poche PT3", 0.2, 200000, 180);
        Utilidades.insertarVehiculo( db, 9, "Pesla model P", 0.15, 80000, 170);
    }

    public static void rellenarInventario_Vehiculo (SQLiteDatabase db){
        //rellenar_Vehiculo( db );

        //Utilidades.insertarVehiculoEnInventario( db, 1);
        //Utilidades.insertarVehiculoEnInventario( db, 2);
        //Utilidades.insertarVehiculoEnInventario( db, 3);
        //Utilidades.insertarVehiculoEnInventario( db, 4);
        //Utilidades.insertarVehiculoEnInventario( db, 5);
        //Utilidades.insertarVehiculoEnInventario( db, 6);
        //Utilidades.insertarVehiculoEnInventario( db, 7);
        //Utilidades.insertarVehiculoEnInventario( db, 8);
        Utilidades.insertarVehiculoEnInventario( db, 9);
    }

    public static void rellenar_Casa (SQLiteDatabase db){
        Utilidades.insertarCasa( db, 1, "Apartamento pequeño", 50, 200, 80000, -0.5 );
        Utilidades.insertarCasa( db, 2, "Apartamento pequeño 2", 70, 250, 100000, -0.8 );
        Utilidades.insertarCasa( db, 3, "Apartamento mediano", 100, 650, 150000, -1 );
        Utilidades.insertarCasa( db, 4, "Apartamento mediano 2", 120, 900, 180000, -1.4 );
        Utilidades.insertarCasa( db, 5, "Casa 1 piso", 150, 1000, 210000, -2 );
        Utilidades.insertarCasa( db, 6, "Chalet", 200, 1500, 300000, -2.5 );
        Utilidades.insertarCasa( db, 7, "Palacete", 400, 3000, 500000, -5 );
        Utilidades.insertarCasa( db, 8, "Mansión", 10000, -1, 3000000, -10 );
    }

    public static void rellenarInventario_Casa (SQLiteDatabase db){
        //rellenar_Casa( db );

        Utilidades.insertarCasaEnInventario( db, 1 );
        Utilidades.insertarCasaEnInventario( db, 2 );
        Utilidades.insertarCasaEnInventario( db, 3 );
        Utilidades.insertarCasaEnInventario( db, 4 );
        Utilidades.insertarCasaEnInventario( db, 5 );
        Utilidades.insertarCasaEnInventario( db, 6 );
        Utilidades.insertarCasaEnInventario( db, 7 );
        Utilidades.insertarCasaEnInventario( db, 8 );
    }

    public static void rellenar_Educacion ( SQLiteDatabase db ) {
        Utilidades.insertarEducacion( db, 1, "Matemáticas I", BaseDeDatos.Educacion.MATEMATICAS, 1, 3000, 5 );
        Utilidades.insertarEducacion( db, 2, "Matemáticas II", BaseDeDatos.Educacion.MATEMATICAS, 2, 3500, 10 );
        Utilidades.insertarEducacion( db, 3, "Matemáticas III", BaseDeDatos.Educacion.MATEMATICAS, 3, 2000, 5 );
        Utilidades.insertarEducacion( db, 4, "Letras I", BaseDeDatos.Educacion.LETRAS, 1, 2200, 5 );
        Utilidades.insertarEducacion( db, 5, "Letras II", BaseDeDatos.Educacion.LETRAS, 2, 5000, 10 );
        Utilidades.insertarEducacion( db, 6, "Letras III", BaseDeDatos.Educacion.LETRAS, 3, 2400, 5 );
        Utilidades.insertarEducacion( db, 7, "Rural I", BaseDeDatos.Educacion.RURAL, 1, 2600, 5 );
        Utilidades.insertarEducacion( db, 8, "Rural II", BaseDeDatos.Educacion.RURAL, 2, 2900, 5 );
        Utilidades.insertarEducacion( db, 9, "Rural III", BaseDeDatos.Educacion.RURAL, 3, 2900, 5 );
        Utilidades.insertarEducacion( db, 10, "Informática I", BaseDeDatos.Educacion.INFORMÁTICA, 1, 2700, 5 );
        Utilidades.insertarEducacion( db, 11, "Informática II", BaseDeDatos.Educacion.INFORMÁTICA, 2, 2200, 5 );
        Utilidades.insertarEducacion( db, 12, "Informática III", BaseDeDatos.Educacion.INFORMÁTICA, 3, 2000, 10 );
    }

    public static void rellenarInventario_Educacion ( SQLiteDatabase db ) {
        Utilidades.insertarEducacionEnInventario( db, 3 );
        Utilidades.insertarEducacionEnInventario( db, 5 );
        Utilidades.insertarEducacionEnInventario( db, 6 );
        Utilidades.insertarEducacionEnInventario( db, 10 );
    }

    public static void rellenar_Trabajo ( SQLiteDatabase db ) {
        Utilidades.insertarTrabajo( db, 1, "Desempleado", "Ninguna", 0, 0, 0, 0 );
        Utilidades.insertarTrabajo( db, 2, "Pizzero", "Pizzeria", 0, 3, 5, 7 );
        Utilidades.insertarTrabajo( db, 3, "Repartidor", "Apazon", 0, 4, 6, 8 );
        Utilidades.insertarTrabajo( db, 4, "Desarrolador de aplicaciones", "AppEnture", 11, 5, 18, 6 );
        Utilidades.insertarTrabajo( db, 5, "Diseñador Web", "NinPepplerguna", 10, 5, 17, 7 );
        Utilidades.insertarTrabajo( db, 6, "Periodista", "CBA", 4, 6, 10, 8 );
        Utilidades.insertarTrabajo( db, 7, "Abogado", "APEM", 6, 5, 22, 10 );
        Utilidades.insertarTrabajo( db, 8, "Recolector de uvas", "Vega Sicilia", 7, 5, 9, 9 );
        Utilidades.insertarTrabajo( db, 9, "Consultor financiero", "G De Gestion", 2, 5, 13, 7 );
        Utilidades.insertarTrabajo( db, 10, "Contable", "EcoNova", 1, 5, 14, 7 );
    }

}
