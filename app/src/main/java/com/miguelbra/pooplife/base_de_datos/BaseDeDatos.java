package com.miguelbra.pooplife.base_de_datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public final class BaseDeDatos extends SQLiteOpenHelper{
    // ---------------------------------------------------------------------------------------------
    //--- NOMBRE DE TABLAS Y COLUMNAS --------------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    // --- TABLA PERSONAJE -------------------------------------------------------------------------
    public final class Personaje {
        public static final String PERSONAJE_TABLE_NAME = "personaje";
        public static final String ID_PERSONAJE = "id_personaje";
        public static final String NOMBRE_PERSONAJE = "nombre_personaje";
        public static final String SALUD = "salud";
        public static final String COMIDA = "comida";
        public static final String DINERO = "dinero";
        public static final String ESTADO_ANIMO = "estado_animo";
        public static final String PROB_ENFERMEDAD = "prob_enfermedad";
        public static final String ID_TRABAJO_PERSONAJE_FK = "id_trabajo_personaje_fk";
        public static final String ID_VEHICULO_PERSONAJE_FK = "id_vehiculo_personaje_fk";
        public static final String ID_CASA_PERSONAJE_FK = "id_casa_personaje_fk";
        public static final String EXP_TRABAJO = "exp_trabajo";
        public static final String NIVEL_TRABAJO = "nivel_trabajo";
        public static final String MATEMATICAS_PERSONAJE = "matematicas_personaje";
        public static final String LETRAS_PERSONAJE = "letras_personaje";
        public static final String RURAL_PERSONAJE = "rural_personaje";
        public static final String INFORMATICA_PERSONAJE = "informatica_personaje";

        // CREATE TABLE PERSONAJE
        public static final String SQL_CREATE_PERSONAJE =
                "CREATE TABLE " + PERSONAJE_TABLE_NAME + " (" +
                        ID_PERSONAJE + " INTEGER PRIMARY KEY," +
                        NOMBRE_PERSONAJE + " STRING," +
                        SALUD + " INTEGER," +
                        COMIDA + " INTEGER," +
                        DINERO + " INTEGER," +
                        ESTADO_ANIMO + " INTEGER," +
                        PROB_ENFERMEDAD + " INTEGER," +
                        ID_TRABAJO_PERSONAJE_FK + " INTEGER," +
                        ID_VEHICULO_PERSONAJE_FK + " INTEGER," +
                        ID_CASA_PERSONAJE_FK + " INTEGER," +
                        EXP_TRABAJO + " INTEGER," +
                        NIVEL_TRABAJO + " INTEGER," +
                        MATEMATICAS_PERSONAJE + " INTEGER," +
                        LETRAS_PERSONAJE + " INTEGER," +
                        RURAL_PERSONAJE + " INTEGER," +
                        INFORMATICA_PERSONAJE + " INTEGER," +
                        "constraint fk_id_trabajo_personaje FOREIGN KEY(" + ID_TRABAJO_PERSONAJE_FK + ") REFERENCES " + Trabajo.TRABAJO_TABLE_NAME + "(" + Trabajo.ID_TRABAJO + ")," +
                        "constraint fk_id_vehiculo_personaje FOREIGN KEY(" + ID_VEHICULO_PERSONAJE_FK + ") REFERENCES " + Vehiculo.VEHICULO_TABLE_NAME + "(" + Vehiculo.ID_VEHICULO + ")," +
                        "constraint fk_id_casa_personaje FOREIGN KEY(" + ID_CASA_PERSONAJE_FK + ") REFERENCES " + Casa.CASA_TABLE_NAME + "(" + Casa.ID_CASA + "));";

        public static final String SQL_DELETE_PERSONAJE =
                "DROP TABLE IF EXISTS " + PERSONAJE_TABLE_NAME;
    }

    // --- TABLA COMIDA ----------------------------------------------------------------------------
    public final class Comida {
        public static final String COMIDA_TABLE_NAME = "comida";
        public static final String ID_COMIDA = "id_comida";
        public static final String NOMBRE_COMIDA = "nombre_comida";
        public static final String PRECIO_COMIDA = "precio";
        public static final String COMIDA_REGENERA = "comida_regenera";

        public static final String SQL_CREATE_COMIDA =
                "CREATE TABLE " + COMIDA_TABLE_NAME + " (" +
                        ID_COMIDA + " INTEGER PRIMARY KEY," +
                        NOMBRE_COMIDA + " STRING," +
                        PRECIO_COMIDA + " INTEGER," +
                        COMIDA_REGENERA + " INTEGER)";

        public static final String SQL_DELETE_COMIDA =
                "DROP TABLE IF EXISTS " + COMIDA_TABLE_NAME;
    }

    // --- TABLA INV_COMIDA ------------------------------------------------------------------------
    public final class Inv_Comida {
        public static final String INV_COMIDA_TABLE_NAME = "inv_comida";
        public static final String ID_COMIDA_FK = "id_comida_fk";
        public static final String CANTIDAD_COMIDA = "cantidad_comida";

        // CREATE TABLE INV_COMIDA
        public static final String SQL_CREATE_INV_COMIDA =
                "CREATE TABLE " + INV_COMIDA_TABLE_NAME + " (" +
                        ID_COMIDA_FK + " INTEGER PRIMARY KEY," +
                        CANTIDAD_COMIDA + " INTEGER," +
                        "constraint fk_id_comida_inv FOREIGN KEY(" + ID_COMIDA_FK + ") REFERENCES " + Comida.COMIDA_TABLE_NAME + "(" + Comida.ID_COMIDA + "));";

        public static final String SQL_DELETE_INV_COMIDA =
                "DROP TABLE IF EXISTS " + INV_COMIDA_TABLE_NAME;
    }

    // --- TABLA DE MEDICAMENTO --------------------------------------------------------------------
    public final class Medicamento {
        public static final String MEDICAMENTO_TABLE_NAME = "medicamento";
        public static final String ID_MEDICAMENTO = "id_medicamento";
        public static final String NOMBRE_MEDICAMENTO = "nombre_medicamento";
        public static final String TIPO_MEDICAMENTO = "tipo_medicamento";
        public static final String PROB_CURA = "prob_cura";
        public static final String PRECIO_MEDICAMENTO = "precio";
        public static final String VIDA_REGENERA = "vida_regenera";

        // --- TIPO DE MEDICAMENTO ---
        public static final String TIPO_ENFERMEDAD = "tipo_enfermedad";
        public static final String TIPO_HERIDA = "tipo_herida";
        // --- --- --- --- --- --- ---

        public static final String SQL_CREATE_MEDICAMENTO =
                "CREATE TABLE " + MEDICAMENTO_TABLE_NAME + " (" +
                        ID_MEDICAMENTO + " INTEGER PRIMARY KEY," +
                        NOMBRE_MEDICAMENTO + " STRING," +
                        TIPO_MEDICAMENTO + " STRING," +
                        PROB_CURA + " INTEGER," +
                        PRECIO_MEDICAMENTO + " INTEGER," +
                        VIDA_REGENERA + " INTEGER);";

        public static final String SQL_DELETE_MEDICAMENTO =
                "DROP TABLE IF EXISTS " + MEDICAMENTO_TABLE_NAME;
    }

    // --- TABLA INV_MEDICAMENTO -------------------------------------------------------------------
    public final class Inv_Medicamento {
        public static final String INV_MEDICAMENTO_TABLE_NAME = "inv_medicamento";
        public static final String ID_MEDICAMENTO_FK = "id_medicamento_fk";
        public static final String CANTIDAD_MEDICAMENTO = "cantidad_medicamento";

        public static final String SQL_CREATE_INV_MEDICAMENTO =
                "CREATE TABLE " + INV_MEDICAMENTO_TABLE_NAME + " (" +
                        ID_MEDICAMENTO_FK + " INTEGER PRIMARY KEY," +
                        CANTIDAD_MEDICAMENTO + " INTEGER," +
                        "constraint fk_id_medicamento_inv FOREIGN KEY(" + ID_MEDICAMENTO_FK + ") REFERENCES " + Medicamento.MEDICAMENTO_TABLE_NAME + "(" + Medicamento.ID_MEDICAMENTO + "));";

        public static final String SQL_DELETE_INV_MEDICAMENTO =
                "DROP TABLE IF EXISTS " + INV_MEDICAMENTO_TABLE_NAME;
    }

    // --- TABLA OCIO ------------------------------------------------------------------------------
    public final class Ocio {
        public static final String OCIO_TABLE_NAME = "ocio";
        public static final String ID_OCIO = "id_ocio";
        public static final String NOMBRE_OCIO = "nombre_ocio";
        public static final String PROB_ROTO = "prob_roto";
        public static final String PRECIO_OCIO = "precio_ocio";
        public static final String ESTADO_REGENERA = "estado_regenera";
        public static final String COMIDA_CONSUME_OCIO = "comida_consume_ocio";

        public static final String SQL_CREATE_OCIO =
                "CREATE TABLE " + OCIO_TABLE_NAME + " (" +
                        ID_OCIO + " INTEGER PRIMARY KEY," +
                        NOMBRE_OCIO + " STRING," +
                        PROB_ROTO + " REAL," +
                        PRECIO_OCIO + " INTEGER," +
                        ESTADO_REGENERA + " INTEGER," +
                        COMIDA_CONSUME_OCIO + " INTEGER);";

        public static final String SQL_DELETE_OCIO =
                "DROP TABLE IF EXISTS " + OCIO_TABLE_NAME;
    }

    // --- TABLA INV_OCIO --------------------------------------------------------------------------
    public final class Inv_Ocio {
        public static final String INV_OCIO_TABLE_NAME = "inv_ocio";
        public static final String ID_OCIO_FK = "id_ocio_fk";
        public static final String CANTIDAD_OCIO = "cantidad_ocio";

        public static final String SQL_CREATE_INV_OCIO =
                "CREATE TABLE " + INV_OCIO_TABLE_NAME + " (" +
                        ID_OCIO_FK + " INTEGER PRIMARY KEY," +
                        CANTIDAD_OCIO + " INTEGER," +
                        "constraint fk_id_ocio_inv FOREIGN KEY(" + ID_OCIO_FK + ") REFERENCES " + Ocio.OCIO_TABLE_NAME + "(" + Ocio.ID_OCIO + "));";

        public static final String SQL_DELETE_INV_OCIO =
                "DROP TABLE IF EXISTS " + INV_OCIO_TABLE_NAME;
    }

    // --- TABLA VEHICULOS -------------------------------------------------------------------------
    public final class Vehiculo {
        public static final String VEHICULO_TABLE_NAME = "vehiculo";
        public static final String ID_VEHICULO = "id_vehiculo";
        public static final String NOMBRE_VEHICULO = "nombre_vehiculo";
        public static final String PROB_AVERIA = "prob_averia";
        public static final String PRECIO_VEHICULO = "precio_vehiculo";
        public static final String VELOCIDAD_VEHICULO = "velocidad_vehiculo";

        public static final String SQL_CREATE_VEHICULO =
                "CREATE TABLE " + VEHICULO_TABLE_NAME + " (" +
                        ID_VEHICULO + " INTEGER PRIMARY KEY," +
                        NOMBRE_VEHICULO + " STRING," +
                        PROB_AVERIA + " REAL," +
                        PRECIO_VEHICULO + " INTEGER," +
                        VELOCIDAD_VEHICULO + " INTEGER);";

        public static final String SQL_DELETE_VEHICULO =
                "DROP TABLE IF EXISTS " + VEHICULO_TABLE_NAME;
    }

    // --- TABLA INV_VEHICULO ----------------------------------------------------------------------
    public final class Inv_Vehiculo {
        public static final String INV_VEHICULO_TABLE_NAME = "inv_vehiculo";
        public static final String ID_VEHICULO_FK = "id_vehiculo_fk";

        public static final String SQL_CREATE_INV_VEHICULO =
                "CREATE TABLE " + INV_VEHICULO_TABLE_NAME + " (" +
                        ID_VEHICULO_FK + " INTEGER PRIMARY KEY," +
                        "constraint fk_id_vehiculo_inv FOREIGN KEY(" + ID_VEHICULO_FK + ") REFERENCES " + Vehiculo.VEHICULO_TABLE_NAME + "(" + Vehiculo.ID_VEHICULO + "));";

        public static final String SQL_DELETE_INV_VEHICULO =
                "DROP TABLE IF EXISTS " + INV_VEHICULO_TABLE_NAME;
    }

    // --- TABLA CASA ------------------------------------------------------------------------------
    public final class Casa {
        public static final String CASA_TABLE_NAME = "casa";
        public static final String ID_CASA = "id_casa";
        public static final String DIRECCION_CASA = "direccion_casa";
        public static final String TAMAÑO_CASA = "tamaño_casa";
        public static final String ALQUILER_CASA = "alquiler_casa";
        public static final String PRECIO_CASA = "precio_casa";
        public static final String BONIF_CASA = "bonif_casa";

        public static final String SQL_CREATE_CASA =
                "CREATE TABLE " + CASA_TABLE_NAME + " (" +
                        ID_CASA + " INTEGER PRIMARY KEY," +
                        DIRECCION_CASA + " STRING," +
                        TAMAÑO_CASA + " INTEGER," +
                        ALQUILER_CASA + " INTEGER," +
                        PRECIO_CASA + " INTEGER," +
                        BONIF_CASA + " REAL);";

        public static final String SQL_DELETE_CASA =
                "DROP TABLE IF EXISTS " + CASA_TABLE_NAME;
    }

    // --- TABLA INV_CASA --------------------------------------------------------------------------
    public final class Inv_Casa {
        public static final String INV_CASA_TABLE_NAME = "inv_casa";
        public static final String ID_CASA_FK = "id_casa_fk";

        public static final String SQL_CREATE_INV_CASA =
                "CREATE TABLE " + INV_CASA_TABLE_NAME + " (" +
                        ID_CASA_FK + " INTEGER PRIMARY KEY," +
                        "CONSTRAINT fk_id_vehiculo_inv FOREIGN KEY(" + ID_CASA_FK + ") REFERENCES " + Casa.CASA_TABLE_NAME + "(" + Casa.ID_CASA + "));";

        public static final String SQL_DELETE_INV_CASA =
                "DROP TABLE IF EXISTS " + INV_CASA_TABLE_NAME;
    }

    // --- TABLA TRABAJO ---------------------------------------------------------------------------
    public final class Trabajo {
        public static final String TRABAJO_TABLE_NAME = "trabajo";
        public static final String ID_TRABAJO = "id_trabajo";
        public static final String NOMBRE_TRABAJO = "nombre_trabajo";
        public static final String EMPRESA_TRABAJO = "empresa_trabajo";
        public static final String TIPO_TRABAJO = "tipo_trabajo";
        public static final String EDUCACION_NECESARIA_FK = "educacion_necesaria";
        public static final String EXP_APORTA_TRABAJAR = "exp_aporta_trabajar";
        public static final String SUELDO_TRABAJO = "sueldo_trabajo";
        public static final String COMIDA_CONSUME_TRABAJO = "comida_consume_trabajo";


        public static final String SQL_CREATE_TRABAJO =
                "CREATE TABLE " + TRABAJO_TABLE_NAME + " (" +
                        ID_TRABAJO + " INTEGER PRIMARY KEY," +
                        NOMBRE_TRABAJO + " STRING," +
                        EMPRESA_TRABAJO + " STRING," +
                        TIPO_TRABAJO + " STRING," +
                        EDUCACION_NECESARIA_FK + " INTEGER," +
                        EXP_APORTA_TRABAJAR + " INTEGER," +
                        SUELDO_TRABAJO + " INTEGER," +
                        COMIDA_CONSUME_TRABAJO + " INTEGER," +
                        "CONSTRAINT fk_id_educacion_trabajo FOREIGN KEY(" + EDUCACION_NECESARIA_FK + ") REFERENCES " + Educacion.EDUCACION_TABLE_NAME + "(" + Educacion.ID_EDUCACION + "))";

        public static final String SQL_DELETE_TRABAJO =
                "DROP TABLE IF EXISTS " + TRABAJO_TABLE_NAME;
    }

    // --- TABLA EDUCACIÓN -------------------------------------------------------------------------
    public final class Educacion {
        public static final String EDUCACION_TABLE_NAME = "educacion";
        public static final String ID_EDUCACION = "id_educacion";
        public static final String NOMBRE_EDUCACION = "nombre_educacion";
        public static final String TIPO_EDUCACION = "tipo_educacion";
        public static final String NIVEL_EDUCACION = "nivel_educacion";
        public static final String PRECIO_EDUCACION = "precio_educacion";
        public static final String COMIDA_CONSUME_ESTUDIAR = "comida_consume_estudiar";

        public static final String SQL_CREATE_EDUCACION =
                "CREATE TABLE " + EDUCACION_TABLE_NAME + " (" +
                        ID_EDUCACION + " INTEGER PRIMARY KEY," +
                        NOMBRE_EDUCACION + " STRING," +
                        TIPO_EDUCACION + " STRING," +
                        NIVEL_EDUCACION + " INTEGER," +
                        PRECIO_EDUCACION + " INTEGER," +
                        COMIDA_CONSUME_ESTUDIAR + " INTEGER);";

        public static final String SQL_DELETE_EDUCACION =
                "DROP TABLE IF EXISTS " + EDUCACION_TABLE_NAME;

        public static final String MATEMATICAS = "Matemáticas";
        public static final String LETRAS = "Letras";
        public static final String INFORMÁTICA = "Informática";
        public static final String RURAL = "Rural";
    }

    // --- TABLA INV_EDUCACION ---------------------------------------------------------------------
    public final class Inv_Educacion {
        public static final String EDUCACION_INV_TABLE_NAME = "educacion_inv";
        public static final String ID_EDUCACION_FK = "id_educacion_fk";

        public static final String SQL_CREATE_EDUCACION_INV =
                "CREATE TABLE " + EDUCACION_INV_TABLE_NAME + " (" +
                        ID_EDUCACION_FK + " INTEGER PRIMARY KEY," +
                        "CONSTRAINT fk_id_educacion_inv FOREIGN KEY(" + ID_EDUCACION_FK + ") REFERENCES " + Educacion.EDUCACION_TABLE_NAME + "(" + Educacion.ID_EDUCACION + "));";

        public static final String SQL_DELETE_EDUCACION_INV =
                "DROP TABLE IF EXISTS " + EDUCACION_INV_TABLE_NAME;
    }

    //----------------------------------------------------------------------------------------------
    //--- CREACIÓN DE BASE DE DATOS Y TABLAS -------------------------------------------------------
    //----------------------------------------------------------------------------------------------

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PoopLifeDB";

    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL( Trabajo.SQL_CREATE_TRABAJO );
            db.execSQL( Personaje.SQL_CREATE_PERSONAJE );
            db.execSQL( Comida.SQL_CREATE_COMIDA );
            db.execSQL( Inv_Comida.SQL_CREATE_INV_COMIDA );
            db.execSQL( Medicamento.SQL_CREATE_MEDICAMENTO );
            db.execSQL( Inv_Medicamento.SQL_CREATE_INV_MEDICAMENTO );
            db.execSQL( Ocio.SQL_CREATE_OCIO );
            db.execSQL( Inv_Ocio.SQL_CREATE_INV_OCIO );
            db.execSQL( Vehiculo.SQL_CREATE_VEHICULO );
            db.execSQL( Inv_Vehiculo.SQL_CREATE_INV_VEHICULO );
            db.execSQL( Casa.SQL_CREATE_CASA );
            db.execSQL( Inv_Casa.SQL_CREATE_INV_CASA );
            db.execSQL( Educacion.SQL_CREATE_EDUCACION );
            db.execSQL( Inv_Educacion.SQL_CREATE_EDUCACION_INV );

            Log.i("BD", "CREADAS");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( Personaje.SQL_DELETE_PERSONAJE );
        db.execSQL( Trabajo.SQL_DELETE_TRABAJO );
        db.execSQL( Inv_Comida.SQL_DELETE_INV_COMIDA );
        db.execSQL( Comida.SQL_DELETE_COMIDA );
        db.execSQL( Inv_Medicamento.SQL_DELETE_INV_MEDICAMENTO );
        db.execSQL( Medicamento.SQL_DELETE_MEDICAMENTO );
        db.execSQL( Inv_Ocio.SQL_DELETE_INV_OCIO );
        db.execSQL( Ocio.SQL_DELETE_OCIO );
        db.execSQL( Inv_Vehiculo.SQL_DELETE_INV_VEHICULO );
        db.execSQL( Vehiculo.SQL_DELETE_VEHICULO );
        db.execSQL( Inv_Casa.SQL_DELETE_INV_CASA );
        db.execSQL( Casa.SQL_DELETE_CASA );
        db.execSQL( Inv_Educacion.SQL_DELETE_EDUCACION_INV );
        db.execSQL( Educacion.SQL_DELETE_EDUCACION );
        db.execSQL( Trabajo.SQL_CREATE_TRABAJO );
        db.execSQL( Personaje.SQL_CREATE_PERSONAJE );
        db.execSQL( Comida.SQL_CREATE_COMIDA );
        db.execSQL( Inv_Comida.SQL_CREATE_INV_COMIDA );
        db.execSQL( Medicamento.SQL_CREATE_MEDICAMENTO );
        db.execSQL( Inv_Medicamento.SQL_CREATE_INV_MEDICAMENTO );
        db.execSQL( Ocio.SQL_CREATE_OCIO );
        db.execSQL( Inv_Ocio.SQL_CREATE_INV_OCIO );
        db.execSQL( Vehiculo.SQL_CREATE_VEHICULO );
        db.execSQL( Inv_Vehiculo.SQL_CREATE_INV_VEHICULO );
        db.execSQL( Casa.SQL_CREATE_CASA );
        db.execSQL( Inv_Casa.SQL_CREATE_INV_CASA );
        db.execSQL( Educacion.SQL_CREATE_EDUCACION );
        db.execSQL( Inv_Educacion.SQL_CREATE_EDUCACION_INV );

        Log.i("BD", "ACTUALIZAR");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen( db );
    }
}
