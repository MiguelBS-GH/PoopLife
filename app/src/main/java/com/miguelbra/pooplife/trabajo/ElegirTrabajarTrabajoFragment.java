package com.miguelbra.pooplife.trabajo;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.miguelbra.pooplife.R;

public class ElegirTrabajarTrabajoFragment extends Fragment {

    public ElegirTrabajarTrabajoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate( R.layout.fragment_elegir_trabajar_trabajo, container, false );
    }
}
