package com.miguelbra.pooplife.trabajo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;

public class TrabajarFragment extends Fragment {

    public TrabajarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate( R.layout.fragment_trabajar, container, false );

        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        int exp_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.EXP_TRABAJO );
        int nivel_actual = UtilidadesTablas.getColumnaInt( db, BaseDeDatos.Personaje.PERSONAJE_TABLE_NAME, BaseDeDatos.Personaje.ID_PERSONAJE, 1, BaseDeDatos.Personaje.NIVEL_TRABAJO );

        String nombre_trabajo = getArguments().getString( "nombre_trabajo" );
        String empresa_trabajo = getArguments().getString( "empresa_trabajo" );
        int sueldo_trabajo = getArguments().getInt( "sueldo_trabajo" );

        TextView tv_nombre_trabajo = v.findViewById( R.id.tv_nombre_trabajo_trabajar );
        TextView tv_empresa_trabajo = v.findViewById( R.id.tv_nombre_empresa_trabajar );
        TextView tv_sueldo_trabajo = v.findViewById( R.id.tv_sueldo_trabajar );

        tv_nombre_trabajo.setText( nombre_trabajo + " " );
        tv_empresa_trabajo.setText( empresa_trabajo + " " );
        tv_sueldo_trabajo.setText( sueldo_trabajo + " " );

        FrameLayout fl = v.findViewById( R.id.fl_fondo_papel );
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fl.setClipToOutline( true );
        }

        TextView tv_nivel = v.findViewById( R.id.tv_nivel_actual_trabajo );
        tv_nivel.setText( nivel_actual + "" );
        ProgressBar pb_exp = v.findViewById(R.id.pb_exp);
        pb_exp.setMax(100*nivel_actual);
        pb_exp.setProgress( exp_actual % 100 );
        TextView tv_siguiente_nivel = v.findViewById(R.id.tv_sueldo_sig_nivel);
        tv_siguiente_nivel.setText(sueldo_trabajo * (nivel_actual + 1) + "");

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume Trabajar");
    }
}
