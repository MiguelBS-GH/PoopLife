package com.miguelbra.pooplife.inventario;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.inventario.Comida_Inv_Fragment.OnListFragmentInteractionListener;
import com.miguelbra.pooplife.objetos.Comida;

import java.util.List;

public class MyComidaRecyclerViewAdapter extends RecyclerView.Adapter<MyComidaRecyclerViewAdapter.ViewHolder> {

    private final List<Comida> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyComidaRecyclerViewAdapter(List<Comida> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_inv_item_simple_, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mComida = mValues.get( position );
        holder.mNombreView.setText( "" + mValues.get( position ).getNombre() );
        holder.mCantidadView.setText( "" + mValues.get( position ).getCantidad() );

        holder.mBtnUsar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction( holder.mComida, holder.mCantidadView );
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
        public final TextView mNombreView;
        public final TextView mCantidadView;
        public final Button mBtnUsar;
        public Comida mComida;

        public ViewHolder(View view) {
            super( view );
            mView = view;
            mNombreView = (TextView) view.findViewById( R.id.tv_nombre_objeto );
            mCantidadView = (TextView) view.findViewById( R.id.tv_cantidad_objeto );
            mBtnUsar = view.findViewById( R.id.btn_usar );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombreView.getText() + "'";
        }
    }
}
