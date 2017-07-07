package com.example.p3.redevent.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

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
    ProgressBar carga;
    public Fragment_All() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (adapter != null){
            adapter.clear();}
        view = inflater.inflate(R.layout.fragment_fragment__all, container, false);
        lista = (ListView) view.findViewById(R.id.list);
        carga = (ProgressBar) view.findViewById(R.id.carregar);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ArrayAdapter<Eventos> arrayAdapter = (ArrayAdapter<Eventos>)listView.getAdapter();
                Eventos ev = arrayAdapter.getItem(position);
                Intent itent = new Intent(getContext(), EventOpen.class);
                Bundle args = new Bundle();
                args.putString("Id", ev.getId());
                args.putString("Autor", ev.getAutor());
                args.putString("Titulo", ev.getTitulo());
                args.putString("Descrição", ev.getDescricao());
                args.putString("Data Inicial", ev.getData_Inicio());
                args.putString("Data Final", ev.getData_Final());
                args.putString("Participantes",ev.getParticipantes());
                args.putString("User",getArguments().getString("user"));
                itent.putExtra("events", args);
                startActivity(itent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://redevent-6cfe4.firebaseio.com/eventos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot d: dataSnapshot.getChildren()){

                    Eventos ev = d.getValue(Eventos.class);
                    eventos.add(ev);
                }

                adapter = new Evento_Adapter(getContext(),eventos);
                lista.setAdapter(adapter);
                carga.setVisibility(View.INVISIBLE);
                lista.setVisibility(View.VISIBLE);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Nome:",databaseError.toString());
            }

        });
        // Inflate the layout for this fragment

        if (eventos == null){
            eventos = insere();
            adapter = new Evento_Adapter(getContext(), (ArrayList<Eventos>) eventos);
            lista.setAdapter(adapter);
            adapter.clear();
            carga.setVisibility(View.VISIBLE);
            lista.setVisibility(View.INVISIBLE);
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
