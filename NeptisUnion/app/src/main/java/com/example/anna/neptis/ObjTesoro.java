package com.example.anna.neptis;

/**
 * Created by Anna on 17/10/2016.
 */

public class ObjTesoro {

    String user;

    private String code;
    private String latitudine;
    private String longitudine;
    private String info;
    private boolean found = false;  //per verificare se il tesoro è stato trovato
    private Integer aperto = new Integer(R.drawable.forziere_aperto);
    private Integer chiuso = new Integer(R.drawable.forziere_chiuso);

    public ObjTesoro(String c,String lat,String lon,String i,String u){
        this.code = c;
        this.latitudine = lat;
        this.longitudine = lon;
        this.info = i;
        this.user=u;
    }

    public String getCode() {
        return this.code;
    }

    public String getLatitudine() {
        return this.latitudine;
    }

    public String getLongitudine() {
        return this.longitudine;
    }

    public String getInfo() {
        return this.info;
    }

    public String getUser(){return this.user;}

    public Integer getTreasureImage() {
        return this.chiuso;
    }

    public Integer getTreasureImage(boolean f,int p) {
        if(f)return this.aperto;
        else return this.chiuso;
    }

    public boolean isFound() {
        return this.found;
    }

    public void setFound() {
        this.found = true;
    }




}
