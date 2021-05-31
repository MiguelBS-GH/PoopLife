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
import com.miguelbra.pooplife.objetos.Ocio;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class Ocio_Inv_Fragment extends Fragment {

    // TODO: Customize parameters
    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List ocioList;

    private OnListFragmentInteractionListener mListener;
    MyOcioRecyclerViewAdapter adapterOcio;

    public Ocio_Inv_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_inv_item_list, container, false );

        ocioList = new ArrayList<Ocio>(  );
        rellenarOcioList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            adapterOcio = new MyOcioRecyclerViewAdapter(ocioList , mListener );
            recyclerView.setAdapter( adapterOcio );
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
        void onListFragmentInteraction(Ocio item, TextView tv_cantidad);
    }

    public void rellenarOcioList(){
        SQLiteDatabase db;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        db = baseDatos.getWritableDatabase();
        Cursor id;
        Cursor c = db.rawQuery( "select " + BaseDeDatos.Inv_Ocio.ID_OCIO_FK + ", " + BaseDeDatos.Inv_Ocio.CANTIDAD_OCIO + " from " + BaseDeDatos.Inv_Ocio.INV_OCIO_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                id = db.rawQuery( "select "  + BaseDeDatos.Ocio.NOMBRE_OCIO + ", " + BaseDeDatos.Ocio.ESTADO_REGENERA + ", " + BaseDeDatos.Ocio.COMIDA_CONSUME_OCIO + ", " + BaseDeDatos.Ocio.PROB_ROTO + " from " + BaseDeDatos.Ocio.OCIO_TABLE_NAME + " where " + BaseDeDatos.Ocio.ID_OCIO + "=?", new String[]{c.getString( 0 )} );
                id.moveToNext();
                Ocio ocio = new Ocio(c.getInt( 0 ), id.getString( 0 ), c.getInt( 1), id.getInt( 1 ), id.getInt( 2 ), id.getDouble( 3 ) );
                ocioList.add( ocio );
                c.moveToNext();
            }
        }
    }
}
