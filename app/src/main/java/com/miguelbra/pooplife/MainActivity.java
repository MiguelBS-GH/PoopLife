package com.miguelbra.pooplife;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.RellenarTablas;
import com.miguelbra.pooplife.base_de_datos.Utilidades;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;
import com.miguelbra.pooplife.educación.EducacionActivity;
import com.miguelbra.pooplife.inventario.InventoryActivity;
import com.miguelbra.pooplife.tienda.TiendaActivity;
import com.miguelbra.pooplife.trabajo.TrabajoActivity;

public class MainActivity extends AppCompatActivity {

    boolean abierto;
    ConstraintLayout clVida;
    ImageButton abrirPerfil;
    ImageView estado_animo;
    ScrollView sv;
    TextView tv_salud, tv_dinero, tv_comida, tv_energia, tv_vehiculo, tv_casa, tv_trabajo;
    int height;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        getSupportActionBar().hide();

        tv_salud = findViewById( R.id.tv_salud_actual );
        tv_dinero = findViewById( R.id.tv_dinero_actual );
        tv_comida = findViewById( R.id.tv_comida_actual );
        tv_vehiculo = findViewById( R.id.tv_vehiculo_actual );
        tv_casa = findViewById( R.id.tv_casa_actual );
        estado_animo = findViewById( R.id.ivEstadoAnimo );
        tv_trabajo = findViewById( R.id.tv_trabajo_actual );

        abierto = true;
        clVida = findViewById( R.id.clVida );
        abrirPerfil = findViewById( R.id.ibAbrirPerfil );
        sv = findViewById( R.id.svSitios );

        BaseDeDatos baseDatos = new BaseDeDatos( this );
        db = baseDatos.getWritableDatabase();

        //Utilidades.reiniciarBaseDeDatos( db );
        RellenarTablas.rellenarDatos( db );

        Utilidades.insertarPersonaje( db, 1, "Miguel", 100, 1000, 100, 100, 1, 1, -1, -1, 0, 1, 0, 0, 0, 0 );

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("--------- onResume ------------------------------");
        tv_salud.setText( UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.SALUD ) + " " );
        tv_dinero.setText( UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO ) + " " );
        tv_comida.setText( UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.COMIDA ) + " " );
        int id_casa = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_CASA_PERSONAJE_FK );
        if(id_casa != -1)
            tv_casa.setText("" + UtilidadesTablas.getColumnaString(db, BaseDeDatos.Casa.CASA_TABLE_NAME, BaseDeDatos.Casa.ID_CASA, id_casa, BaseDeDatos.Casa.DIRECCION_CASA));
        int id_vehiculo = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_VEHICULO_PERSONAJE_FK );
        if(id_vehiculo != -1)
        tv_vehiculo.setText( "" + UtilidadesTablas.getColumnaString( db, BaseDeDatos.Vehiculo.VEHICULO_TABLE_NAME, BaseDeDatos.Vehiculo.ID_VEHICULO, id_vehiculo, BaseDeDatos.Vehiculo.NOMBRE_VEHICULO ) );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            actualizar_EstadoAnimo();
        }
        int id_trabajo = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ID_TRABAJO_PERSONAJE_FK );
        if (id_trabajo != -1)
            tv_trabajo.setText( "" + UtilidadesTablas.getColumnaString( db, BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME, BaseDeDatos.Trabajo.ID_TRABAJO, id_trabajo, BaseDeDatos.Trabajo.NOMBRE_TRABAJO ) );
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void actualizar_EstadoAnimo() {
        int estado = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.ESTADO_ANIMO );
        Drawable emotion = null;
        if ( estado == 0 ) {
            emotion = getDrawable( R.drawable.emotion_muerto_100dp );
        }
        else if ( estado <= 20 && estado > 0 ) {
            emotion = getDrawable( R.drawable.emotion_aturdido_100dp );
        }
        else if ( estado <= 45 && estado > 20 ) {
            emotion = getDrawable( R.drawable.emotion_triste_100dp );
        }
        else if ( estado <= 55 && estado > 45 ) {
            emotion = getDrawable( R.drawable.emotion_inexpresivo_100dp );
        }
        else if ( estado <= 80 && estado > 55 ) {
            emotion = getDrawable( R.drawable.emotion_sonriendo_100dp );
        }
        else if ( estado <= 100 && estado > 80 ) {
            emotion = getDrawable( R.drawable.emotion_burlon_100dp );
        }
        estado_animo.setImageDrawable( emotion );
    }

    public void abrirPanel(View view) {
        ValueAnimator oa;
        if(abierto){
            abrirPerfil.setImageResource( R.drawable.ic_arrow_drop_down_black_24dp );
            oa = ObjectAnimator.ofFloat( clVida, "Y", -clVida.getHeight() );
            height = clVida.getHeight();
            clVida.setMaxHeight( 0 );
        }
        else{
            clVida.setMaxHeight( height );
            abrirPerfil.setImageResource( R.drawable.ic_arrow_drop_up_black_24dp );
        }
        abierto = !abierto;
    }

    public void abrirInventario( View view ) {
        Intent inventario = new Intent (this, InventoryActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(inventario, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        }
        else {
            startActivityForResult( inventario, 0 );
        }

    }

    public void abrirEscuela( View view ) {
        Intent escuela = new Intent (this, EducacionActivity.class);
        startActivityForResult(escuela, 0);
    }

    public void abrirTrabajo(View view) {
        Intent trabajo = new Intent ( this, TrabajoActivity.class );
        startActivityForResult( trabajo, 0 );
    }

    public void abrirTienda(View view) {
        Intent tienda = new Intent(this, TiendaActivity.class);
        startActivityForResult(tienda, 0);
    }

}
