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
import com.miguelbra.pooplife.objetos.Ocio;

import java.util.ArrayList;
import java.util.List;

public class TiendaOcioFragment extends Fragment {


    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List ocioList;

    MyOcioTiendaRecyclerViewAdapter adapterOcio;
    private TiendaOcioFragment.OnListFragmentInteractionListener mListener;

    public TiendaOcioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propiedad_list, container, false);

        ocioList = new ArrayList<Ocio>();
        rellenarOcioList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapterOcio = new MyOcioTiendaRecyclerViewAdapter(ocioList, mListener);
            recyclerView.setAdapter(adapterOcio);
        }
        return view;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FarmaciaFragment.OnListFragmentInteractionListener) {
            mListener = (TiendaOcioFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Ocio item, int cantidad);
    }

    public void rellenarOcioList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();
        Cursor c = bd.rawQuery( "select "  +
                BaseDeDatos.Ocio.ID_OCIO + ", " +
                BaseDeDatos.Ocio.NOMBRE_OCIO+ ", " +
                BaseDeDatos.Ocio.PRECIO_OCIO  +
                " from " + BaseDeDatos.Ocio.OCIO_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                Ocio ocio = new Ocio(c.getInt( 0 ), c.getString( 1 ), c.getInt(2) );
                ocioList.add( ocio );
                c.moveToNext();
            }
        }

    }
}