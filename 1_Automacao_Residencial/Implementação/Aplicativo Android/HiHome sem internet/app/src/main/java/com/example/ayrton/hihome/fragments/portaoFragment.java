package com.example.ayrton.hihome.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ayrton.hihome.R;

public class portaoFragment extends Fragment{

    static int portao;
    View v[] = new View[portao];

    public static portaoFragment newInstance(int qtdPortao) {
        portao = qtdPortao;
        portaoFragment fragment = new portaoFragment();
        return fragment;
    }

    public View[] getViews() {
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.frame_portao, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LinearLayout frame_portao = (LinearLayout) getView().findViewById(R.id.frame_portao);
        frame_portao.removeAllViews();

        TextView text[] = new TextView[portao];
        LayoutInflater inflater = getLayoutInflater(savedInstanceState);

        for(int i = 0; i < portao; i++){
            v[i] = inflater.inflate(R.layout.widget_status_item_layout, frame_portao, true);
            v[i].setId(i);

            text[i] = (TextView) v[i].findViewById(R.id.txt_item);
            text[i].setId(i);
            text[i].setText("Portao "+(i+1));
        }
    }
}
