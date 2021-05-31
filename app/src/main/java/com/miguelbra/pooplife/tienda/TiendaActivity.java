package com.miguelbra.pooplife.tienda;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.Utilidades;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;
import com.miguelbra.pooplife.objetos.Casa;
import com.miguelbra.pooplife.objetos.Comida;
import com.miguelbra.pooplife.objetos.Medicamento;
import com.miguelbra.pooplife.objetos.Ocio;
import com.miguelbra.pooplife.objetos.Vehiculo;

public class TiendaActivity
        extends AppCompatActivity
        implements FarmaciaFragment.OnListFragmentInteractionListener, ConcesionarioFragment.OnListFragmentInteractionListener, AlimentacionFragment.OnListFragmentInteractionListener, TiendaOcioFragment.OnListFragmentInteractionListener, InmobiliariaFragment.OnListFragmentInteractionListener {

    String vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        getSupportActionBar().hide();

        FrameLayout fl = findViewById(R.id.frame_tienda_entrada);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fl.setClipToOutline(true);
        }

        getSupportFragmentManager().beginTransaction().add( R.id.frame_tienda_entrada, new TiendaEntradaFragment() ).commit();
        vista = "entrada";

    }

    public void abrirTienda (View view) {
        switch (view.getId()){
            case R.id.btn_farmacia:
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_tienda_entrada, new FarmaciaFragment() ).commit();
                vista = "otra";
                break;
            case R.id.btn_concesionario:
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_tienda_entrada, new ConcesionarioFragment() ).commit();
                vista = "otra";
                break;
            case R.id.btn_alimentacion:
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_tienda_entrada, new AlimentacionFragment() ).commit();
                vista = "otra";
                break;
            case R.id.btn_inmobiliaria:
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_tienda_entrada, new InmobiliariaFragment() ).commit();
                vista = "otra";
                break;
            case R.id.btn_tienda_ocio:
                getSupportFragmentManager().beginTransaction().replace( R.id.frame_tienda_entrada, new TiendaOcioFragment() ).commit();
                vista = "otra";
                break;
        }
    }

    public void atras (View view) {
        if (vista.equals("entrada"))
            finish();
        else{
            getSupportFragmentManager().beginTransaction().replace( R.id.frame_tienda_entrada, new TiendaEntradaFragment() ).commit();
            vista = "entrada";
        }

    }

    @Override
    public void onListFragmentInteraction(final Medicamento medicamento, final int cantidad) {
        final Dialog seguro = new Dialog(this, R.style.custom_alert_no_results);
        seguro.setContentView(R.layout.alert_seguro_pagar);
        final TextView precio = seguro.findViewById(R.id.tv_seguro_precio);
        precio.setText( (medicamento.getPrecio() * cantidad)  + "");
        TextView pregunta = seguro.findViewById(R.id.tv_seguro_pregunta);
        pregunta.setText(R.string.seguro_comprar);
        final TextView advertencia = seguro.findViewById(R.id.tv_advertencia_dinero);
        Button si = seguro.findViewById(R.id.btn_si_pagar);
        Button no = seguro.findViewById(R.id.btn_no_pagar);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos baseDatos = new BaseDeDatos( v.getContext() );
                SQLiteDatabase db = baseDatos.getWritableDatabase();
                int dinero = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO );
                int cantidad_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Inv_Medicamento.INV_MEDICAMENTO_TABLE_NAME, BaseDeDatos.Inv_Medicamento.ID_MEDICAMENTO_FK, medicamento.getId(), BaseDeDatos.Inv_Medicamento.CANTIDAD_MEDICAMENTO );
                if (dinero < (medicamento.getPrecio() * cantidad)) {
                    advertencia.setTextSize( 14 );
                    advertencia.setPadding( 24, 8, 24, 0 );
                    advertencia.setText( R.string.no_dinero );
                } else if (cantidad_actual != -1) {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - (medicamento.getPrecio() * cantidad)) );
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Inv_Medicamento.INV_MEDICAMENTO_TABLE_NAME, BaseDeDatos.Inv_Medicamento.ID_MEDICAMENTO_FK, medicamento.getId(), BaseDeDatos.Inv_Medicamento.CANTIDAD_MEDICAMENTO, (cantidad_actual + cantidad) );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.compra_exitosa);
                    comprado.show();
                    System.out.println("Has comprado " + cantidad + " " + medicamento.getNombre());
                } else {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - (medicamento.getPrecio() * cantidad)) );
                    Utilidades.insertarMedicamentoEnInventario( db, medicamento.getId(), cantidad );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.compra_exitosa);
                    comprado.show();
                    System.out.println("Has comprado " + cantidad + " " + medicamento.getNombre());
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguro.dismiss();
            }
        });
        seguro.show();
    }

    @Override
    public void onListFragmentInteraction(final Vehiculo vehiculo) {
        final Dialog seguro = new Dialog(this, R.style.custom_alert_no_results);
        seguro.setContentView(R.layout.alert_seguro_pagar);
        final TextView precio = seguro.findViewById(R.id.tv_seguro_precio);
        precio.setText(vehiculo.getPrecio() + "");
        TextView pregunta = seguro.findViewById(R.id.tv_seguro_pregunta);
        pregunta.setText(R.string.seguro_comprar_coche);
        final TextView advertencia = seguro.findViewById(R.id.tv_advertencia_dinero);
        Button si = seguro.findViewById(R.id.btn_si_pagar);
        Button no = seguro.findViewById(R.id.btn_no_pagar);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos baseDatos = new BaseDeDatos( v.getContext() );
                SQLiteDatabase db = baseDatos.getWritableDatabase();
                int dinero = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO );
                int existe = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Inv_Vehiculo.INV_VEHICULO_TABLE_NAME, BaseDeDatos.Inv_Vehiculo.ID_VEHICULO_FK, vehiculo.getId(), BaseDeDatos.Inv_Vehiculo.ID_VEHICULO_FK );
                if (dinero < vehiculo.getPrecio()) {
                    advertencia.setTextSize( 14 );
                    advertencia.setPadding( 24, 8, 24, 0 );
                    advertencia.setText( R.string.no_dinero );
                } else if (existe != -1) {
                    advertencia.setTextSize( 14 );
                    advertencia.setPadding( 24, 8, 24, 0 );
                    advertencia.setText(R.string.tienes_coche);
                } else {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - vehiculo.getPrecio()) );
                    Utilidades.insertarVehiculoEnInventario( db, vehiculo.getId() );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.comprado_coche);
                    hecho.setText(hecho.getText().toString() + " " + vehiculo.getNombre());
                    comprado.show();
                    System.out.println("Has comprado un coche");
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguro.dismiss();
            }
        });
        seguro.show();
    }

    @Override
    public void onListFragmentInteraction(final Comida comida, final int cantidad) {
        final Dialog seguro = new Dialog(this, R.style.custom_alert_no_results);
        seguro.setContentView(R.layout.alert_seguro_pagar);
        final TextView precio = seguro.findViewById(R.id.tv_seguro_precio);
        precio.setText( (comida.getPrecio() * cantidad)  + "");
        TextView pregunta = seguro.findViewById(R.id.tv_seguro_pregunta);
        pregunta.setText(R.string.seguro_comprar);
        final TextView advertencia = seguro.findViewById(R.id.tv_advertencia_dinero);
        Button si = seguro.findViewById(R.id.btn_si_pagar);
        Button no = seguro.findViewById(R.id.btn_no_pagar);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos baseDatos = new BaseDeDatos( v.getContext() );
                SQLiteDatabase db = baseDatos.getWritableDatabase();
                int dinero = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO );
                int cantidad_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Inv_Comida.INV_COMIDA_TABLE_NAME, BaseDeDatos.Inv_Comida.ID_COMIDA_FK, comida.getId(), BaseDeDatos.Inv_Comida.CANTIDAD_COMIDA );
                if (dinero < (comida.getPrecio() * cantidad)) {
                    advertencia.setTextSize( 14 );
                    advertencia.setPadding( 24, 8, 24, 0 );
                    advertencia.setText( R.string.no_dinero );
                } else if (cantidad_actual != -1) {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - (comida.getPrecio() * cantidad)) );
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Inv_Comida.INV_COMIDA_TABLE_NAME, BaseDeDatos.Inv_Comida.ID_COMIDA_FK, comida.getId(), BaseDeDatos.Inv_Comida.CANTIDAD_COMIDA, (cantidad_actual + cantidad) );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.compra_exitosa);
                    comprado.show();
                    System.out.println("Has comprado " + cantidad + " " + comida.getNombre());
                } else {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - (comida.getPrecio() * cantidad)) );
                    Utilidades.insertarComidaEnInventario( db, comida.getId(), cantidad );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.compra_exitosa);
                    comprado.show();
                    System.out.println("Has comprado " + cantidad + " " + comida.getNombre());
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguro.dismiss();
            }
        });
        seguro.show();
    }

    @Override
    public void onListFragmentInteraction(final Ocio ocio, final int cantidad) {
        final Dialog seguro = new Dialog(this, R.style.custom_alert_no_results);
        seguro.setContentView(R.layout.alert_seguro_pagar);
        final TextView precio = seguro.findViewById(R.id.tv_seguro_precio);
        precio.setText( (ocio.getPrecio() * cantidad)  + "");
        TextView pregunta = seguro.findViewById(R.id.tv_seguro_pregunta);
        pregunta.setText(R.string.seguro_comprar);
        final TextView advertencia = seguro.findViewById(R.id.tv_advertencia_dinero);
        Button si = seguro.findViewById(R.id.btn_si_pagar);
        Button no = seguro.findViewById(R.id.btn_no_pagar);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos baseDatos = new BaseDeDatos( v.getContext() );
                SQLiteDatabase db = baseDatos.getWritableDatabase();
                int dinero = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO );
                int cantidad_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Inv_Ocio.INV_OCIO_TABLE_NAME, BaseDeDatos.Inv_Ocio.ID_OCIO_FK, ocio.getId(), BaseDeDatos.Inv_Ocio.CANTIDAD_OCIO );
                if (dinero < (ocio.getPrecio() * cantidad)) {
                    advertencia.setTextSize( 14 );
                    advertencia.setPadding( 24, 8, 24, 0 );
                    advertencia.setText( R.string.no_dinero );
                } else if (cantidad_actual != -1) {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - (ocio.getPrecio() * cantidad)) );
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Inv_Ocio.INV_OCIO_TABLE_NAME, BaseDeDatos.Inv_Ocio.ID_OCIO_FK, ocio.getId(), BaseDeDatos.Inv_Ocio.CANTIDAD_OCIO, (cantidad_actual + cantidad) );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.compra_exitosa);
                    comprado.show();
                    System.out.println("Has comprado " + cantidad + " " + ocio.getNombre());
                } else {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - (ocio.getPrecio() * cantidad)) );
                    Utilidades.insertarOcioEnInventario( db, ocio.getId(), cantidad );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.compra_exitosa);
                    comprado.show();
                    System.out.println("Has comprado " + cantidad + " " + ocio.getNombre());
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguro.dismiss();
            }
        });
        seguro.show();
    }

    @Override
    public void onListFragmentInteraction(final Casa casa) {
        final Dialog seguro = new Dialog(this, R.style.custom_alert_no_results);
        seguro.setContentView(R.layout.alert_seguro_pagar);
        final TextView precio = seguro.findViewById(R.id.tv_seguro_precio);
        precio.setText(casa.getPrecio() + "");
        TextView pregunta = seguro.findViewById(R.id.tv_seguro_pregunta);
        pregunta.setText(R.string.seguro_comprar_coche);
        final TextView advertencia = seguro.findViewById(R.id.tv_advertencia_dinero);
        Button si = seguro.findViewById(R.id.btn_si_pagar);
        Button no = seguro.findViewById(R.id.btn_no_pagar);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDeDatos baseDatos = new BaseDeDatos( v.getContext() );
                SQLiteDatabase db = baseDatos.getWritableDatabase();
                int dinero = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO );
                int existe = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Inv_Casa.INV_CASA_TABLE_NAME, BaseDeDatos.Inv_Casa.ID_CASA_FK, casa.getId(), BaseDeDatos.Inv_Casa.ID_CASA_FK );
                if (dinero < casa.getPrecio()) {
                    advertencia.setTextSize( 14 );
                    advertencia.setPadding( 24, 8, 24, 0 );
                    advertencia.setText( R.string.no_dinero );
                } else if (existe != -1) {
                    advertencia.setTextSize( 14 );
                    advertencia.setPadding( 24, 8, 24, 0 );
                    advertencia.setText(R.string.tienes_casa);
                } else {
                    UtilidadesTablas.updateColumnaInteger( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.DINERO, (dinero - casa.getPrecio()) );
                    Utilidades.insertarCasaEnInventario( db, casa.getId() );
                    seguro.dismiss();
                    Dialog comprado = new Dialog(v.getContext(), R.style.custom_alert_no_results);
                    comprado.setContentView(R.layout.alert_advertencia_custom);
                    TextView hecho = comprado.findViewById(R.id.tv_advertencia_custom);
                    hecho.setText(R.string.comprado_casa);
                    hecho.setText(hecho.getText().toString() + " " + casa.getDireccion());
                    comprado.show();
                    System.out.println("Has comprado un coche");
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seguro.dismiss();
            }
        });
        seguro.show();
    }
}