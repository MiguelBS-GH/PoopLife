package com.miguelbra.pooplife.educación;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.Utilidades;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;
import com.miguelbra.pooplife.objetos.Educacion;

import java.util.ArrayList;
import java.util.List;

public class EducacionFragment extends Fragment {


    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List educacionList;
    EditText et_busqueda;
    String busqueda;

    private OnListFragmentInteractionListener mListener;
    MyeducacionRecyclerViewAdapter adapterEducacion;

    public EducacionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_educacion_list, container, false );

        busqueda = getArguments().getString( "busqueda" ).toLowerCase();

        educacionList = new ArrayList<Educacion>();
        rellenarEducacionList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }

            adapterEducacion = new MyeducacionRecyclerViewAdapter( educacionList, mListener );
            recyclerView.setAdapter( adapterEducacion );
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Educacion item);
    }

    public void rellenarEducacionList(){
        SQLiteDatabase db;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        db = baseDatos.getWritableDatabase();

        Cursor id;
        Cursor c;

            c = db.rawQuery( "select " +
                    BaseDeDatos.Educacion.ID_EDUCACION + ", " +
                    BaseDeDatos.Educacion.NOMBRE_EDUCACION + ", " +
                    BaseDeDatos.Educacion.TIPO_EDUCACION + ", " +
                    BaseDeDatos.Educacion.NIVEL_EDUCACION + "," +
                    BaseDeDatos.Educacion.PRECIO_EDUCACION + ", " +
                    BaseDeDatos.Educacion.COMIDA_CONSUME_ESTUDIAR +
                    " from " + BaseDeDatos.Educacion.EDUCACION_TABLE_NAME, null );

        if( c.moveToFirst() ){
            while(!c.isAfterLast()){
                boolean estudiado = false;
                boolean disponible = false;
                if ( c.getString( 1 ).toLowerCase().indexOf( busqueda ) != -1 || c.getString( 2 ).toLowerCase().indexOf( busqueda ) != -1 ) {
                    id = db.rawQuery( "select "  +
                            BaseDeDatos.Inv_Educacion.ID_EDUCACION_FK +
                            " from " + BaseDeDatos.Inv_Educacion.EDUCACION_INV_TABLE_NAME +
                            " where " + BaseDeDatos.Inv_Educacion.ID_EDUCACION_FK + "=?", new String[]{c.getString( 0 )} );
                    if( id.moveToFirst() ){
                        estudiado = true;
                    }
                    else {
                        estudiado = false;
                    }
                    System.out.println(disponible);
                    int nivel = 0;
                    switch (c.getString(2)) {
                        case BaseDeDatos.Educacion.MATEMATICAS:
                            nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.MATEMATICAS_PERSONAJE);
                            if( nivel >= (c.getInt(3) - 1) ) {
                                disponible = true;
                            }
                            break;
                        case BaseDeDatos.Educacion.LETRAS:
                            nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.LETRAS_PERSONAJE);
                            if( nivel >= (c.getInt(3) - 1) ) {
                                disponible = true;
                            }
                            break;
                        case BaseDeDatos.Educacion.RURAL:
                            nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.RURAL_PERSONAJE);
                            if( nivel >= (c.getInt(3) - 1) ) {
                                disponible = true;
                            }
                            break;
                        case BaseDeDatos.Educacion.INFORMÁTICA:
                            nivel = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.INFORMATICA_PERSONAJE);
                            if( nivel >= (c.getInt(3) - 1) ) {
                                disponible = true;
                            }
                            break;
                    }

                    // HACER DISPONIBILIDAD DE EDUCACION SEGUN NIVEL

                    educacionList.add( new Educacion( c.getInt( 0 ), c.getString( 1 ), c.getString( 2 ), c.getInt(3), estudiado, c.getInt( 4 ), c.getInt( 5 ), disponible ) );
                    System.out.println(c.getString( 1 ) + " estudiado:" + estudiado + " - disponible:" + c.getInt(3) + " <= " + nivel + disponible + " ---------------");
                }
                c.moveToNext();
            }
        }

        if(educacionList.size() == 0) {
            AlertDialog.Builder ad = new AlertDialog.Builder( getActivity(), R.style.custom_alert_no_results );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ad.setView( R.layout.alert_no_resultados_layout );
            }
            //ad.setMessage( R.string.sin_resultados );
            ad.show();
            busqueda = "";
            rellenarEducacionList();
        }

        System.out.println("----------------------------------------------------------------");
        for(int x = 0; x < educacionList.size(); x++){
            System.out.println( (x + 1) + " - " + educacionList.get( x ) );
        }

    }
}
