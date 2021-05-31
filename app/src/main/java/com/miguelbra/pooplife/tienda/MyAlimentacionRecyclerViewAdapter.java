package com.miguelbra.pooplife.tienda;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.objetos.Comida;
import com.miguelbra.pooplife.objetos.Medicamento;

import java.util.List;

public class MyAlimentacionRecyclerViewAdapter extends RecyclerView.Adapter<MyAlimentacionRecyclerViewAdapter.ViewHolder> {

    private final List<Comida> mValues;
    private final AlimentacionFragment.OnListFragmentInteractionListener mListener;

    public MyAlimentacionRecyclerViewAdapter(List<Comida> items, AlimentacionFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tienda_objeto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mComida = mValues.get(position);
        holder.mNombre.setText(mValues.get(position).getNombre() + "");
        holder.mPrecio.setText(mValues.get(position).getPrecio() + "");

        holder.mAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(holder.mCantidad.getText().toString()) != 0)
                    mListener.onListFragmentInteraction( holder.mComida, Integer.parseInt(holder.mCantidad.getText().toString()) );
            }
        });
        holder.mSumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = Integer.parseInt(holder.mCantidad.getText().toString());
                holder.mCantidad.setText((cantidad + 1) + "");
            }
        });
        holder.mRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cantidad = Integer.parseInt(holder.mCantidad.getText().toString());
                if (cantidad != 0)
                    holder.mCantidad.setText((cantidad - 1) + "");
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
        public final TextView mCantidad;
        public final ImageButton mAñadir;
        public final Button mSumar;
        public final Button mRestar;
        public Comida mComida;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNombre = (TextView) view.findViewById(R.id.tv_nombre_objeto_tienda);
            mPrecio = (TextView) view.findViewById(R.id.tv_precio_objeto_tienda);
            mCantidad = (TextView) view.findViewById(R.id.tv_cantidad_cesta);
            mAñadir = (ImageButton) view.findViewById(R.id.btn_añadir_cesta);
            mSumar = (Button) view.findViewById(R.id.btn_sumar_cantidad);
            mRestar = (Button) view.findViewById(R.id.btn_restar_cantidad);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}