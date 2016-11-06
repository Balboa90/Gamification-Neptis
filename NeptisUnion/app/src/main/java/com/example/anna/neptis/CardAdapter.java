package com.example.anna.neptis;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anna on 23/10/2016.
 */


public class CardAdapter extends ArrayAdapter<ObjCard> {

    public CardAdapter(Context context, int textViewResourceId, List<ObjCard> list){
        super(context,textViewResourceId,list);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adapter_card, null);
        ImageView card = (ImageView) convertView.findViewById(R.id.card_icon);

        TextView cost = (TextView)convertView.findViewById(R.id.valore_costo);
        TextView name = (TextView)convertView.findViewById(R.id.nome);

        ScrollView s  = (ScrollView)convertView.findViewById(R.id.ScrollView);
        TextView des = (TextView)convertView.findViewById(R.id.d);
        ObjCard t = getItem(position);

        cost.setText(t.getCost());
        name.setText(t.getName());
        des.setText(t.getDescription());
        s.setNestedScrollingEnabled(true);

        int img_id = position;
        card.setImageResource(t.getCardImage(img_id));


        return convertView;
    }


}