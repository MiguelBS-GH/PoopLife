package com.miguelbra.pooplife.tienda;

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
import android.widget.ArrayAdapter;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.objetos.Comida;
import com.miguelbra.pooplife.objetos.Medicamento;

import java.util.ArrayList;
import java.util.List;


public class AlimentacionFragment extends Fragment {

    private int mColumnCount = 1;
    List comidaList;
    MyAlimentacionRecyclerViewAdapter comidaAdapter;

    RecyclerView recyclerView;
    private AlimentacionFragment.OnListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propiedad_list, container, false);

        comidaList = new ArrayList<Comida>();
        rellenarComidaList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            comidaAdapter = new MyAlimentacionRecyclerViewAdapter(comidaList, mListener);
            recyclerView.setAdapter(comidaAdapter);
        }
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AlimentacionFragment.OnListFragmentInteractionListener) {
            mListener = (AlimentacionFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Comida comida, int cantidad);
    }

    public void rellenarComidaList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();
        Cursor c = bd.rawQuery( "select "  +
                BaseDeDatos.Comida.ID_COMIDA + ", " +
                BaseDeDatos.Comida.NOMBRE_COMIDA+ ", " +
                BaseDeDatos.Comida.COMIDA_REGENERA + ", " +
                BaseDeDatos.Comida.PRECIO_COMIDA  +
                " from " + BaseDeDatos.Comida.COMIDA_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                Comida comida = new Comida(c.getInt( 0 ), c.getString( 1 ), c.getInt(2));
                comida.setPrecio(c.getInt(3));
                comidaList.add( comida );
                c.moveToNext();
            }
        }

    }
}