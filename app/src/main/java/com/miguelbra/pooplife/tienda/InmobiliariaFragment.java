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

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.objetos.Casa;

import java.util.ArrayList;
import java.util.List;

public class InmobiliariaFragment extends Fragment {

    private int mColumnCount = 1;
    List casaList;
    MyInmobiliariaRecyclerViewAdapter casaAdapter;

    RecyclerView recyclerView;
    private InmobiliariaFragment.OnListFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propiedad_list, container, false);

        casaList = new ArrayList<Casa>();
        rellenarCasaList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            casaAdapter = new MyInmobiliariaRecyclerViewAdapter(casaList, mListener);
            recyclerView.setAdapter(casaAdapter);
        }
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InmobiliariaFragment.OnListFragmentInteractionListener) {
            mListener = (InmobiliariaFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Casa casa);
    }

    public void rellenarCasaList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();
        Cursor c = bd.rawQuery( "select "  +
                BaseDeDatos.Casa.ID_CASA + ", " +
                BaseDeDatos.Casa.DIRECCION_CASA + ", " +
                BaseDeDatos.Casa.PRECIO_CASA  +
                " from " + BaseDeDatos.Casa.CASA_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                Casa casa = new Casa(c.getInt( 0 ), c.getInt(2), c.getString( 1 ));
                casaList.add( casa );
                c.moveToNext();
            }
        }

    }
}