package com.example.p3.redevent.activity;

import android.content.Intent;
import android.content.pm.LauncherApps;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth autenticFire;
    AccessToken accessToken;
    private CallbackManager cbm;
    LoginButton btn_facebookIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(getApplicationContext());
/*
        String hash = getHashKey();
        Log.d("HASHKEY::: ", hash);
*/
        btn_facebookIn = (LoginButton) findViewById(R.id.btn_face);
        cbm = CallbackManager.Factory.create();
        facebookIn();

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

    private void facebookIn(){
        cbm = CallbackManager.Factory.create();
        btn_facebookIn.setReadPermissions("email","public_profile","user_friends");
        btn_facebookIn.registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("MainActivity","facebook sucss::"+loginResult);
                accessToken = loginResult.getAccessToken();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    private void facebookLogin(){
        if(accessToken != null){
            accessToken = com.facebook.AccessToken.getCurrentAccessToken();
            LoginManager.getInstance().logOut();
        }
        LoginManager.getInstance().registerCallback(cbm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Granted permision:: "+ loginResult.getRecentlyGrantedPermissions());
                GraphRequest gr = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        String userDatil = response.getRawResponse();
                        try {
                            JSONObject jsonObject = new JSONObject(userDatil);
                            System.out.println("JSON "+ jsonObject);
                            String facebookId = jsonObject.getString("id");
                            String email = jsonObject.getString("email");
                            String name = jsonObject.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields","name,email");
                gr.setParameters(parameters);
                gr.executeAsync();

            }

            @Override
            public void onCancel() {
                System.out.println("Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("no net");
            }
        });
        btn_facebookIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile, email, user_videos"));
            }
        });
    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        cbm.onActivityResult(requestCode,resultCode,data);
    }
}
