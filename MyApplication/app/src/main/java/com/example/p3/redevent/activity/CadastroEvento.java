package com.example.p3.redevent.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p3.redevent.CONF.ConfigFirebase;
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

public class CadastroEvento extends AppCompatActivity {

    private FirebaseAuth autenticFire;
    private FirebaseUser user;
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
        //getUserName();
        try {
            Toast.makeText(CadastroEvento.this, getUser(),Toast.LENGTH_LONG).show();
        }
        catch (Exception ex){
            Log.d("Erro da desgraca::: ",ex.toString());
            Toast.makeText(CadastroEvento.this,ex.toString(),Toast.LENGTH_LONG).show();
        }

        criar = (Button) findViewById(R.id.btnCriar);
        criar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!titulo.getText().toString().equals("")){
                    eventos = new Eventos();
                    eventos.setTitulo(titulo.getText().toString());
                    eventos.setAutor(autor.getText().toString());
                    eventos.setAutor(getUserName());
                    eventos.setData(data.getText().toString());
                    eventos.setParticipantes(null);
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

    public String getUserName(){
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("redevent-6cfe4.firebaseio.com/usuario");
        final String[] userr = new String[1];
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                userr[0] = user.nome.toString();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        //userf[0].getNome();
        //Toast.makeText(CadastroEvento.this, userf[0].getNome(),Toast.LENGTH_LONG).show();
        /*autenticFire = ConfiguracaoFirebase.getFirebaseAutentic();
        FirebaseUser user = autenticFire.getCurrentUser();

        return user.getDisplayName().toString();*/
        return  userr[0];

    }
    public String getUser(){
        user = autenticFire.getCurrentUser();
        return user.getDisplayName();
    }
    public void abrirTelaLogado(){
        Intent intent = new Intent(CadastroEvento.this,TelaLogado.class);
        startActivity(intent);
        finish();
    }
}
class User {

    public String email;
    public String nome;
    public String sexo;

    public User(String nome, String email,String sexo) {
        this.nome = nome;
        this.email = email;
        this.sexo = sexo;
        // ...
    }

}