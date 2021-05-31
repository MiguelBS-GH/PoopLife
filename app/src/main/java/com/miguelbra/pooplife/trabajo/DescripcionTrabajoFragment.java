package com.miguelbra.pooplife.trabajo;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.miguelbra.pooplife.R;
import com.miguelbra.pooplife.base_de_datos.BaseDeDatos;
import com.miguelbra.pooplife.base_de_datos.Utilidades;
import com.miguelbra.pooplife.base_de_datos.UtilidadesTablas;

public class DescripcionTrabajoFragment extends Fragment {

    public DescripcionTrabajoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate( R.layout.fragment_descripcion_trabajo, container, false );

        String nombre_trabajo = getArguments().getString( "nombre_trabajo" );
        String empresa_tabajo = getArguments().getString( "empresa_trabajo" );
        int sueldo_trabajo = getArguments().getInt( "sueldo_trabajo" );
        int educacion = getArguments().getInt( "educacion" );
        Double nivel_trabajo = getArguments().getDouble( "nivel_trabajo" );

        BaseDeDatos baseDatos = new BaseDeDatos( getContext() );
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String nombre_educacion = UtilidadesTablas.getColumnaString(db, BaseDeDatos.Educacion.EDUCACION_TABLE_NAME, BaseDeDatos.Educacion.ID_EDUCACION, educacion, BaseDeDatos.Educacion.NOMBRE_EDUCACION);

        boolean disponible = false;
        int existe = UtilidadesTablas.getColumnaInt(db, BaseDeDatos.Inv_Educacion.EDUCACION_INV_TABLE_NAME, BaseDeDatos.Inv_Educacion.ID_EDUCACION_FK, educacion, BaseDeDatos.Inv_Educacion.ID_EDUCACION_FK);

        TextView tv_nombre_trabajo = v.findViewById( R.id.tv_descr_nombre_trabajo );
        TextView tv_empresa_tabajo = v.findViewById( R.id.tv_descr_empresa_trabajo );
        TextView tv_sueldo_trabajo = v.findViewById( R.id.tv_descr_sueldo_tabajo );
        TextView tv_tipo_trabajo = v.findViewById( R.id.tv_descr_tipo_trabajo );
        Button coger = v.findViewById(R.id.btn_coger_trabajo);

        tv_nombre_trabajo.setText( nombre_trabajo + "" );
        tv_empresa_tabajo.setText( empresa_tabajo + "" );
        tv_sueldo_trabajo.setText( sueldo_trabajo + "" );
        if (nombre_educacion != null) {
            tv_tipo_trabajo.setText( nombre_educacion + "" );
            if(existe == -1){
                tv_tipo_trabajo.setTextColor(getResources().getColor(R.color.colorAccent));
                coger.setEnabled(false);
            }
        } else
            tv_tipo_trabajo.setText( "Nada" );
        return v;
    }
}
