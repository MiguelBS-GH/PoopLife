package com.miguelbra.pooplife.trabajo;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.objetos.Trabajo;
import com.miguelbra.pooplife.trabajo.ListaTrabajoFragment.OnListFragmentInteractionListener;

import java.util.List;

public class MyListaTrabajoRecyclerViewAdapter extends RecyclerView.Adapter<MyListaTrabajoRecyclerViewAdapter.ViewHolder> {

    private final List<Trabajo> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyListaTrabajoRecyclerViewAdapter(List<Trabajo> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_trabajo, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mTrabajo = mValues.get( position );
        holder.mNombre.setText( mValues.get( position ).getNombre() + " " );
        holder.mSueldo.setText( mValues.get( position ).getSueldo() + " " );
        holder.mEmpresa.setText( mValues.get( position ).getEmpresa() + " " );
        holder.mTipo.setText( mValues.get( position ).getTipo_trabajo() + " " );

        holder.mAbrir.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction( holder.mTrabajo );
                }
            }
        } );
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNombre;
        public final TextView mSueldo;
        public final TextView mEmpresa;
        public final TextView mTipo;
        public final Button mAbrir;
        public Trabajo mTrabajo;

        public ViewHolder(View view) {
            super( view );
            mView = view;
            mNombre = (TextView) view.findViewById( R.id.tv__nombre_trabajo );
            mSueldo = (TextView) view.findViewById( R.id.tv_sueldo_trabajo_list );
            mEmpresa = (TextView) view.findViewById( R.id.tv_empresa_trabajo );
            mTipo = (TextView) view.findViewById( R.id.tv_tipo_trabajo );
            mAbrir = (Button) view.findViewById( R.id.btn_abrir_perfil_trabajo );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}
