package com.example.anna.neptis;

/**
 * Created by lorpe on 03/10/2016.
 */

class ObjAchievement {
    private String nome;
    private String descrizione;
    private boolean completato = false; // stelletta grigia

    public ObjAchievement(String n, String d){
        this.nome=n;
        this.descrizione=d;
    }

    public String getNome(){
        return this.nome;
    }

    public String getDescrizione(){
        return this.descrizione;
    }

    public boolean isCompletato(){
        return this.completato;
    }

    public void setCompletato(){
        this.completato = true;
    }
}
