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

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.objetos.Vehiculo;

import java.util.ArrayList;
import java.util.List;


public class Vehiculo_Inv_Fragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List vehiculoList;

    private OnListFragmentInteractionListener mListener;
    MyVehiculoRecyclerViewAdapter adapterVehiculo;

    public Vehiculo_Inv_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_inv_item_list, container, false );

        vehiculoList = new ArrayList<Vehiculo>(  );
        rellenarVehiculoList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            adapterVehiculo = new MyVehiculoRecyclerViewAdapter( vehiculoList, mListener );
            recyclerView.setAdapter( adapterVehiculo );
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Vehiculo item);
    }

    public void rellenarVehiculoList(){
        SQLiteDatabase db;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        db = baseDatos.getWritableDatabase();
        Cursor id;
        Cursor c = db.rawQuery( "select " + BaseDeDatos.Inv_Vehiculo.ID_VEHICULO_FK + " from " + BaseDeDatos.Inv_Vehiculo.INV_VEHICULO_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                id = db.rawQuery( "select "  + BaseDeDatos.Vehiculo.NOMBRE_VEHICULO + ", " + BaseDeDatos.Vehiculo.VELOCIDAD_VEHICULO +  " from " + BaseDeDatos.Vehiculo.VEHICULO_TABLE_NAME + " where " + BaseDeDatos.Vehiculo.ID_VEHICULO + "=?", new String[]{c.getString( 0 )} );
                id.moveToNext();
                vehiculoList.add( new Vehiculo(c.getInt( 0 ), id.getString( 0 ), id.getInt( 1 )) );
                c.moveToNext();
            }
        }
    }
}
