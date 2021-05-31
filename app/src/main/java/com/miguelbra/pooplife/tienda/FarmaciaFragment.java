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
import com.miguelbra.pooplife.objetos.Medicamento;

import java.util.ArrayList;
import java.util.List;

public class FarmaciaFragment extends Fragment {

    private int mColumnCount = 1;
    RecyclerView recyclerView;
    List medicamentoList;

    MyFarmaciaRecyclerViewAdapter adapterMedicamentos;
    private FarmaciaFragment.OnListFragmentInteractionListener mListener;

    public FarmaciaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_propiedad_list, container, false);

        medicamentoList = new ArrayList<Medicamento>();
        rellenarMedicamentoList();

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            adapterMedicamentos =  new MyFarmaciaRecyclerViewAdapter( medicamentoList, mListener);
            recyclerView.setAdapter(adapterMedicamentos);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FarmaciaFragment.OnListFragmentInteractionListener) {
            mListener = (FarmaciaFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Medicamento item, int cantidad);
    }

    public void rellenarMedicamentoList(){
        SQLiteDatabase bd;
        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        bd = baseDatos.getWritableDatabase();
        Cursor c = bd.rawQuery( "select "  +
                BaseDeDatos.Medicamento.ID_MEDICAMENTO + ", " +
                BaseDeDatos.Medicamento.NOMBRE_MEDICAMENTO+ ", " +
                BaseDeDatos.Medicamento.VIDA_REGENERA + ", " +
                BaseDeDatos.Medicamento.PRECIO_MEDICAMENTO  +
                " from " + BaseDeDatos.Medicamento.MEDICAMENTO_TABLE_NAME, null );
        if(c.moveToFirst()){
            while(!c.isAfterLast()){
                Medicamento medicamento = new Medicamento(c.getInt( 0 ), c.getString( 1 ), c.getInt(2));
                medicamento.setPrecio(c.getInt(3));
                medicamentoList.add( medicamento );
                c.moveToNext();
            }
        }

    }
}