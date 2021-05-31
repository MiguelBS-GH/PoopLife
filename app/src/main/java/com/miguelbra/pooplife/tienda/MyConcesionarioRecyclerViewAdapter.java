package com.miguelbra.pooplife.tienda;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.objetos.Vehiculo;

import java.util.List;


public class MyConcesionarioRecyclerViewAdapter extends RecyclerView.Adapter<MyConcesionarioRecyclerViewAdapter.ViewHolder> {

    private final List<Vehiculo> mValues;
    private final ConcesionarioFragment.OnListFragmentInteractionListener mListener;

    public MyConcesionarioRecyclerViewAdapter(List<Vehiculo> items, ConcesionarioFragment.OnListFragmentInteractionListener listener) {
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
        holder.mVehiculo = mValues.get(position);
        holder.mNombre.setText(mValues.get(position).getNombre() + "");
        holder.mPrecio.setText(mValues.get(position).getPrecio() + "");

        holder.mComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onListFragmentInteraction(holder.mVehiculo);
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
        public Vehiculo mVehiculo;

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