package com.miguelbra.pooplife.inventario;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.inventario.Vehiculo_Inv_Fragment.OnListFragmentInteractionListener;
import com.miguelbra.pooplife.objetos.Vehiculo;

import java.util.List;

public class MyVehiculoRecyclerViewAdapter extends RecyclerView.Adapter<MyVehiculoRecyclerViewAdapter.ViewHolder> {

    private final List<Vehiculo> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyVehiculoRecyclerViewAdapter(List<Vehiculo> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_inv_vehiculo, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mVehiculo = mValues.get( position );
        holder.mNombre.setText( mValues.get( position ).getNombre());
        holder.mVelociad.setText( "" + mValues.get( position ).getVelocidad() + " " );


        holder.mBtnUsar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction( holder.mVehiculo );
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
        public final TextView mVelociad;
        public final Button mBtnUsar;
        public Vehiculo mVehiculo;

        public ViewHolder(View view) {
            super( view );
            mView = view;
            mNombre = (TextView) view.findViewById( R.id.tv_nombre_vehiculo );
            mVelociad = (TextView) view.findViewById( R.id.tv_Velocidad );
            mBtnUsar = view.findViewById( R.id.btn_usar_vehiculo );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}
