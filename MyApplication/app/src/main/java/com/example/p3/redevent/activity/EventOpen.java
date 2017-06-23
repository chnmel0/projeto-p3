package com.example.p3.redevent.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.p3.redevent.R;

public class EventOpen extends AppCompatActivity {
    TextView title;
    TextView autor;
    TextView dt_ini;
    TextView dt_fim;
    TextView descr;
    Button btn_pert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_open);
        title = (TextView) findViewById(R.id.titulo);
        autor = (TextView) findViewById(R.id.autor);
        dt_ini = (TextView) findViewById(R.id.dt_inicio);
        dt_fim = (TextView) findViewById(R.id.dt_final);
        descr = (TextView) findViewById(R.id.descr);
        btn_pert = (Button) findViewById(R.id.btn_part);
        Bundle args = getIntent().getBundleExtra("events");
        if (args != null){
            title.setText(args.getString("Titulo"));
            autor.setText(args.getString("Autor"));
            dt_ini.setText(args.getString("Data Inicial"));
            dt_fim.setText(args.getString("Data Final"));
            descr.setText(args.getString("Descrição"));
        }
    }
}
