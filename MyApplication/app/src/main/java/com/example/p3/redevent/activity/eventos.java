package com.example.p3.redevent.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;

import com.example.p3.redevent.Entidades.Eventos;
import com.example.p3.redevent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class eventos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventos);
    }
    public List<String> getEvents(){
        final List<Array> listReturn;
        DatabaseReference referenceD = FirebaseDatabase.getInstance().getReference();
        DatabaseReference childR = referenceD.child("evento");
        childR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d : dataSnapshot.getChildren()){
                    Eventos ev = dataSnapshot.getValue(Eventos.class);
                    //listReturn.add([ev.getTitulo(),ev.getAutor(),ev.getData(),ev.getDescricao(),ev.getParticipantes()]);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return null;

    }
}
