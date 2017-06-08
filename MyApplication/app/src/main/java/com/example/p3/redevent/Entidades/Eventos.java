package com.example.p3.redevent.Entidades;

import com.example.p3.redevent.DAO.ConfiguracaoFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Carlos on 07/06/2017.
 */

public class Eventos {
    private String id;
    private String titulo;
    private String descricao;
    private String data;
    private String autor;
    private String participantes;

    public Eventos() {
    }
    public void salvar(){
        DatabaseReference referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        referenciaFirebase.child("evento").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude

    public Map<String,Object> toMap(){
        HashMap<String,Object> hashMapEventos = new HashMap<>();
        hashMapEventos.put("id",getId());
        hashMapEventos.put("titulo",getTitulo());
        hashMapEventos.put("descricao", getDescricao());
        hashMapEventos.put("data", getData());
        hashMapEventos.put("autor", getAutor());
        hashMapEventos.put("participantes", getParticipantes());

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String participantes) {
        this.participantes = participantes;
    }
}
