package com.miguelbra.pooplife.trabajo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.UtilidadesGeneral;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.Utilidades;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;
import com.miguelbra.pooplife.objetos.Trabajo;

public class TrabajoActivity extends AppCompatActivity implements ListaTrabajoFragment.OnListFragmentInteractionListener {

    boolean basico, matematicas, letras, informatica, rural, buscado;
    Trabajo trabajo;
    String vista_actual, busqueda;
    ProgressBar pb_exp;
    EditText buscador;
    TextView tv_nivel, tv_siguiente_nivel;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_trabajo );

        buscado = false;
        getSupportActionBar().hide();

        busqueda = "";

        ImageButton ibtn = findViewById( R.id.ibtn_atras_trabajo );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ibtn.setClipToOutline( true );
        }

        vista_actual = "elegir";
        getSupportFragmentManager().beginTransaction().add( R.id.frame_trabajo, new ElegirTrabajarTrabajoFragment() ).commit();
    }

    @Override
    public void onListFragmentInteraction(Trabajo item) {

        trabajo = item;

        Bundle args = new Bundle(  );
        args.putString( "nombre_trabajo", item.getNombre() );
        args.putString( "empresa_trabajo", item.getEmpresa() );
        args.putInt( "sueldo_trabajo", item.getSueldo() );
        args.putInt( "educacion", item.getEducacion() );

        DescripcionTrabajoFragment dtf = new DescripcionTrabajoFragment();
        dtf.setArguments( args );

        vista_actual = "descr_trabajo";
        getSupportFragmentManager().beginTransaction().replace( R.id.frame_lista_trabajo, dtf ).commit();
    }

    public void atras (View view ) {

        switch ( vista_actual ) {
            case "buscador_trabajo":
                vista_actual = "elegir";
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_trabajo, new ElegirTrabajarTrabajoFragment() ).commit();
                break;
            case "elegir":
                finish();
                break;
            case "trabajar":
                vista_actual = "elegir";
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_trabajo, new ElegirTrabajarTrabajoFragment() ).commit();
                break;
            case "descr_trabajo":

                if( basico ) {
                    TextView textView = (TextView) findViewById( R.id.tv_tipo_basico );
                    textView.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
                }
                if ( matematicas ) {
                    TextView textView = (TextView) findViewById( R.id.tv_tipo_matematicas );
                    textView.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
                }
                if ( letras ) {
                    TextView textView = (TextView) findViewById( R.id.tv_tipo_letras );
                    textView.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
                }
                if ( informatica ) {
                    TextView textView = (TextView) findViewById( R.id.tv_tipo_informatica );
                    textView.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
                }
                if ( rural ) {
                    TextView textView = (TextView) findViewById( R.id.tv_tipo_rural );
                    textView.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
                }

                System.out.println( "PASADO" );
                buscador = findViewById(R.id.et_buscador_trabajo);
                if ( buscador.getText().toString().length() > 0 )
                    busqueda = buscador.getText().toString();
                vista_actual = "buscador_trabajo";
                Bundle args = new Bundle(  );
                if ( buscado ) {

                    args.putBoolean( "basico", basico );
                    args.putBoolean( "matematicas", matematicas );
                    args.putBoolean( "letras", letras );
                    args.putBoolean( "informatica", informatica );
                    args.putBoolean( "rural", rural );
                    args.putString( "busqueda", busqueda );
                }
                else {
                    args.putBoolean( "basico", false );
                    args.putBoolean( "matematicas", false );
                    args.putBoolean( "letras", false );
                    args.putBoolean( "informatica", false );
                    args.putBoolean( "rural", false );
                    args.putString( "busqueda", busqueda );
                }

                busqueda = "";

                ListaTrabajoFragment ltf = new ListaTrabajoFragment();
                ltf.setArguments( args );

                System.out.println( " abrir - " + basico + " - " + matematicas + " - " + letras + " - " + informatica + " - " + rural + " - " );

                vista_actual = "buscador_trabajo";
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_lista_trabajo, ltf ).commit();
        }

    }

    public void abrir_lista_trabajo(View view) {

        basico = false;
        matematicas = false;
        letras = false;
        informatica = false;
        rural = false;

        Bundle args = new Bundle(  );
        args.putBoolean( "basico", basico );
        args.putBoolean( "matematicas", matematicas );
        args.putBoolean( "letras", letras );
        args.putBoolean( "informatica", informatica );
        args.putBoolean( "rural", rural );
        args.putString( "busqueda", busqueda );

        BuscarTrabajoFragment btf = new BuscarTrabajoFragment();
        btf.setArguments( args );

        System.out.println( " abrir - " + basico + " - " + matematicas + " - " + letras + " - " + informatica + " - " + rural + " - " );

        vista_actual = "buscador_trabajo";
        getSupportFragmentManager().beginTransaction().replace( R.id.frame_trabajo, btf ).commit();
        //getSupportFragmentManager().beginTransaction().add( R.id.frame_lista_trabajo, new ListaTrabajoFragment() ).commit();
    }

    public void abrir_trabajar(View view) {
        if ( db == null ){
            BaseDeDatos baseDatos = new BaseDeDatos( this );
            db = baseDatos.getWritableDatabase();
        }


        Bundle args = new Bundle(  );
        Cursor c, trabajo;

        c = db.rawQuery( "select " +
                BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK +
                " from " + BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME +
                " where " + BaseDeDatos.Personaje.ID_PERSONAJE + "=?", new String[]{"1"} );

        if ( c.moveToFirst() ) {
            if ( c.getInt(0) != 1 ) {
                trabajo = db.rawQuery( "select " +
                        BaseDeDatos.Trabajo.NOMBRE_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EMPRESA_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.SUELDO_TRABAJO +
                        " from " + BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME +
                        " where " + BaseDeDatos.Trabajo.ID_TRABAJO + "=?", new String[]{c.getString( 0 )} );

                if ( trabajo.moveToFirst() ) {
                    args.putString( "nombre_trabajo", trabajo.getString( 0 ) );
                    args.putString( "empresa_trabajo", trabajo.getString( 1 ) );
                    args.putInt( "sueldo_trabajo", trabajo.getInt( 2 ) );

                    TrabajarFragment tf = new TrabajarFragment();
                    tf.setArguments( args );

                    vista_actual = "trabajar";
                    getSupportFragmentManager().beginTransaction().replace( R.id.frame_trabajo, tf ).commit();
                }
            }else {
                Dialog no_trabajo = new Dialog(this, R.style.custom_alert_no_results);
                no_trabajo.setContentView(R.layout.alert_advertencia_custom);
                TextView tv = no_trabajo.findViewById(R.id.tv_advertencia_custom);
                tv.setText(R.string.sin_trabajo);
                no_trabajo.show();
            }
        }


    }

    public void buscar_trabajos ( View view ) {

        buscador = findViewById(R.id.et_buscador_trabajo);

        buscador.clearFocus();

        if ( buscador.getText().toString().length() > 0 )
            busqueda = buscador.getText().toString();

        Bundle args = new Bundle(  );
        args.putBoolean( "basico", basico );
        args.putBoolean( "matematicas", matematicas );
        args.putBoolean( "letras", letras );
        args.putBoolean( "informatica", informatica );
        args.putBoolean( "rural", rural );
        args.putString( "busqueda", busqueda );

        System.out.println( " - " + basico + " - " + matematicas + " - " + letras + " - " + informatica + " - " + rural + " - " );

        ListaTrabajoFragment ltf = new ListaTrabajoFragment();
        ltf.setArguments( args );

        buscado = true;
        getSupportFragmentManager().beginTransaction().replace( R.id.frame_lista_trabajo, ltf ).commit();

        busqueda = "";
    }

    public void clickado_tipo ( View view ) {

        if ( view.getId() == R.id.tv_tipo_basico ) {
            basico = !basico;
            System.out.println( basico );
            if( basico )
                view.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
            else
                view.setBackgroundResource( R.drawable.atributo_tipo_style );
        } else if ( view.getId() == R.id.tv_tipo_matematicas ) {
            matematicas = !matematicas;
            System.out.println( matematicas );
            if( matematicas )
                view.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
            else
                view.setBackgroundResource( R.drawable.atributo_tipo_style );
        } else if ( view.getId() == R.id.tv_tipo_letras ) {
            letras = !letras;
            System.out.println( letras );
            if( letras )
                view.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
            else
                view.setBackgroundResource( R.drawable.atributo_tipo_style );
        } else if ( view.getId() == R.id.tv_tipo_informatica ) {
            informatica = !informatica;
            System.out.println( informatica );
            if( informatica )
                view.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
            else
                view.setBackgroundResource( R.drawable.atributo_tipo_style );
        } else if ( view.getId() == R.id.tv_tipo_rural) {
            rural = !rural;
            System.out.println( rural );
            if( rural )
                view.setBackgroundResource( R.drawable.btn_tirar_style_ripple );
            else
                view.setBackgroundResource( R.drawable.atributo_tipo_style );
        }
        buscado = false;
    }

    public void coger_trabajo(View view) {

        if ( db == null ){
            BaseDeDatos baseDatos = new BaseDeDatos( this );
            db = baseDatos.getWritableDatabase();
        }

        Dialog dialog = new Dialog( this, R.style.custom_alert_no_results );;
        Cursor c;
        c = db.rawQuery( "select " +
                BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK +
                " from " + BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME +
                " where " + BaseDeDatos.Personaje.ID_PERSONAJE + "=?", new String[]{"1"} );
        if ( c.moveToFirst() ) {
            if ( c.getInt( 0 ) == 1 ) {
                UtilidadesTablas.updateColumnaInteger( db,
                        BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME,
                        BaseDeDatos.Personaje.ID_PERSONAJE, 1,
                        BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK, trabajo.getId() );
                dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
                dialog.setCancelable( true );
                dialog.setContentView( R.layout.alert_nuevo_trabajo );
                TextView tv_trabajo = dialog.findViewById( R.id.tv_nuevo_trabajo );
                tv_trabajo.setText(" " + trabajo.getNombre());
            } else {
                dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
                dialog.setCancelable( true );
                dialog.setContentView( R.layout.alert_ya_tienes_trabajo );
            }
        } else {
            UtilidadesTablas.updateColumnaInteger( db,
                    BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME,
                    BaseDeDatos.Personaje.ID_PERSONAJE, 1,
                    BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK, trabajo.getId() );
            dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
            dialog.setCancelable( true );
            dialog.setContentView( R.layout.alert_nuevo_trabajo );
            TextView tv_trabajo = dialog.findViewById( R.id.tv_nuevo_trabajo );
            tv_trabajo.setText(" " + trabajo.getNombre());
        }
        dialog.show();
    }

    public void trabajar (View view) {
        System.out.println("TRABAJAR");

        BaseDeDatos baseDatos = new BaseDeDatos( this );
        db = baseDatos.getWritableDatabase();
        int comida_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA);
        int id_trabajo = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK );
        int comida_consume = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME, BaseDeDatos.Trabajo.ID_TRABAJO, id_trabajo, BaseDeDatos.Trabajo.COMIDA_CONSUME_TRABAJO);
        int nivel_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.NIVEL_TRABAJO);
        int exp_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.EXP_TRABAJO);
        int exp_aporta = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME, BaseDeDatos.Trabajo.ID_TRABAJO, id_trabajo, BaseDeDatos.Trabajo.EXP_APORTA_TRABAJAR);
        int dinero_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO);
        int estado_animo = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ESTADO_ANIMO);
        int salud = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.SALUD);
        System.out.println(dinero_actual);
        int sueldo = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME, BaseDeDatos.Trabajo.ID_TRABAJO, id_trabajo, BaseDeDatos.Trabajo.SUELDO_TRABAJO);
        System.out.println(sueldo);
        int exp;
        if( comida_consume <= comida_actual ) {
            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA, comida_actual - comida_consume );
            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero_actual + sueldo*nivel_actual) );
            tv_nivel = findViewById(R.id.tv_nivel_actual_trabajo);
            pb_exp = findViewById(R.id.pb_exp);
            tv_siguiente_nivel = findViewById(R.id.tv_sueldo_sig_nivel);
            exp = exp_aporta + exp_actual;
            if( exp < (100 * nivel_actual) ) {
                pb_exp.setProgress( exp );
            } else {
                exp = exp - (100 * nivel_actual) ;
                nivel_actual ++;
                pb_exp.setMax(100 * nivel_actual);
                pb_exp.setProgress( exp );
                tv_siguiente_nivel.setText( sueldo * (nivel_actual + 1) + "" );
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.NIVEL_TRABAJO, nivel_actual );
            }
            if(estado_animo == 0) {
                if (salud == 0) {
                    Dialog aviso = new Dialog(this, R.style.custom_alert_no_results);
                    aviso.setContentView(R.layout.alert_muerte);
                    final Context context = this;
                    aviso.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            UtilidadesGeneral.muerte(context);
                            finish();
                        }
                    });
                    aviso.show();
                } else if (salud - 10 <= 0) {
                    Dialog aviso = new Dialog(this, R.style.custom_alert_no_results);
                    aviso.setContentView(R.layout.alert_advertencia_custom);
                    TextView advertencia = aviso.findViewById(R.id.tv_advertencia_custom);
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.SALUD, 0 );
                    advertencia.setText(R.string.vas_morir);
                    aviso.show();
                } else {
                    Dialog aviso = new Dialog(this, R.style.custom_alert_no_results);
                    aviso.setContentView(R.layout.alert_advertencia_custom);
                    TextView advertencia = aviso.findViewById(R.id.tv_advertencia_custom);
                    advertencia.setText(R.string.perdiendo_vida);
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.SALUD, (salud - 10) );
                    System.out.println(salud - 10);
                    aviso.show();
                }

            } else if (estado_animo - 10 <= 0) {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ESTADO_ANIMO, 0 );
            } else {
                UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ESTADO_ANIMO, (estado_animo - 10) );
            }
            System.out.println("EXP " + exp);
            tv_nivel.setText(nivel_actual + "");
            UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.EXP_TRABAJO, exp );
        }
    }

    public void dejar_trabajo (View view) {
        if ( db == null ){
            BaseDeDatos baseDatos = new BaseDeDatos( this );
            db = baseDatos.getWritableDatabase();
        }
        final Dialog seguro = new Dialog( this, R.style.custom_alert_no_results );
        seguro.requestWindowFeature( Window.FEATURE_NO_TITLE );
        seguro.setCancelable( true );
        seguro.setContentView( R.layout.alert_seguro_dejar_trabajo );
        Button btn_si_dejar = seguro.findViewById( R.id.btn_si_dejar_trabajo );
        btn_si_dejar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguro.dismiss();
                if ( db == null ){
                    BaseDeDatos baseDatos = new BaseDeDatos( v.getContext() );
                    db = baseDatos.getWritableDatabase();
                }
                Dialog dialog = new Dialog( v.getContext(), R.style.custom_alert_no_results );;
                Cursor c;
                c = db.rawQuery( "select " +
                        BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK +
                        " from " + BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME +
                        " where " + BaseDeDatos.Personaje.ID_PERSONAJE + "=?", new String[]{"1"} );
                if ( c.moveToFirst() ) {
                    if ( c.getInt( 0 ) != 1 ) {
                        UtilidadesTablas.updateColumnaInteger( db,
                                BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME,
                                BaseDeDatos.Personaje.ID_PERSONAJE, 1,
                                BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK, 1 );
                        dialog.requestWindowFeature( Window.FEATURE_NO_TITLE );
                        dialog.setCancelable( true );
                        dialog.setContentView( R.layout.alert_advertencia_custom );
                        TextView tv_trabajo_custom = dialog.findViewById( R.id.tv_advertencia_custom );
                        tv_trabajo_custom.setText(R.string.dejado_trabajo);
                        dialog.show();
                        UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.NIVEL_TRABAJO, 1 );
                        UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.EXP_TRABAJO, 0 );
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_trabajo, new ElegirTrabajarTrabajoFragment()).commit();
                    }
                }
            }
        });
        Button btn_no_dejar = seguro.findViewById( R.id.btn_no_dejar_trabajo );
        btn_no_dejar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguro.dismiss();
            }
        });
        seguro.show();
    }
}
