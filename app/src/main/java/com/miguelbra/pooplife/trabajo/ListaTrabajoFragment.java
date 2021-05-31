package com.miguelbra.pooplife.trabajo;

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
import android.widget.FrameLayout;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.objetos.Trabajo;

import java.util.ArrayList;
import java.util.List;

public class ListaTrabajoFragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    List listaTrabajos;
    MyListaTrabajoRecyclerViewAdapter adapterTrabajo;
    View view;
    boolean basico, matematicas, letras, informatica, rural;
    String busqueda;

    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;

    public ListaTrabajoFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate( R.layout.fragment_trabajo_list, container, false );

        listaTrabajos = new ArrayList<Trabajo>(  );
        basico = getArguments().getBoolean( "basico" );
        matematicas = getArguments().getBoolean( "matematicas" );
        letras = getArguments().getBoolean( "letras" );
        informatica = getArguments().getBoolean( "informatica" );
        rural = getArguments().getBoolean( "rural" );
        busqueda = getArguments().getString( "busqueda" );

        System.out.println( " LISTA ANTES - " + basico + " - " + matematicas + " - " + letras + " - " + informatica + " - " + rural + " - " );

        rellenarTrabajoList();

        System.out.println("LLEGÉ ---------------------------------------------");

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            System.out.println("LLEGÉ ----------------------------------------------");
            adapterTrabajo = new MyListaTrabajoRecyclerViewAdapter( listaTrabajos, mListener );
            System.out.println("LLEGÉ -----------------------------------------------");
            recyclerView.setAdapter( adapterTrabajo );
            System.out.println("LLEGÉ ------------------------------------------------");
        }

        FrameLayout fl = getActivity().findViewById( R.id.frame_lista_trabajo );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fl.setClipToOutline( true );
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
        // TODO: Update argument type and name
        void onListFragmentInteraction(Trabajo item);
    }

    public void rellenarTrabajoList (  ) {

        System.out.println( " LISTA - " + basico + " - " + matematicas + " - " + letras + " - " + informatica + " - " + rural + " - " );

        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();

        Cursor c, edu;

        if( !basico && !matematicas && !letras && !informatica && !rural ) {

            c = bd.rawQuery( "select " +
                    BaseDeDatos.Trabajo.ID_TRABAJO + ", " +
                    BaseDeDatos.Trabajo.NOMBRE_TRABAJO + ", " +
                    BaseDeDatos.Trabajo.EMPRESA_TRABAJO + ", " +
                    BaseDeDatos.Trabajo.SUELDO_TRABAJO + ", " +
                    BaseDeDatos.Trabajo.EDUCACION_NECESARIA_FK + ", " +
                    BaseDeDatos.Trabajo.TIPO_TRABAJO +
                    " from " + BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME, null );

            if( c.moveToFirst() ){
                c.moveToNext();
                while(!c.isAfterLast()){
                    if( c.getString( 1 ).toLowerCase().indexOf( busqueda.toLowerCase() ) != -1 ) {
                        listaTrabajos.add(new Trabajo(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getInt(4), c.getString(5)));
                        System.out.println(c.getString(1) + " " + c.getString(5));
                    }
                    c.moveToNext();
                }
            }

        } else {

            if ( basico ) {
                System.out.println("BASICO");
                c = bd.rawQuery( "select " +
                        BaseDeDatos.Trabajo.ID_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.NOMBRE_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EMPRESA_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.SUELDO_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EDUCACION_NECESARIA_FK + ", " +
                        BaseDeDatos.Trabajo.TIPO_TRABAJO +
                        " from " + BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME +
                        " where " + BaseDeDatos.Trabajo.TIPO_TRABAJO + "=?", new String[]{"Básico"} );

                if( c.moveToFirst() ){
                    while(!c.isAfterLast()){
                        if( c.getString( 1 ).toLowerCase().indexOf( busqueda.toLowerCase() ) != -1 ) {
                            listaTrabajos.add( new Trabajo( c.getInt( 0 ), c.getString( 1 ), c.getString( 2 ), c.getInt( 3 ), c.getInt( 4 ), c.getString(5) ) );
                            System.out.println( c.getString( 1 ) + " " + c.getString(5) );
                        }
                        c.moveToNext();
                    }
                }
            }

            if ( matematicas ) {
                System.out.println("MATEMATICAS");
                c = bd.rawQuery( "select " +
                        BaseDeDatos.Trabajo.ID_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.NOMBRE_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EMPRESA_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.SUELDO_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EDUCACION_NECESARIA_FK + ", " +
                        BaseDeDatos.Trabajo.TIPO_TRABAJO +
                        " from " + BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME +
                        " where " + BaseDeDatos.Trabajo.TIPO_TRABAJO + "=?" , new String[]{BaseDeDatos.Educacion.MATEMATICAS} );

                if( c.moveToFirst() ){

                    while(!c.isAfterLast()){
                        if( c.getString( 1 ).toLowerCase().indexOf( busqueda.toLowerCase() ) != -1 ) {
                            listaTrabajos.add( new Trabajo( c.getInt( 0 ), c.getString( 1 ), c.getString( 2 ), c.getInt( 3 ), c.getInt( 4 ), c.getString(5) ) );
                            System.out.println( c.getString( 1 ) + " " + c.getString(5) );
                        }
                        c.moveToNext();
                    }
                }
            }

            if ( letras ) {
                System.out.println("LETRAS");
                c = bd.rawQuery( "select " +
                        BaseDeDatos.Trabajo.ID_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.NOMBRE_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EMPRESA_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.SUELDO_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EDUCACION_NECESARIA_FK + ", " +
                        BaseDeDatos.Trabajo.TIPO_TRABAJO +
                        " from " + BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME +
                        " where " + BaseDeDatos.Trabajo.TIPO_TRABAJO + "=?", new String[]{BaseDeDatos.Educacion.LETRAS} );

                if( c.moveToFirst() ){

                    while(!c.isAfterLast()){
                        if( c.getString( 1 ).toLowerCase().indexOf( busqueda.toLowerCase() ) != -1 ) {
                            listaTrabajos.add( new Trabajo( c.getInt( 0 ), c.getString( 1 ), c.getString( 2 ), c.getInt( 3 ), c.getInt( 4 ), c.getString(5) ) );
                            System.out.println( c.getString( 1 ) + " " + c.getString(5) );
                        }
                        c.moveToNext();
                    }
                }
            }

            if ( informatica ) {
                System.out.println("INFORMATICA");
                c = bd.rawQuery( "select " +
                        BaseDeDatos.Trabajo.ID_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.NOMBRE_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EMPRESA_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.SUELDO_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EDUCACION_NECESARIA_FK + ", " +
                        BaseDeDatos.Trabajo.TIPO_TRABAJO +
                        " from " + BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME +
                        " where " + BaseDeDatos.Trabajo.TIPO_TRABAJO + "=?", new String[]{BaseDeDatos.Educacion.INFORMÁTICA} );

                if( c.moveToFirst() ){

                    while(!c.isAfterLast()){
                        if( c.getString( 1 ).toLowerCase().indexOf( busqueda.toLowerCase() ) != -1 ) {
                            listaTrabajos.add( new Trabajo( c.getInt( 0 ), c.getString( 1 ), c.getString( 2 ), c.getInt( 3 ), c.getInt( 4 ), c.getString(5) ) );
                            System.out.println( c.getString( 1 ) + " " + c.getString(5) );
                        }
                        c.moveToNext();
                    }
                }
            }

            if ( rural ) {
                System.out.println("RURAL");
                c = bd.rawQuery( "select " +
                        BaseDeDatos.Trabajo.ID_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.NOMBRE_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EMPRESA_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.SUELDO_TRABAJO + ", " +
                        BaseDeDatos.Trabajo.EDUCACION_NECESARIA_FK + ", " +
                        BaseDeDatos.Trabajo.TIPO_TRABAJO +
                        " from " + BaseDeDatos.Trabajo.TRABAJO_TABLE_NAME +
                        " where " + BaseDeDatos.Trabajo.TIPO_TRABAJO + "=?", new String[]{BaseDeDatos.Educacion.RURAL} );

                if( c.moveToFirst() ){

                    while(!c.isAfterLast()){
                        if( c.getString( 1 ).toLowerCase().indexOf( busqueda.toLowerCase() ) != -1 ) {
                            listaTrabajos.add( new Trabajo( c.getInt( 0 ), c.getString( 1 ), c.getString( 2 ), c.getInt( 3 ), c.getInt( 4 ), c.getString(5) ) );
                            System.out.println( c.getString( 1 ) + " " + c.getString(5) );
                        }
                        c.moveToNext();
                    }
                }
            }

        }

    }
}
