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
import com.miguelbra.pooplife.objetos.Medicamento;

import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class Medicamento_Inv_Fragment extends Fragment {

    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List medicamentoList;

    private OnListFragmentInteractionListener mListener;
    private MyMedicamentoRecyclerViewAdapter adapterMedicamento;

    public Medicamento_Inv_Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_inv_item_list, container, false );

        medicamentoList = new ArrayList<Medicamento>();
        rellenarMedicamentoList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager( new LinearLayoutManager( context ) );
            } else {
                recyclerView.setLayoutManager( new GridLayoutManager( context, mColumnCount ) );
            }
            adapterMedicamento = new MyMedicamentoRecyclerViewAdapter( medicamentoList, mListener );
            recyclerView.setAdapter( adapterMedicamento );
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
        void onListFragmentInteraction(Medicamento item, TextView tv_cantidad);
    }

    public void rellenarMedicamentoList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();
        Cursor id;
        Cursor c = bd.rawQuery( "select " + BaseDeDatos.Inv_Medicamento.ID_MEDICAMENTO_FK + ", " + BaseDeDatos.Inv_Medicamento.CANTIDAD_MEDICAMENTO + " from " + BaseDeDatos.Inv_Medicamento.INV_MEDICAMENTO_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                id = bd.rawQuery( "select "  + BaseDeDatos.Medicamento.NOMBRE_MEDICAMENTO+ ", " + BaseDeDatos.Medicamento.VIDA_REGENERA  + " from " + BaseDeDatos.Medicamento.MEDICAMENTO_TABLE_NAME + " where " + BaseDeDatos.Medicamento.ID_MEDICAMENTO + "=?", new String[]{c.getString( 0 )} );
                id.moveToNext();
                Medicamento medicamento = new Medicamento(c.getInt( 0 ), id.getString( 0 ), id.getInt( 1 ));
                medicamento.setCantidad(c.getInt(1));
                medicamentoList.add( medicamento );
                c.moveToNext();
            }
        }

    }
}
