package com.example.anna.neptis;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anna on 23/10/2016.
 */
/*
public class CardImageAdapter extends ArrayAdapter<ObjCardImage> {

    Context my_context;
    ObjCardImage t;
    TextView code;

    public CardImageAdapter(Context context, int textViewResourceId, List<ObjCardImage> list){
        super(context,textViewResourceId,list);
        my_context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.adapter_card, null);
        ImageButton card = (ImageButton)convertView.findViewById(R.id.card_icon);
        code = (TextView)convertView.findViewById(R.id.codice_carta);
        t = getItem(position);
        code.setText(t.getCode());
        card.setImageResource(t.getCardImage());


       /*card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goto_card_info = new Intent(view.getContext(),CardInfoActivity.class);
                //ObjCardImage selected_card = all_cards.get(position);
                String codice = t.getCode();
                Log.d("Codice: ",codice);
                goto_card_info.putExtra("codice", codice);
               // openCardInfoActivity.putExtra("codice",cod); //passo il codice a CardInfoActivity
                my_context.startActivity(goto_card_info);
            }
        });
        return convertView;
    }


}*/