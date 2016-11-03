package com.example.anna.neptis;

import android.util.Log;

/**
 * Created by Anna on 23/10/2016.
 */

class ObjCard {
    String code,cost,name,description;
    private Integer card_image = new Integer(R.drawable.card);



    public ObjCard(String cod,String cost,String n,String d){
        this.code=cod;
        this.cost=cost;
        this.name=n;
        this.description=d;
    }

    public ObjCard(String cost,String n,String d){
        this.cost=cost;
        this.name=n;
        this.description=d;
    }

    public String getCode(){return this.code;}

    public String getCost(){
        return this.cost;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Integer getCardImage(){
        return this.card_image;
    }

    public Integer getCardImage(int i){
        switch (i){

            case 0: return R.drawable.colosseo;
            case 1:return R.drawable.faro_di_alessandria;
            case 2:return R.drawable.machu_picchu;
            case 3:return R.drawable.london_bridge;
            case 4:return R.drawable.via_appia;
            case 5:return R.drawable.isola_tiberina;
            case 6:return R.drawable.partenone;
            case 7:return R.drawable.stile_dorico;
            case 8:return R.drawable.stile_ionico;
            case 9:return R.drawable.stile_corinzio;
            case 10:return R.drawable.arco_a_tutto_sesto;
            case 11:return R.drawable.arco_a_sesto_acuto;
            case 12:return R.drawable.stile_gotico;
            case 13:return R.drawable.basilica_di_san_pietro;
            case 14:return R.drawable.basilica_san_giovanni;
            case 15:return R.drawable.piazza_del_popolo;
            case 16:return R.drawable.altare_della_patria;
            case 17:return R.drawable.balcone_piazza_venezia;
            case 18:return R.drawable.museo_maxxi;
            case 19:return R.drawable.stadio_olimpico;


            default:
                Log.d("Prova img: ",Integer.toString(i));
                return this.card_image;
        }
    }


}
