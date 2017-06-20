package com.example.p3.redevent.Entidades;

/**
 * Created by Augusto on 20/06/2017.
 */

public class Eventos_Exibi {
    private String title;
    private String data;



    public Eventos_Exibi(String title,String data){
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
