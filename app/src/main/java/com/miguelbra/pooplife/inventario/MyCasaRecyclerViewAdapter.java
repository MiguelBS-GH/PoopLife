package com.miguelbra.pooplife.inventario;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.inventario.Casa_Inv_Fragment.OnListFragmentInteractionListener;
import com.miguelbra.pooplife.objetos.Casa;

import java.util.List;

public class MyCasaRecyclerViewAdapter extends RecyclerView.Adapter<MyCasaRecyclerViewAdapter.ViewHolder> {

    private final List<Casa> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyCasaRecyclerViewAdapter(List<Casa> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_inv_casa_, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mCasa = mValues.get( position );
        holder.mNombre.setText( mValues.get( position ).getDireccion() );
        holder.mTamaño.setText( "" + mValues.get( position ).getTamaño() + " " );

        holder.mBtnUsar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction( holder.mCasa );
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
        public final TextView mTamaño;
        public final Button mBtnUsar;
        public Casa mCasa;

        public ViewHolder(View view) {
            super( view );
            mView = view;
            mNombre = (TextView) view.findViewById( R.id.tv_nombre_casa );
            mTamaño = (TextView) view.findViewById( R.id.tv_tamaño );
            mBtnUsar = view.findViewById( R.id.btn_usar_casa );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}
