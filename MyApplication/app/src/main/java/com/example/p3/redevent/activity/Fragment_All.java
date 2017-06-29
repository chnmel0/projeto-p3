package com.example.p3.redevent.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.p3.redevent.Entidades.Evento_Adapter;
import com.example.p3.redevent.Entidades.Eventos;
import com.example.p3.redevent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Fragment_All extends Fragment {
    ListView lista;
    ArrayAdapter adapter;
    View view;
    ArrayList<Eventos> eventos;
    public Fragment_All() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fragment__logado, container, false);
        lista = (ListView) view.findViewById(R.id.list);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://redevent-6cfe4.firebaseio.com/eventos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d: dataSnapshot.getChildren()){

                    Eventos ev = d.getValue(Eventos.class);
                    eventos.add(ev);
                }

                adapter = new Evento_Adapter(getActivity(),eventos);
                lista.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Nome:",databaseError.toString());
            }

        });
        // Inflate the layout for this fragment

        if (eventos == null){
            eventos = insere();
            adapter = new Evento_Adapter(getActivity(), (ArrayList<Eventos>) eventos);
            lista.setAdapter(adapter);
            adapter.clear();
        }
        return view;



    }
    private ArrayList<Eventos> insere(){
        ArrayList<Eventos> eventos = new ArrayList<Eventos>();
        Eventos e = new Eventos(" "," "," "," "," "," "," ");
        eventos.add(e);
        return eventos;
    }
}
