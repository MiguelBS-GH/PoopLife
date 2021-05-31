package com.miguelbra.pooplife;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.RellenarTablas;
import com.miguelbra.pooplife.base_de_datos.Utilidades;

public class UtilidadesGeneral {

    static SQLiteDatabase db;

    public static void muerte(Context context) {
        if ( db == null ){
            BaseDeDatos baseDatos = new BaseDeDatos( context );
            db = baseDatos.getWritableDatabase();
        }
        Utilidades.reiniciarBaseDeDatos(db);
        RellenarTablas.rellenarDatos(db);
        Utilidades.insertarPersonaje( db, 1, "Miguel", 100, 1000, 100, 50, 1, 1, -1, -1, 0, 1, 0, 0, 0, 0 );
    }

}
