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

public class lampadaFragment extends Fragment{

    static int lampada;
    View v[] = new View[lampada];

    public static lampadaFragment newInstance(int qtdLampada) {
        lampada = qtdLampada;
        lampadaFragment fragment = new lampadaFragment();
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
        return layoutInflater.inflate(R.layout.frame_lampadas, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LinearLayout frame_lampada = (LinearLayout) getView().findViewById(R.id.frame_lampada);
        frame_lampada.removeAllViews();

        TextView text[] = new TextView[lampada];
        LayoutInflater inflater = getLayoutInflater(savedInstanceState);

        for(int i = 0; i < lampada; i++){
            v[i] = inflater.inflate(R.layout.widget_status_item_layout, frame_lampada, true);
            v[i].setId(i);

            text[i] = (TextView) v[i].findViewById(R.id.txt_item);
            text[i].setId(i);
            text[i].setText("Lampada "+(i+1));
        }
    }
}
