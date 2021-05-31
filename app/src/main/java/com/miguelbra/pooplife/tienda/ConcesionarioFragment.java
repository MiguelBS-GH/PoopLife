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
import com.miguelbra.pooplife.objetos.Vehiculo;

import java.util.ArrayList;
import java.util.List;


public class ConcesionarioFragment extends Fragment {

    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List concesionarioList;

    MyConcesionarioRecyclerViewAdapter adapterVehiculo;
    private ConcesionarioFragment.OnListFragmentInteractionListener mListener;

    public ConcesionarioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propiedad_list, container, false);

        concesionarioList = new ArrayList<Vehiculo>();
        rellenarConcesionarioList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapterVehiculo = new MyConcesionarioRecyclerViewAdapter(concesionarioList, mListener);
            recyclerView.setAdapter(adapterVehiculo);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ConcesionarioFragment.OnListFragmentInteractionListener) {
            mListener = (ConcesionarioFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Vehiculo vehiculo);
    }

    public void rellenarConcesionarioList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();
        Cursor c = bd.rawQuery( "select "  +
                BaseDeDatos.Vehiculo.ID_VEHICULO + ", " +
                BaseDeDatos.Vehiculo.NOMBRE_VEHICULO + ", " +
                BaseDeDatos.Vehiculo.VELOCIDAD_VEHICULO + ", " +
                BaseDeDatos.Vehiculo.PRECIO_VEHICULO  +
                " from " + BaseDeDatos.Vehiculo.VEHICULO_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                Vehiculo vehiculo = new Vehiculo(c.getInt( 0 ), c.getString( 1 ), c.getInt(2));
                vehiculo.setPrecio(c.getInt(3));
                concesionarioList.add( vehiculo );
                c.moveToNext();
            }
        }

    }
}