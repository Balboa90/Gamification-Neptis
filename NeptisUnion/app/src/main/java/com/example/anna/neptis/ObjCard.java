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

            case 0: return R.drawable.basilica;

            /*case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:*/


            default:
                Log.d("Prova img: ",Integer.toString(i));
                return this.card_image;
        }
    }


}
