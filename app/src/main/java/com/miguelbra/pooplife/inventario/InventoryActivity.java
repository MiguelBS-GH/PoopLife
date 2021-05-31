package com.miguelbra.pooplife.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;
import com.miguelbra.pooplife.objetos.Casa;
import com.miguelbra.pooplife.objetos.Comida;
import com.miguelbra.pooplife.objetos.Medicamento;
import com.miguelbra.pooplife.objetos.Ocio;
import com.miguelbra.pooplife.objetos.Vehiculo;

import java.util.Random;

public class InventoryActivity extends AppCompatActivity implements Comida_Inv_Fragment.OnListFragmentInteractionListener, Medicamento_Inv_Fragment.OnListFragmentInteractionListener, Ocio_Inv_Fragment.OnListFragmentInteractionListener, Vehiculo_Inv_Fragment.OnListFragmentInteractionListener, Casa_Inv_Fragment.OnListFragmentInteractionListener{

    FrameLayout fl;
    private String fname;
    TextView tituto_inventario;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_inventario );

        getSupportActionBar().setTitle( R.string.inventario );
        getSupportActionBar().hide();

        tituto_inventario = findViewById( R.id.tv_titulo_inv );
        fl = findViewById( R.id.contenedor_inventario );

        getSupportFragmentManager()
                .beginTransaction()
                .add( R.id.contenedor_inventario, new InventoryFragment() )
                .commit();
        fname = "INVENTARIO";
        tituto_inventario.setText( fname );

        BaseDeDatos baseDatos = new BaseDeDatos( this );
        db = baseDatos.getWritableDatabase();
    }

    public void atras(View view) {
        if(fname.equals( "INVENTARIO" ))
            finish();
        else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.contenedor_inventario, new InventoryFragment() )
                    .commit();
            fname = "INVENTARIO";
        }
        tituto_inventario.setText( fname );
    }

    public void seleccionadoInventario(View view) {
        switch (view.getId()){
            case R.id.medicina_inv_button:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.contenedor_inventario, new Medicamento_Inv_Fragment() )
                        .commit();
                fname="MEDICAMENTOS";
                break;
            case R.id.comida_inv_button:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.contenedor_inventario, new Comida_Inv_Fragment() )
                        .commit();
                fname="COMIDA";
                break;
            case R.id.ocio_inv_button:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.contenedor_inventario, new Ocio_Inv_Fragment() )
                        .commit();
                fname="OCIO";
                break;
            case R.id.mejoras_inv_button:

                fname="MEJORAS";
                break;
            case R.id.vehiculos_inv_button:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.contenedor_inventario, new Vehiculo_Inv_Fragment() )
                        .commit();
                fname="VEHÍCULOS";
                break;
            case R.id.casas_inv_button:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace( R.id.contenedor_inventario, new Casa_Inv_Fragment() )
                        .commit();
                fname="CASAS";
                break;
        }
        tituto_inventario.setText( fname );

    }

    @Override
    public void onListFragmentInteraction(Comida item, TextView tv_cantidad) {
        int comida = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA );

        if( comida != 100 ) {
            int suma = comida + item.getComida_regenera();
            if(suma > 100) {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA, 100 );
                System.out.println( "Se ha recuperado toda la comida" );
                Toast.makeText( this, "Se ha recuperado toda la comida", Toast.LENGTH_SHORT );
            }
            else {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA, suma );
                System.out.println( "Se ha recuperado " + item.getComida_regenera() + " de comida" );
                Toast.makeText( this, "Se ha recuperado " + item.getComida_regenera() + " de comida", Toast.LENGTH_SHORT );
            }
            int cantidad = Integer.parseInt( tv_cantidad.getText().toString() );
            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Inv_Comida.INV_COMIDA_TABLE_NAME, BaseDeDatos.Inv_Comida.ID_COMIDA_FK, item.getId(), BaseDeDatos.Inv_Comida.CANTIDAD_COMIDA, cantidad - 1 );
            if ((cantidad - 1) <= 0){
                UtilidadesTablas.eliminarDeTablaInteger(db, BaseDeDatos.Inv_Comida.INV_COMIDA_TABLE_NAME, BaseDeDatos.Inv_Comida.ID_COMIDA_FK, item.getId());
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_inventario, new Comida_Inv_Fragment()).commit();
            } else {
                tv_cantidad.setText( ( cantidad - 1) + "" );
            }

        }
        else{
            System.out.println( "La comida ya está al máximo" );
            Toast.makeText( this, "La comida ya está al máximo", Toast.LENGTH_SHORT );
        }
    }

    @Override
    public void onListFragmentInteraction(Medicamento item, TextView tv_cantidad) {
        int salud = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.SALUD );
        if(salud != 100) {
            int suma = salud + item.getSalud_recuperada();
            if( suma > 100 ) {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.SALUD, 100 );
                System.out.println( "Se ha recuperado toda la salud" );
                Toast.makeText( this, "Se ha recuperado toda la salud", Toast.LENGTH_SHORT );
            }
            else{
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.SALUD, suma );
                System.out.println( "Se ha recuperado " + item.getSalud_recuperada() + " de salud" );
                Toast.makeText( this, "Se ha recuperado " + item.getSalud_recuperada() + "Salud", Toast.LENGTH_SHORT );
            }
            int cantidad = Integer.parseInt( tv_cantidad.getText().toString() );
            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Inv_Medicamento.INV_MEDICAMENTO_TABLE_NAME, BaseDeDatos.Inv_Medicamento.ID_MEDICAMENTO_FK, item.getId(), BaseDeDatos.Inv_Medicamento.CANTIDAD_MEDICAMENTO, cantidad - 1 );
            if ((cantidad - 1) == 0){
                UtilidadesTablas.eliminarDeTablaInteger(db, BaseDeDatos.Inv_Medicamento.INV_MEDICAMENTO_TABLE_NAME,  BaseDeDatos.Inv_Medicamento.ID_MEDICAMENTO_FK, item.getId());
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_inventario, new Medicamento_Inv_Fragment()).commit();
            } else {
                tv_cantidad.setText( ( cantidad - 1) + "" );
            }

        }
        else{
            System.out.println( "La salud ya está al máximo" );
            Toast.makeText( this, "La salud ya está al máximo", Toast.LENGTH_SHORT );
        }
    }

    @Override
    public void onListFragmentInteraction(Ocio item, TextView tv_cantidad) {
        int ocio = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ESTADO_ANIMO );
        int comida_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA );
        Random prob = new Random();
        double se_rompe = prob.nextDouble() * 10;
        System.out.println(se_rompe);
        if( ocio != 100 && item.getComida_consume() <= comida_actual ) {
            int suma = ocio + item.getEstado_regenera();
            if( suma > 100 ) {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ESTADO_ANIMO, 100 );
                System.out.println( "Ha recuperado todo el animo" );
                Toast.makeText( this, "Ha recuperado todo el animo", Toast.LENGTH_SHORT );
            }
            else {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ESTADO_ANIMO, suma );
                System.out.println( "Ha recuperado " + item.getEstado_regenera() + " de animo" );
                Toast.makeText( this, "Ha recuperado " + item.getEstado_regenera() + " de animo", Toast.LENGTH_SHORT );
            }

            if ( se_rompe <= item.getProb_romperse() ) {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Inv_Ocio.INV_OCIO_TABLE_NAME, BaseDeDatos.Inv_Ocio.ID_OCIO_FK, item.getId(), BaseDeDatos.Inv_Ocio.CANTIDAD_OCIO, item.getCantidad() - 1 );
                int cantidad = Integer.parseInt( tv_cantidad.getText().toString() );
                Dialog roto = new Dialog( this, R.style.custom_alert_no_results );
                roto.setContentView( R.layout.alert_advertencia_custom );
                TextView tv_roto = roto.findViewById( R.id.tv_advertencia_custom );
                tv_roto.setText( R.string.roto_objeto );
                roto.show();
                if ((cantidad - 1) == 0){
                    UtilidadesTablas.eliminarDeTablaInteger(db, BaseDeDatos.Inv_Ocio.INV_OCIO_TABLE_NAME, BaseDeDatos.Inv_Ocio.ID_OCIO_FK, item.getId());
                    getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_inventario, new Ocio_Inv_Fragment()).commit();
                } else {
                    tv_cantidad.setText( (cantidad - 1) + "" );
                }
            }

            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA, comida_actual - item.getComida_consume() );
        }
        else {
            System.out.println( "Ya tienes el máximo de animo" );
            Toast.makeText( this, "Ya tienes el máximo de animo", Toast.LENGTH_SHORT );
        }
    }

    @Override
    public void onListFragmentInteraction(Vehiculo item) {
        int vehiculo = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_VEHICULO_PERSONAJE_FK );
        int vehiculo_nuevo = item.getId();
        if ( vehiculo_nuevo != vehiculo) {
            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_VEHICULO_PERSONAJE_FK, vehiculo_nuevo );
            System.out.println( "Ahora usarás el " + item.getNombre() );
            Toast.makeText( this, "Ahora usas el " + item.getNombre(), Toast.LENGTH_SHORT );
        }
        else {
            System.out.println( "Ya estás usando ese vehiculo" );
            Toast.makeText( this, "Ya estás usando ese vehiculo", Toast.LENGTH_SHORT );
        }
    }

    public void onListFragmentInteraction(Casa item) {
        int casa = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_CASA_PERSONAJE_FK );
        int casa_nueva = item.getId();
        if ( casa_nueva != casa) {
            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_CASA_PERSONAJE_FK, casa_nueva );
            System.out.println( "Ahora vives en " + item.getDireccion() );
            Toast.makeText( this, "Ahora vives en " + item.getDireccion(), Toast.LENGTH_SHORT );
        }
        else {
            System.out.println( "Ya vives en esa casa" );
            Toast.makeText( this, "Ya vives en esa casa", Toast.LENGTH_SHORT );
        }
    }


}
