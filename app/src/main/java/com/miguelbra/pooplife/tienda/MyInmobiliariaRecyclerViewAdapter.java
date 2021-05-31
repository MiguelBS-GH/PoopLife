package com.miguelbra.pooplife.tienda;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.objetos.Casa;

import java.util.List;

public class MyInmobiliariaRecyclerViewAdapter extends RecyclerView.Adapter<MyInmobiliariaRecyclerViewAdapter.ViewHolder> {

    private final List<Casa> mValues;
    private final InmobiliariaFragment.OnListFragmentInteractionListener mListener;

    public MyInmobiliariaRecyclerViewAdapter(List<Casa> items, InmobiliariaFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_propiedad, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mCasa = mValues.get(position);
        holder.mNombre.setText(mValues.get(position).getDireccion() + "");
        holder.mPrecio.setText(mValues.get(position).getPrecio() + "");

        holder.mComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentInteraction(holder.mCasa);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNombre;
        public final TextView mPrecio;
        public final Button mComprar;
        public Casa mCasa;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNombre = (TextView) view.findViewById(R.id.tv_nombre_propiedad_tienda);
            mPrecio = (TextView) view.findViewById(R.id.tv_precio_propiedad_tienda);
            mComprar = (Button) view.findViewById(R.id.btn_comprar_propiedad_tienda);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}