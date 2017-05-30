package com.example.p3.redevent.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Augusto on 30/05/2017.
 */

public class Preferencias {
    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "projeto-p3";
    private SharedPreferences.Editor editor;
    private int mode = 0;

    private final String chave_ident = "identificarUsuarioLogado";
    private final String chave_nome = "nomeUsuarioLogado";

    public Preferencias(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO,mode);

        editor = preferences.edit();
    }

    public void salvarUsuarioPreferencias(String identificadorUsuario,String nomeUsuario){
        editor.putString(chave_ident,identificadorUsuario);
        editor.putString(chave_nome,nomeUsuario);
        editor.commit();
    }
    public String getIdentificador(){
        return preferences.getString(chave_ident,null);
    }
    public String getnome(){
        return preferences.getString(chave_nome,null);
    }

}
