package com.example.p3.redevent.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.p3.redevent.R;

public class TelaLogado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_logado);
        Button btnCria = (Button) findViewById(R.id.btncadEvento);
        btnCria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent it = new Intent(TelaLogado.this,CadastroEvento.class);
                startActivity(it);
            }
        });
    }
}
