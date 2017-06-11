package com.example.p3.redevent.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p3.redevent.CONF.ConfigFirebase;
import com.example.p3.redevent.Entidades.Eventos;
import com.example.p3.redevent.Entidades.Usuarios;
import com.example.p3.redevent.Helper.Base64Custom;
import com.example.p3.redevent.R;
import com.google.firebase.auth.FirebaseAuth;

public class CadastroEvento extends AppCompatActivity {

    private EditText titulo;
    private EditText autor;
    private EditText data;
    private EditText participantes;
    private EditText descrição;
    private Button criar;
    private Eventos eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);

        titulo = (EditText) findViewById(R.id.edtTitulo);
        autor = (EditText) findViewById(R.id.edtNomeAutor);
        data = (EditText) findViewById(R.id.edtData);
        participantes = (EditText) findViewById(R.id.edtPart);
        descrição = (EditText) findViewById(R.id.edtDesc);
        criar = (Button) findViewById(R.id.btnCriar);
        criar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!titulo.getText().toString().equals("")){
                    eventos = new Eventos();

                    eventos.setTitulo(titulo.getText().toString());
                    eventos.setAutor(autor.getText().toString());
                    eventos.setData(data.getText().toString());
                    eventos.setParticipantes(participantes.getText().toString());
                    eventos.setDescricao(descrição.getText().toString());
                    cadastrarEvento();
                }
            }
        });
    }
    private void cadastrarEvento(){
        String identificadorEvento = Base64Custom.codificarBase64(eventos.getTitulo());
        eventos.setId(identificadorEvento);
        eventos.salvar();
        Toast.makeText(CadastroEvento.this,"Evento Criado com Sucesso", Toast.LENGTH_LONG).show();

        abrirTelaLogado();
    }
    public void abrirTelaLogado(){
        Intent intent = new Intent(CadastroEvento.this,TelaLogado.class);
        startActivity(intent);
        finish();
    }
}
