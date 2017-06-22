package com.example.p3.redevent.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.p3.redevent.Entidades.Evento_Adapter;
import com.example.p3.redevent.Entidades.Eventos;
import com.example.p3.redevent.Entidades.Eventos_Exibi;
import com.example.p3.redevent.Entidades.Usuarios;
import com.example.p3.redevent.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Logado extends Fragment {

    ListView lista;
    ArrayAdapter adapter;
    View view;
    ArrayList<Eventos_Exibi> eventos;
    public Fragment_Logado() {
        // Required empty public constructor
    }
    public Fragment_Logado(ArrayList<Eventos_Exibi> eventos) {
        // Required empty public constructor
        this.eventos = eventos;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_fragment__logado, container, false);
        lista = (ListView) view.findViewById(R.id.list);

        //preenche list

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://redevent-6cfe4.firebaseio.com/eventos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d: dataSnapshot.getChildren()){
                    Eventos ev = d.getValue(Eventos.class);
                    Eventos_Exibi e = new Eventos_Exibi(ev.getTitulo(),ev.getData_Inicio());
                    eventos.add(e);
                }
                adapter = new Evento_Adapter(getActivity(),eventos);
                lista.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Nome:",databaseError.toString());
            }

        });
        if (eventos == null){
            eventos = insere();
        }
        adapter = new Evento_Adapter(getActivity(),eventos);
        lista.setAdapter(adapter);
        //eventos = insere();
        return view;


    }

    private ArrayList<Eventos_Exibi> insere(){
        ArrayList<Eventos_Exibi> eventos = new ArrayList<Eventos_Exibi>();
        Eventos_Exibi e = new Eventos_Exibi(" "," ");
        eventos.add(e);
        return eventos;
    }

}
