package com.example.anna.neptis;

/**
 * Created by Anna on 23/10/2016.
 */

class ObjCard {
    String code,cost,name,description;
    private Integer card_image = new Integer(R.drawable.card);
    private Integer info_image = new Integer(R.drawable.info);


    public ObjCard(String cod,String cost,String n,String d){
        this.code=cod;
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

    public Integer getInfoImage(){
        return this.info_image;
    }

}
