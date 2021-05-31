package com.miguelbra.pooplife.base_de_datos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UtilidadesTablas {

    public static int getColumnaInt( SQLiteDatabase db, String tabla, String id_columna_name, int id_valor, String columna ){
        try ( Cursor c = db.rawQuery( "SELECT " + columna + " FROM " + tabla + " WHERE " + id_columna_name + "=" + id_valor, null ) ) {
            if (c.moveToFirst()) {
                return c.getInt( 0 );
            }
        }catch (Exception e){
            System.out.println( "ERROR AL CONSEGUIR COLUMNA" + columna );
            System.out.println( e.getMessage() );
        }
        return -1;
    }
    public static String getColumnaString( SQLiteDatabase db, String tabla, String id_columna_name, int id_valor, String columna ){
        try ( Cursor c = db.rawQuery( "SELECT " + columna + " FROM " + tabla + " WHERE " + id_columna_name + "=" + id_valor, null ) ) {
            if ( c.moveToFirst() ) {
                return c.getString( 0 );
            }
        }catch ( Exception e ){
            System.out.println( "ERROR AL CONSEGUIR COLUMNA" + columna );
            System.out.println( e.getMessage() );
        }
        return null;
    }

    public static void updateColumnaInteger( SQLiteDatabase db, String tabla, String id_columna_name, int id_valor, String columna_name, int columna_valor ) {
        ContentValues valores = new ContentValues();
        valores.put( columna_name, columna_valor );
        db.update( tabla, valores, id_columna_name + "=" + id_valor, null );
    }
    public static void updateColumnaString( SQLiteDatabase db, String tabla, String id_columna_name, int id_valor, String columna_name, String columna_valor ){
        ContentValues valores = new ContentValues();
        valores.put( columna_name, columna_valor );
        db.update(tabla, valores, id_columna_name + "=" + id_valor, null);
    }

    public static void eliminarDeTablaInteger( SQLiteDatabase db, String tabla, String columna, int valor ){
        db.delete( tabla, columna + "=?", new String[]{(valor + "")} );
    }
    public static void eliminarDeTablaString( SQLiteDatabase db, String tabla, String columna, String valor ){
        db.delete( tabla, columna + "=?", new String[]{valor} );
    }

}
