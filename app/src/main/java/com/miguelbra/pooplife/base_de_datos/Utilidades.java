package com.miguelbra.pooplife.base_de_datos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Utilidades {

    public static void insertarPersonaje(SQLiteDatabase db, int id, String nombre, int salud, int dinero, int comida, int estado_animo, int prob_enfermedad, int id_trabajo, int id_vehiculo, int id_casa, int exp_trabajo, int nivel_trabajo, int matematicas, int letras, int rural, int informatica ){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Personaje.ID_PERSONAJE, id );
        cv.put( BaseDeDatos.Personaje.NOMBRE_PERSONAJE, nombre );
        cv.put( BaseDeDatos.Personaje.SALUD, salud );
        cv.put( BaseDeDatos.Personaje.COMIDA, comida);
        cv.put( BaseDeDatos.Personaje.ESTADO_ANIMO, estado_animo);
        cv.put( BaseDeDatos.Personaje.DINERO, dinero);
        cv.put( BaseDeDatos.Personaje.PROB_ENFERMEDAD, prob_enfermedad);
        cv.put( BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK, id_trabajo );
        cv.put( BaseDeDatos.Personaje.ID_VEHICULO_PERSONAJE_FK, id_vehiculo );
        cv.put( BaseDeDatos.Personaje.ID_CASA_PERSONAJE_FK, id_casa );
        cv.put( BaseDeDatos.Personaje.EXP_TRABAJO, exp_trabajo );
        cv.put( BaseDeDatos.Personaje.NIVEL_TRABAJO, nivel_trabajo );
        cv.put( BaseDeDatos.Personaje.MATEMATICAS_PERSONAJE, matematicas );
        cv.put( BaseDeDatos.Personaje.LETRAS_PERSONAJE, letras );
        cv.put( BaseDeDatos.Personaje.RURAL_PERSONAJE, rural );
        cv.put( BaseDeDatos.Personaje.INFORMATICA_PERSONAJE, informatica );
        db.insert( BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, null, cv );
    }

    public static void insertarComida(SQLiteDatabase db, int id_comida, String nombre_comida, int cantidad_regenera, int precio_medicamento){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Comida.ID_COMIDA, id_comida );
        cv.put( BaseDeDatos.Comida.NOMBRE_COMIDA, nombre_comida );
        cv.put( BaseDeDatos.Comida.COMIDA_REGENERA, cantidad_regenera);
        cv.put( BaseDeDatos.Comida.PRECIO_COMIDA, precio_medicamento);
        db.insert( BaseDeDatos.Comida.COMIDA_TABLE_NAME, null, cv );
    }

    public static void insertarComidaEnInventario(SQLiteDatabase db, int id_comida, int cantidad_comida){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Inv_Comida.ID_COMIDA_FK, id_comida );
        cv.put( BaseDeDatos.Inv_Comida.CANTIDAD_COMIDA, cantidad_comida );
        db.insert( BaseDeDatos.Inv_Comida.INV_COMIDA_TABLE_NAME, null, cv );
    }

    public static void insertarMedicamento(SQLiteDatabase db, int id_medicamento, String nombre_medicamento, String tipo_medicamento, int prob_cura,  int precio_medicamento, int vida_regenera){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Medicamento.ID_MEDICAMENTO, id_medicamento );
        cv.put( BaseDeDatos.Medicamento.NOMBRE_MEDICAMENTO, nombre_medicamento );
        cv.put( BaseDeDatos.Medicamento.TIPO_MEDICAMENTO, tipo_medicamento );
        cv.put( BaseDeDatos.Medicamento.PROB_CURA, prob_cura );
        cv.put( BaseDeDatos.Medicamento.VIDA_REGENERA, vida_regenera);
        cv.put( BaseDeDatos.Medicamento.PRECIO_MEDICAMENTO, precio_medicamento);
        db.insert( BaseDeDatos.Medicamento.MEDICAMENTO_TABLE_NAME, null, cv );
    }

    public static void insertarMedicamentoEnInventario(SQLiteDatabase db, int id_medicamento, int cantidad_medicamento){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Inv_Medicamento.ID_MEDICAMENTO_FK, id_medicamento );
        cv.put( BaseDeDatos.Inv_Medicamento.CANTIDAD_MEDICAMENTO, cantidad_medicamento );
        db.insert( BaseDeDatos.Inv_Medicamento.INV_MEDICAMENTO_TABLE_NAME, null, cv );
    }

    public static void insertarOcio(SQLiteDatabase db, int id, String nombre_ocio, double prob_roto,  int precio_ocio, int estado_regenera, int comida_consume_ocio){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Ocio.ID_OCIO, id );
        cv.put( BaseDeDatos.Ocio.NOMBRE_OCIO, nombre_ocio );
        cv.put( BaseDeDatos.Ocio.PROB_ROTO, prob_roto );
        cv.put( BaseDeDatos.Ocio.ESTADO_REGENERA, estado_regenera);
        cv.put( BaseDeDatos.Ocio.PRECIO_OCIO, precio_ocio);
        cv.put( BaseDeDatos.Ocio.COMIDA_CONSUME_OCIO, comida_consume_ocio );
        db.insert( BaseDeDatos.Ocio.OCIO_TABLE_NAME, null, cv );
    }

    public static void insertarOcioEnInventario(SQLiteDatabase db, int id_ocio, int cantidad_ocio){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Inv_Ocio.ID_OCIO_FK, id_ocio );
        cv.put( BaseDeDatos.Inv_Ocio.CANTIDAD_OCIO, cantidad_ocio );
        db.insert( BaseDeDatos.Inv_Ocio.INV_OCIO_TABLE_NAME, null, cv );
    }

    public static void insertarVehiculo(SQLiteDatabase db, int id_vehiculo, String nombre_vehiculo,  double prob_averia, int precio_vehiculo, int velocidad_vehiculo){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Vehiculo.ID_VEHICULO, id_vehiculo );
        cv.put( BaseDeDatos.Vehiculo.NOMBRE_VEHICULO, nombre_vehiculo );
        cv.put( BaseDeDatos.Vehiculo.PROB_AVERIA, prob_averia );
        cv.put( BaseDeDatos.Vehiculo.PRECIO_VEHICULO, precio_vehiculo);
        cv.put( BaseDeDatos.Vehiculo.VELOCIDAD_VEHICULO, velocidad_vehiculo);
        db.insert( BaseDeDatos.Vehiculo.VEHICULO_TABLE_NAME, null, cv );
    }

    public static void insertarVehiculoEnInventario(SQLiteDatabase db, int id_vehiculo){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Inv_Vehiculo.ID_VEHICULO_FK, id_vehiculo );
        db.insert( BaseDeDatos.Inv_Vehiculo.INV_VEHICULO_TABLE_NAME, null, cv );
    }

    public static void insertarCasa(SQLiteDatabase db, int id_casa, String direccion_casa, int tamaño_casa, int alquiler_casa, int precio_casa, double bonif_casa){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Casa.ID_CASA, id_casa );
        cv.put( BaseDeDatos.Casa.DIRECCION_CASA, direccion_casa );
        cv.put( BaseDeDatos.Casa.TAMAÑO_CASA, tamaño_casa );
        cv.put( BaseDeDatos.Casa.ALQUILER_CASA, alquiler_casa );
        cv.put( BaseDeDatos.Casa.PRECIO_CASA, precio_casa );
        cv.put( BaseDeDatos.Casa.BONIF_CASA, bonif_casa );
        db.insert( BaseDeDatos.Casa.CASA_TABLE_NAME, null, cv );
    }

    public static void insertarCasaEnInventario(SQLiteDatabase db, int id_casa){
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Inv_Casa.ID_CASA_FK, id_casa );
        db.insert( BaseDeDatos.Inv_Casa.INV_CASA_TABLE_NAME, null, cv );
    }

    public static void insertarEducacion( SQLiteDatabase db, int id_educacion, String nombre_educacion, String tipo_educacion, int nivel_educacion, int precio_educacion, int comida_consume_estudiar ) {
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Educacion.ID_EDUCACION, id_educacion );
        cv.put( BaseDeDatos.Educacion.NOMBRE_EDUCACION, nombre_educacion );
        cv.put( BaseDeDatos.Educacion.TIPO_EDUCACION, tipo_educacion );
        cv.put( BaseDeDatos.Educacion.NIVEL_EDUCACION, nivel_educacion );
        cv.put( BaseDeDatos.Educacion.PRECIO_EDUCACION, precio_educacion );
        cv.put( BaseDeDatos.Educacion.COMIDA_CONSUME_ESTUDIAR, comida_consume_estudiar );
        db.insert( BaseDeDatos.Educacion.EDUCACION_TABLE_NAME, null, cv );
    }

    public static void insertarEducacionEnInventario ( SQLiteDatabase db, int id_educacion ) {
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Inv_Educacion.ID_EDUCACION_FK, id_educacion );
        db.insert( BaseDeDatos.Inv_Educacion.EDUCACION_INV_TABLE_NAME, null, cv );
    }

    public static void insertarTrabajo( SQLiteDatabase db, int id_trabajo, String nombre_trabajo, String empresa_trabajo, int educacion_necesaria_fk, int exp_aporta_trabajar, int sueldo, int comida_consume_trabajar ) {
        ContentValues cv = new ContentValues(  );
        cv.put( BaseDeDatos.Trabajo.ID_TRABAJO, id_trabajo );
        cv.put( BaseDeDatos.Trabajo.NOMBRE_TRABAJO, nombre_trabajo );
        cv.put( BaseDeDatos.Trabajo.EMPRESA_TRABAJO, empresa_trabajo );
        String tipo_trabajo = UtilidadesTablas.getColumnaString( db, BaseDeDatos.Educacion.EDUCACION_TABLE_NAME, BaseDeDatos.Educacion.ID_EDUCACION, educacion_necesaria_fk, BaseDeDatos.Educacion.TIPO_EDUCACION );
        if (tipo_trabajo != null) {
            cv.put( BaseDeDatos.Trabajo.TIPO_TRABAJO, tipo_trabajo );
        } else {
            cv.put( BaseDeDatos.Trabajo.TIPO_TRABAJO, "Básico" );
        }
        cv.put( BaseDeDatos.Trabajo.EDUCACION_NECESARIA_FK, educacion_necesaria_fk );
        cv.put( BaseDeDatos.Trabajo.EXP_APORTA_TRABAJAR, exp_aporta_trabajar );
        cv.put( BaseDeDatos.Trabajo.SUELDO_TRABAJO, sueldo );
        cv.put( BaseDeDatos.Trabajo.COMIDA_CONSUME_TRABAJO, comida_consume_trabajar );
        db.insert( BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME, null, cv );
    }

    public static void reiniciarBaseDeDatos(SQLiteDatabase db){
        db.execSQL( BaseDeDatos.Personaje.SQL_DELETE_PERSONAJE );
        db.execSQL( BaseDeDatos.Trabajo.SQL_DELETE_TRABAJO );
        db.execSQL( BaseDeDatos.Inv_Comida.SQL_DELETE_INV_COMIDA );
        db.execSQL( BaseDeDatos.Comida.SQL_DELETE_COMIDA );
        db.execSQL( BaseDeDatos.Inv_Medicamento.SQL_DELETE_INV_MEDICAMENTO );
        db.execSQL( BaseDeDatos.Medicamento.SQL_DELETE_MEDICAMENTO );
        db.execSQL( BaseDeDatos.Inv_Ocio.SQL_DELETE_INV_OCIO );
        db.execSQL( BaseDeDatos.Ocio.SQL_DELETE_OCIO );
        db.execSQL( BaseDeDatos.Inv_Vehiculo.SQL_DELETE_INV_VEHICULO );
        db.execSQL( BaseDeDatos.Vehiculo.SQL_DELETE_VEHICULO );
        db.execSQL( BaseDeDatos.Inv_Casa.SQL_DELETE_INV_CASA );
        db.execSQL( BaseDeDatos.Casa.SQL_DELETE_CASA );
        db.execSQL( BaseDeDatos.Inv_Educacion.SQL_DELETE_EDUCACION_INV );
        db.execSQL( BaseDeDatos.Educacion.SQL_DELETE_EDUCACION );
        db.execSQL( BaseDeDatos.Trabajo.SQL_CREATE_TRABAJO );
        db.execSQL( BaseDeDatos.Personaje.SQL_CREATE_PERSONAJE );
        db.execSQL( BaseDeDatos.Comida.SQL_CREATE_COMIDA );
        db.execSQL( BaseDeDatos.Inv_Comida.SQL_CREATE_INV_COMIDA );
        db.execSQL( BaseDeDatos.Medicamento.SQL_CREATE_MEDICAMENTO );
        db.execSQL( BaseDeDatos.Inv_Medicamento.SQL_CREATE_INV_MEDICAMENTO );
        db.execSQL( BaseDeDatos.Ocio.SQL_CREATE_OCIO );
        db.execSQL( BaseDeDatos.Inv_Ocio.SQL_CREATE_INV_OCIO );
        db.execSQL( BaseDeDatos.Vehiculo.SQL_CREATE_VEHICULO );
        db.execSQL( BaseDeDatos.Inv_Vehiculo.SQL_CREATE_INV_VEHICULO );
        db.execSQL( BaseDeDatos.Casa.SQL_CREATE_CASA );
        db.execSQL( BaseDeDatos.Inv_Casa.SQL_CREATE_INV_CASA );
        db.execSQL( BaseDeDatos.Educacion.SQL_CREATE_EDUCACION );
        db.execSQL( BaseDeDatos.Inv_Educacion.SQL_CREATE_EDUCACION_INV );
    }

}
