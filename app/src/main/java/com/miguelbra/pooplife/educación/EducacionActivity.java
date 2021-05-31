package com.miguelbra.pooplife.educación;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.Utilidades;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;
import com.miguelbra.pooplife.objetos.Educacion;

public class EducacionActivity extends AppCompatActivity implements EducacionFragment.OnListFragmentInteractionListener {

    EditText et_busqueda;
    String busqueda;
    Button si;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_educacion );

        getSupportActionBar().setTitle( R.string.inventario );
        getSupportActionBar().hide();

        FrameLayout fl = findViewById( R.id.frame_lista_educacion );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fl.setClipToOutline( true );
        }

        BaseDeDatos baseDatos = new BaseDeDatos( this );
        db = baseDatos.getWritableDatabase();

        busqueda = "";

        Bundle args = new Bundle(  );
        args.putString( "busqueda", busqueda );

        EducacionFragment ef = new EducacionFragment();
        ef.setArguments( args );

        getSupportFragmentManager()
                .beginTransaction()
                .add( R.id.frame_lista_educacion, ef )
                .commit();

        si = findViewById( R.id.btn_si_pagar);
        et_busqueda = findViewById( R.id.et_educacion_buscada );

    }

    @Override
    public void onListFragmentInteraction(final Educacion item) {

        final Dialog dialog = new Dialog( this, R.style.custom_alert_no_results );
        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
        dialog.setCancelable( false );
        dialog.setContentView( R.layout.alert_seguro_pagar);
        TextView tv_precio = dialog.findViewById( R.id.tv_seguro_precio);
        final TextView tv_advertancia = dialog.findViewById( R.id.tv_advertencia_dinero );
        tv_precio.setText( item.getPrecio() + " ");
        dialog.findViewById( R.id.btn_si_pagar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dinero = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO );
                int comida_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA );
                System.out.println( comida_actual );
                if ( item.getPrecio() <= dinero && item.getComida_consume() <= comida_actual ){
                    estudiar_educacion( item, dinero, comida_actual );
                    Bundle args = new Bundle(  );
                    args.putString( "busqueda", busqueda );

                    EducacionFragment ef = new EducacionFragment();
                    ef.setArguments( args );

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace( R.id.frame_lista_educacion, ef )
                            .commit();
                    dialog.dismiss();
                }
                else if ( item.getPrecio() > dinero ){
                    tv_advertancia.setTextSize( 14 );
                    tv_advertancia.setPadding( 24, 8, 24, 0 );
                    tv_advertancia.setText( R.string.no_dinero );
                } else if ( item.getComida_consume() > comida_actual ) {
                    tv_advertancia.setTextSize( 14 );
                    tv_advertancia.setPadding( 24, 8, 24, 0 );
                    tv_advertancia.setText( R.string.no_comida );
                }

            }
        } );
        dialog.findViewById( R.id.btn_no_pagar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        } );;
        dialog.show();
    }

    public void estudiar_educacion( Educacion educacion, int dinero, int comida_actual ) {
        System.out.println( "Estudiar" );

        UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, dinero - educacion.getPrecio() );
        Utilidades.insertarEducacionEnInventario( db, educacion.getId() );

        UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA, comida_actual - educacion.getComida_consume() );
        int nivel;
        switch (educacion.getTipo()) {
            case BaseDeDatos.Educacion.MATEMATICAS:
                nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.MATEMATICAS_PERSONAJE);
                if( (educacion.getNivel() + 1) > nivel ) {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.MATEMATICAS_PERSONAJE, (educacion.getNivel()) );
                }
                break;
            case BaseDeDatos.Educacion.LETRAS:
                nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.LETRAS_PERSONAJE);
                if( (educacion.getNivel() + 1) > nivel ) {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.LETRAS_PERSONAJE, (educacion.getNivel()) );
                }
                break;
            case BaseDeDatos.Educacion.INFORMÁTICA:
                nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.INFORMATICA_PERSONAJE);
                if( (educacion.getNivel() + 1) > nivel ) {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.INFORMATICA_PERSONAJE, (educacion.getNivel()) );
                }
                break;
            case BaseDeDatos.Educacion.RURAL:
                nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.RURAL_PERSONAJE);
                if( (educacion.getNivel() + 1) > nivel ) {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.RURAL_PERSONAJE, (educacion.getNivel()) );
                }

                break;
        }
        System.out.println("MATEMÁTICAS: " + UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.MATEMATICAS_PERSONAJE));
        System.out.println("LETRAS: " + UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.LETRAS_PERSONAJE));
        System.out.println("INFORMÁTICA: " + UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.INFORMATICA_PERSONAJE));
        System.out.println("RURAL: " + UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.RURAL_PERSONAJE));

    }



    public void buscarEducaciones(View view) {

        et_busqueda.clearFocus();

        if ( et_busqueda.getText().toString().length() > 0 ) {
            busqueda = et_busqueda.getText().toString();

            Bundle args = new Bundle(  );
            args.putString( "busqueda", busqueda );

            EducacionFragment ef = new EducacionFragment();
            ef.setArguments( args );

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace( R.id.frame_lista_educacion, ef )
                    .commit();

        }else {
            busqueda = "";
        }

        System.out.println( "Busqueda: " + busqueda );

    }


}
