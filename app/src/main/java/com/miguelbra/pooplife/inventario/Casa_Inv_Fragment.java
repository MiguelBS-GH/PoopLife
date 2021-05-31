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
import com.miguelbra.pooplife.objetos.Casa;

import java.util.ArrayList;
import java.util.List;


public class Casa_Inv_Fragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List casaList;

    private OnListFragmentInteractionListener mListener;
    MyCasaRecyclerViewAdapter adapterCasa;

    public Casa_Inv_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_inv_item_list, container, false );

        casaList = new ArrayList<Casa>(  );
        rellenarCasaList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            adapterCasa = new MyCasaRecyclerViewAdapter( casaList , mListener );
            recyclerView.setAdapter( adapterCasa );
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
        void onListFragmentInteraction(Casa item);
    }

    public void rellenarCasaList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();

        Cursor id;
        Cursor c = bd.rawQuery( "select " + BaseDeDatos.Inv_Casa.ID_CASA_FK + " from " + BaseDeDatos.Inv_Casa.INV_CASA_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                id = bd.rawQuery( "select "  + BaseDeDatos.Casa.DIRECCION_CASA + ", " + BaseDeDatos.Casa.TAMAÑO_CASA + " from " + BaseDeDatos.Casa.CASA_TABLE_NAME + " where " + BaseDeDatos.Casa.ID_CASA + "=?", new String[]{c.getString( 0 )} );
                id.moveToNext();
                casaList.add( new Casa(c.getInt( 0 ), id.getString( 0 ), id.getInt( 1 )) );
                c.moveToNext();
            }
        }

    }

}
