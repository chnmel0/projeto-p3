package com.example.p3.redevent.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.p3.redevent.CONF.ConfigFirebase;
import com.example.p3.redevent.Entidades.Usuarios;
import com.example.p3.redevent.Helper.Base64Custom;
import com.example.p3.redevent.Helper.Preferencias;
import com.example.p3.redevent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class Cadastro extends AppCompatActivity {

    private EditText edtCadEmail;
    private EditText edtCadNome;
    private EditText edtCadSenha;
    private EditText getEdtCadSenhaConfirm;
    private RadioButton edtCadSexoMasc;
    private RadioButton edtCadSexoFem;
    private Button btnCadastrar;
    private Usuarios usuarios;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        edtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtCadNome = (EditText) findViewById(R.id.edtCadNome);
        edtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        getEdtCadSenhaConfirm = (EditText) findViewById(R.id.edtCadSenhaConfirm);
        edtCadSexoMasc = (RadioButton) findViewById(R.id.edtCadSexoMasc);
        edtCadSexoFem = (RadioButton) findViewById(R.id.edtCadSexoFem);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtCadSenha.getText().toString().equals(getEdtCadSenhaConfirm.getText().toString())) {
                    usuarios = new Usuarios();
                    usuarios.setNome(edtCadNome.getText().toString());
                    usuarios.setEmail(edtCadEmail.getText().toString());
                    usuarios.setSenha(edtCadSenha.getText().toString());

                    if (edtCadSexoMasc.isChecked()) {
                        usuarios.setSexo("Masculino");
                    } else {
                        usuarios.setSexo("Feminino");
                    }
                } else {
                    Toast.makeText(Cadastro.this, "As senhas não correspondem", Toast.LENGTH_LONG).show();
                }
                cadastrarUsuario();
            }
        });

    }

    private void cadastrarUsuario() {
        autenticacao = ConfigFirebase.getFirebaseAutentica();
        autenticacao.createUserWithEmailAndPassword(
                usuarios.getEmail(),
                usuarios.getSenha()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Cadastro.this, "Usuário cadastrado com sucesso", Toast.LENGTH_LONG).show();

                    String identificadorUsuario = Base64Custom.codificarBase64(usuarios.getEmail());

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuarios.setId(identificadorUsuario);
                    usuarios.salvar();

                    Preferencias preferencias = new Preferencias(Cadastro.this);
                    preferencias.salvarUsuarioPreferencias(identificadorUsuario, usuarios.getNome());

                    abrirLoginUsuario();
                } else {
                    String erroExcecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite uma senha mais forte, contendo letras e números";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "O email digitado é inválido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        erroExcecao = "Email já cadastrado";
                    } catch (Exception e) {
                        erroExcecao = "Erro ao efetuar cadastro";
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastro.this, "Erro " + erroExcecao, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void abrirLoginUsuario(){
        Intent intent = new Intent(Cadastro.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}

