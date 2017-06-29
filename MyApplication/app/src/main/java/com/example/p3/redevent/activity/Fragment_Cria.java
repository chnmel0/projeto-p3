package com.example.p3.redevent.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p3.redevent.DAO.ConfiguracaoFirebase;
import com.example.p3.redevent.Entidades.Eventos;
import com.example.p3.redevent.Entidades.Usuarios;
import com.example.p3.redevent.Helper.Base64Custom;
import com.example.p3.redevent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Cria extends Fragment {
    private FirebaseAuth autenticFire;
    private FirebaseUser user;
    private EditText titulo;
    private EditText autor;
    private EditText data_Ini;
    private EditText data_Fim;
    private EditText descrição;
    private Button criar;
    private Eventos eventos;

    public Fragment_Cria() {
        // Required empty public constructor

    }

    private FloatingActionButton fab;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment__cria, container, false);
        titulo = (EditText) view.findViewById(R.id.edtTitulo);
        autor = (EditText) view.findViewById(R.id.edtNomeAutor);
        autor.setText(getArguments().getString("user"));
        autor.setActivated(false);
        data_Ini = (EditText) view.findViewById(R.id.edtDataIni);
        data_Fim = (EditText) view.findViewById(R.id.edtDataFim);
        descrição = (EditText) view.findViewById(R.id.edtDesc);
        criar = (Button) view.findViewById(R.id.btnCriar);
        criar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!titulo.getText().toString().equals("")){
                    eventos = new Eventos();
                    eventos.setTitulo(titulo.getText().toString());
                    eventos.setAutor(autor.getText().toString());
                    eventos.setData_Inicio(data_Ini.getText().toString());
                    eventos.setData_Final(data_Fim.getText().toString());
                    eventos.setDescricao(descrição.getText().toString());
                    //eventos.setDescricao(null);
                    cadastrarEvento();
                }
            }
        });

        return view;
    }

    public String getUser(){
        autenticFire = ConfiguracaoFirebase.getFirebaseAutentic();
        user = autenticFire.getCurrentUser();
        return user.getDisplayName();
    }

    private void cadastrarEvento() {
        String identificadorEvento = Base64Custom.codificarBase64(eventos.getTitulo());
        eventos.setId(identificadorEvento);
        eventos.salvar();
        Toast.makeText(getContext(), "Evento Criado com Sucesso", Toast.LENGTH_LONG).show();
        abrirTelaLogado();
    }
    public void abrirTelaLogado() {
        Fragment_Logado logado = new Fragment_Logado();
        getFragmentManager().beginTransaction().replace(R.id.conteudo_fragment,logado).commit();
    }


    public String getUid() {
        return FirebaseInstanceId.getInstance().getId();
    }
}
