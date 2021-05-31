package com.miguelbra.pooplife.inventario;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.objetos.Comida;

import java.util.ArrayList;
import java.util.List;

public class Comida_Inv_Fragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List comidaList;


    private OnListFragmentInteractionListener mListener;
    private MyComidaRecyclerViewAdapter adapterComida;

    public Comida_Inv_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_inv_item_list, container, false );

        comidaList = new ArrayList<Comida>(  );
        rellenarComidaList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            adapterComida = new MyComidaRecyclerViewAdapter( comidaList, mListener );
            recyclerView.setAdapter( adapterComida );
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
        void onListFragmentInteraction(Comida item, TextView tv_cantidad);
    }

    public void rellenarComidaList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();
        Cursor id;
        Cursor c = bd.rawQuery( "select " + BaseDeDatos.Inv_Comida.ID_COMIDA_FK + ", " + BaseDeDatos.Inv_Comida.CANTIDAD_COMIDA + " from " + BaseDeDatos.Inv_Comida.INV_COMIDA_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                id = bd.rawQuery( "select "  + BaseDeDatos.Comida.NOMBRE_COMIDA + ", " + BaseDeDatos.Comida.COMIDA_REGENERA + " from " + BaseDeDatos.Comida.COMIDA_TABLE_NAME + " where " + BaseDeDatos.Comida.ID_COMIDA + "=?", new String[]{c.getString( 0 )} );
                id.moveToNext();
                Comida comida = new Comida(c.getInt( 0 ), id.getString( 0 ), id.getInt( 1 ));
                comida.setCantidad(c.getInt( 1 ));
                comidaList.add( comida );
                c.moveToNext();
            }
        }

    }
}
