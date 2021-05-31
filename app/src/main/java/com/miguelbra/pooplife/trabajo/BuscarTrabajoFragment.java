package com.miguelbra.pooplife.trabajo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.miguelbra.pooplife.R;

public class BuscarTrabajoFragment extends Fragment {

    public BuscarTrabajoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate( R.layout.fragment_buscar_trabajo, container, false );

        Bundle args = new Bundle(  );
        args.putBoolean( "basico", getArguments().getBoolean( "basico" ) );
        args.putBoolean( "matematicas", getArguments().getBoolean( "matematicas" ) );
        args.putBoolean( "letras", getArguments().getBoolean( "letras" ) );
        args.putBoolean( "informatica", getArguments().getBoolean( "informatica" ) );
        args.putBoolean( "rural", getArguments().getBoolean( "rural" ) );
        args.putString( "busqueda", getArguments().getString( "busqueda" ) );

        System.out.println( " entre - " +
                getArguments().getBoolean( "basico" ) + " - " +
                getArguments().getBoolean( "matematicas" ) + " - " +
                getArguments().getBoolean( "letras" ) + " - " +
                getArguments().getBoolean( "informatica" ) + " - " +
                getArguments().getBoolean( "rural" ) + " - " );

        ListaTrabajoFragment ltf = new ListaTrabajoFragment();
        ltf.setArguments( args );

        getFragmentManager().beginTransaction().add( R.id.frame_lista_trabajo, ltf ).commit();

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

    }



}
