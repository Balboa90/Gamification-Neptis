package com.example.anna.neptis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anna on 16/10/2016.
 */

public class TreasureAdapter extends ArrayAdapter<Tesoro> {

    public TreasureAdapter(Context context, int textViewResourceId, List<Tesoro> list) {
        super(context, textViewResourceId, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adapter_treasure, null);
        ImageButton forziere = (ImageButton)convertView.findViewById(R.id.tesori);
        Tesoro t = getItem(position);
        forziere.setImageResource(t.getImage());
        return convertView;
    }


}
