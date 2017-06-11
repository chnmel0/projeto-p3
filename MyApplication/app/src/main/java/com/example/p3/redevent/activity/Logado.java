package com.example.p3.redevent.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.p3.redevent.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Logado extends AppCompatActivity {
    private FirebaseAuth autenticFire;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);
        user = getUser();
        TextView txt_nome = (TextView) findViewById(R.id.txt_user);
        TextView txt_mail = (TextView) findViewById(R.id.txt_email);
        txt_nome.setText(user.getDisplayName());
        txt_mail.setText(user.getEmail());
    }

    private FirebaseUser getUser(){
        FirebaseUser userf;
        userf = autenticFire.getCurrentUser();
        return userf;
    }
}