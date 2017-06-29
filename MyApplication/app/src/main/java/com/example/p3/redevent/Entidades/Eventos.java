package com.example.p3.redevent.Entidades;

import com.example.p3.redevent.DAO.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Carlos on 07/06/2017.
 */

public class Eventos {
    private String id;
    private String titulo;
    private String descricao;
    private String data_Inicio;
    private String data_Final;
    private String autor;
    //private ArrayList<String> participantes;

    public Eventos(){
    }
    public Eventos(String id, String titulo, String descricao, String data_Inicio, String data_Final, String autor) {
        /*this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data_Inicio = data_Inicio;
        this.data_Final = data_Final;
        this.autor = autor;*/
    }
    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("eventos").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude

    public Map<String,Object> toMap(){
        HashMap<String,Object> hashMapEventos = new HashMap<>();
        hashMapEventos.put("id",getId());
        hashMapEventos.put("titulo",getTitulo());
        hashMapEventos.put("descricao", getDescricao());
        hashMapEventos.put("data_Inicio", getData_Inicio());
        hashMapEventos.put("data_Final", getData_Final());
        hashMapEventos.put("autor", getAutor());
        //hashMapEventos.put("participantes", getParticipantes());

        return hashMapEventos;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_Inicio() {
        return data_Inicio;
    }

    public void setData_Inicio(String data) {
        this.data_Inicio = data;
    }

    public String getData_Final() {
        return data_Final;}

    public void setData_Final(String data_Final) {
        this.data_Final = data_Final;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

   /* public ArrayList<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<String> participantes) {
        this.participantes = participantes;
    }*/


}
