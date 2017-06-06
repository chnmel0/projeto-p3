package com.example.p3.redevent.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p3.redevent.DAO.ConfiguracaoFirebase;
import com.example.p3.redevent.Helper.Base64Custom;
import com.example.p3.redevent.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    /*private DatabaseOpenHelper mDbHelper;
    private SimpleCursorAdapter mAdapter;*/
    private FirebaseAuth autenticFire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mDbHelper = new DatabaseOpenHelper(this);
/*
        String hash = getHashKey();
        Log.d("HASHKEY::: ", hash);
*/
        final EditText tbx_login = (EditText) findViewById(R.id.tbx_user);
        final EditText tbx_senha = (EditText) findViewById(R.id.tbx_pass);
        Button btn_in = (Button) findViewById(R.id.btn_login);
        Button btn_up = (Button) findViewById(R.id.btn_cad);
        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tbx_login.getText().toString().equals("")||!tbx_senha.getText().toString().equals("")){
                    validarLogin(tbx_login.getText().toString(), tbx_senha.getText().toString());
                }else{
                    Toast.makeText(MainActivity.this,"Preencha os campos de Email e Senha",Toast.LENGTH_LONG).show();
                }
                /*validarLogin(tbx_login.getText().toString(), tbx_senha.getText().toString());
                /*SQLiteDatabase bd = mDbHelper.getWritableDatabase();
                Cursor c = bd.rawQuery("SELECT * FROM bdRss WHERE login =" + tbx_login.getText().toString(),null);
                /*Cursor c = bd.query(DatabaseOpenHelper.TABLE_NAME,
                        DatabaseOpenHelper.columns, ("login ="+tbx_login.getText().toString()), new String[] {}, null, null,
                        null);
                if(c!=null){
                    c.moveToFirst();
                }
                if (c.equals(null)){
                    Toast.makeText(getApplicationContext(),"Este login não existe no banco",Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(getApplicationContext(),c.getString(c.getPosition()),Toast.LENGTH_SHORT).show();

                }*/
                //c.moveToFirst();
                /*Cursor c = mDbHelper.getWritableDatabase().query(DatabaseOpenHelper.TABLE_NAME,
                        DatabaseOpenHelper.columns, null, new String[] {}, null, null,
                        null);*/

            }
        });
        btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Cadastro.class));
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        final EditText tbx_login = (EditText) findViewById(R.id.tbx_user);
        final EditText tbx_senha = (EditText) findViewById(R.id.tbx_pass);

        tbx_login.setText("");
        tbx_senha.setText("");
    }

    private void validarLogin(String mail, String senha){
        Toast.makeText(MainActivity.this, mail,Toast.LENGTH_LONG).show();
        Toast.makeText(MainActivity.this, senha,Toast.LENGTH_LONG).show();
        autenticFire = ConfiguracaoFirebase.getFirebaseAutentic();
        autenticFire.signInWithEmailAndPassword(mail,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    abriTelaPrincipal();
                    Toast.makeText(MainActivity.this, "Login Efetuado com Sucesso",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Usuário ou Senha inválidos",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String getHashKey(){
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures){
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                return Base64.encodeToString(md.digest(), Base64.DEFAULT);

            }
        } catch (PackageManager.NameNotFoundException e) {
            return "erro";
        } catch (NoSuchAlgorithmException e) {
            return "erro2";
        }
        return "nada";
    }
    public void abriTelaPrincipal(){
        Intent itTelaPrincipal = new Intent(MainActivity.this, Logado.class);
        startActivity(itTelaPrincipal);
    }
}
