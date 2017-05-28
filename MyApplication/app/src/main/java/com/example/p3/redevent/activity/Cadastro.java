package com.example.p3.redevent.activity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p3.redevent.R;

public class Cadastro extends AppCompatActivity {
    //private SQLiteDatabase db;
    //private DatabaseOpenHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mDbHelper = new DatabaseOpenHelper(this);
        Button btn_cadastrar = (Button) findViewById(R.id.btn_create);
        final EditText tbx_phone = (EditText) findViewById(R.id.tbx_phone);
        final EditText tbx_login = (EditText) findViewById(R.id.tbx_user);
        final EditText tbx_senha = (EditText) findViewById(R.id.tbx_senha);
        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //long resultado;
                //db = mDbHelper.getWritableDatabase();
              //  ContentValues values = new ContentValues();
               // values.put(DatabaseOpenHelper.LOGIN, tbx_login.getText().toString());
              //  values.put(DatabaseOpenHelper.PASS, tbx_senha.getText().toString());
              //  values.put(DatabaseOpenHelper.TELL, tbx_phone.getText().toString());
               // mDbHelper.getWritableDatabase().insert(DatabaseOpenHelper.TABLE_NAME, null, values);
                //db.close();
             //   values.clear();
                /*if (resultado == -1){
                    Toast.makeText(getApplicationContext(),"Erro ao inserir registro",Toast.LENGTH_SHORT).show();}
                else{
                    Toast.makeText(getApplicationContext(),"Registro Inserido com sucesso",Toast.LENGTH_SHORT).show();
                    }*/
            }
        });
    }
}
