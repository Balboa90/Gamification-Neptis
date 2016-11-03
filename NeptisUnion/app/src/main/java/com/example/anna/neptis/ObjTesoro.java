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


    /*************************
    *****************
    ************
    *
    *aggiungere in TreasurePortalPag2 la chiamata al database per recuperare l'info found del tesoro
     * se è stato trovato modificare bacon(da rosso a verde sempre nella stessa classe)
     * modificare immagine tesoro: se è stato trovato l'immagine deve rappresentare il forziere aperto (altrimenti default)
     * quest'ultima cosa si deve fare in this classe, nel getTreasureImage nella quale verrà passato il valore id del tesoro
     * e, facendo un controllo sul found(che troviamo sempre in ObjTesoro), mettiamo l'immagine desiderata
     *
     * il click sulla carta del tesoro sarà possibile solo se il tesoro è stato trovato:vedi TreasureAdapter(l'ho indicato li dentro)
     *
     * ***********
     * **************************/
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
