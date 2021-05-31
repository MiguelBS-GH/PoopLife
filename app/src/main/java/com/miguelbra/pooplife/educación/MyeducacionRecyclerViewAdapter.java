package com.miguelbra.pooplife.educación;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.educación.EducacionFragment.OnListFragmentInteractionListener;
import com.miguelbra.pooplife.objetos.Educacion;

import java.util.List;

public class MyeducacionRecyclerViewAdapter extends RecyclerView.Adapter<MyeducacionRecyclerViewAdapter.ViewHolder> {

    private final List<Educacion> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyeducacionRecyclerViewAdapter(List<Educacion> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( parent.getContext() )
                .inflate( R.layout.fragment_educacion, parent, false );
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mEducacion = mValues.get( position );
        holder.mNombre.setText( mValues.get( position ).getNombre() );
        holder.mTipo.setText( mValues.get( position ).getTipo() + "  " );

        if(holder.mEducacion.isDisponible()){
            if (!mValues.get( position ).isEstudiado()) {
                holder.mPrecio.setText( "" + mValues.get( position ).getPrecio() + " " );
                holder.mEstudiar.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.onListFragmentInteraction( holder.mEducacion );
                    }
                } );
            } else {
                holder.mPrecio.setText( "0 " );
                holder.mEstudiar.setText( R.string.estado_estudiado );
                holder.mEstudiar.setEnabled( false );
                holder.mCL.setBackgroundResource( R.drawable.educacion_estudiado_style );
            }
        } else {
            System.out.println("NO DISPONIBLE");
            holder.mNoNivel.setText(R.string.necesario_nivel);
            holder.mNoNivel.setVisibility(View.VISIBLE);
            holder.mNoNivel.setText(holder.mNoNivel.getText().toString() + " " + (holder.mEducacion.getNivel() - 1) + " " + holder.mEducacion.getTipo());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.mNoNivel.setClipToOutline(true);
            }
            holder.mEstudiar.setEnabled( false );
        }

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ConstraintLayout mCL;
        public final TextView mNombre;
        public final TextView mTipo;
        public final TextView mPrecio;
        public final TextView mNoNivel;
        public final Button mEstudiar;
        public Educacion mEducacion;

        public ViewHolder(View view) {
            super( view );
            mView = view;
            mCL = (ConstraintLayout) view.findViewById( R.id.cl_fondo_educacion );
            mNombre = (TextView) view.findViewById( R.id.tv_nombre_educacion );
            mTipo = (TextView) view.findViewById( R.id.tv_tipo_educacion );
            mPrecio = (TextView) view.findViewById( R.id.tv_precio_educacion );
            mNoNivel = (TextView) view.findViewById(R.id.tv_no_nivel);
            mEstudiar = view.findViewById( R.id.btn_estudiar );
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}
