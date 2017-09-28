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

public class acFragment extends Fragment{

    static int ac;
    View v[] = new View[ac];

    public static acFragment newInstance(int qtdAC) {
        ac = qtdAC;
        acFragment fragment = new acFragment();
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
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savednstanceState) {
        return layoutInflater.inflate(R.layout.frame_ar_cond, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final LinearLayout frame_ac = (LinearLayout) getView().findViewById(R.id.frame_ac);
        frame_ac.removeAllViews();

        TextView text[] = new TextView[ac];
        LayoutInflater inflater = getLayoutInflater(savedInstanceState);

        for(int i = 0; i < ac; i++){
            v[i] = inflater.inflate(R.layout.widget_status_item_layout, frame_ac, true);
            v[i].setId(i);

            text[i] = (TextView) v[i].findViewById(R.id.txt_item);
            text[i].setId(i);
            text[i].setText("Ar condicionado "+(i+1));
        }

    }

}
