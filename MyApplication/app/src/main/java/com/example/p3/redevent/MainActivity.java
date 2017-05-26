package com.example.p3.redevent;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private DatabaseOpenHelper mDbHelper;
    private SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new DatabaseOpenHelper(this);
        final EditText tbx_login = (EditText) findViewById(R.id.tbx_user);
        EditText tbx_senha = (EditText) findViewById(R.id.tbx_pass);
        Button btn_in = (Button) findViewById(R.id.btn_login);
        Button btn_up = (Button) findViewById(R.id.btn_cad);
        btn_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase bd = mDbHelper.getWritableDatabase();
                Cursor c = bd.rawQuery("SELECT * FROM bdRss WHERE login =" + tbx_login.getText().toString(),null);
                if (c.equals(null)){
                    Toast.makeText(getApplicationContext(),"Este login não existe no banco",Toast.LENGTH_SHORT).show();
                }
                else { Toast.makeText(getApplicationContext(),"Este login existe no banco",Toast.LENGTH_SHORT).show();

                }
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
}
