package com.example.anna.neptis;

/**
 * Created by Anna on 31/10/2016.
 */

public class User {

    private String email,password;


    public User(String e,String p){
        this.email=e;
        this.password=p;
    }

    public String getEmail(){
        return this.email;
    }


    public String getPassword(){
        return this.password;
    }

}
