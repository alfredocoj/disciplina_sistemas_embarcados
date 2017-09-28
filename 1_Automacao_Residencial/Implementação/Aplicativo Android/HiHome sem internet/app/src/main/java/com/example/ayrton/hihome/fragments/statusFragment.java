package com.example.ayrton.hihome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ayrton.hihome.R;

public class statusFragment extends Fragment{

    private static int lampada, ac, portao;

    public static statusFragment newInstance(int qtdLampada, int qtdAc, int qtdPortao) {
        lampada = qtdLampada;
        ac = qtdAc;
        portao = qtdPortao;
        statusFragment fragment = new statusFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savednstanceState) {
        return layoutInflater.inflate(R.layout.frame_status, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView txtLampada = (TextView) getView().findViewById(R.id.status_lampada);
        TextView txtAC = (TextView) getView().findViewById(R.id.status_ac);
        TextView txtPortao = (TextView) getView().findViewById(R.id.status_portao);

        txtLampada.setText(""+lampada);
        txtAC.setText(""+ac);
        txtPortao.setText(""+portao);
    }
}
