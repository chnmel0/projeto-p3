package com.example.p3.redevent.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Logado extends Fragment {

    ListView lista;
    ArrayAdapter adapter;
    View view;
    ArrayList<Eventos> eventos;
    public Fragment_Logado() {
        // Required empty public constructor
    }
    public Fragment_Logado(ArrayList<Eventos> eventos) {
        // Required empty public constructor
        this.eventos = eventos;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_fragment__logado, container, false);
        lista = (ListView) view.findViewById(R.id.list);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ArrayAdapter<Eventos> arrayAdapter = (ArrayAdapter<Eventos>)listView.getAdapter();
                Eventos ev = arrayAdapter.getItem(position);
                Intent itent = new Intent(getContext(), EventOpen.class);
                Bundle args = new Bundle();
                args.putString("Autor", ev.getAutor());
                args.putString("Titulo", ev.getTitulo());
                args.putString("Descrição", ev.getDescricao());
                args.putString("Data Inicial", ev.getData_Inicio());
                args.putString("Data Final", ev.getData_Final());
                itent.putExtra("events", args);
                startActivity(itent);
            }
        });

        //preenche list

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReferenceFromUrl("https://redevent-6cfe4.firebaseio.com/eventos");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long date = System.currentTimeMillis();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = sdf.format(date);
                int[] dataAr = convertArrayData(dateString);
                //Toast.makeText(getContext(), dataAr[0]+"/"+dataAr[1]+"/"+dataAr[2],Toast.LENGTH_LONG).show();
                for (DataSnapshot d: dataSnapshot.getChildren()){

                    Eventos ev = d.getValue(Eventos.class);
                    int[] dataArEv = convertArrayData(ev.getData_Final());
                    if (dataAr[0]<=dataArEv[0] && dataAr[1]<=dataArEv[1] && dataAr[2]<=dataArEv[2]){
                        eventos.add(ev);
                    }
                    else if(dataAr[1]<dataArEv[1] && dataAr[2]<=dataArEv[2]){
                        eventos.add(ev);
                    }
                    else if(dataAr[2]<dataArEv[2]){
                        eventos.add(ev);
                    }
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
        adapter.clear();
        //eventos = insere();
        return view;


    }
    private  int[] convertArrayData(String data){
        int[] retorno = {00,00,0000};

        if (data != null && data.length() == 10){
            String[] date = data.split("/");
            retorno[0]= Integer.parseInt(date[0]);
            retorno[1] = Integer.parseInt(date[1]);
            retorno[2] = Integer.parseInt(date[2]);
        }
        return retorno;
    }
    private ArrayList<Eventos> insere(){
        ArrayList<Eventos> eventos = new ArrayList<Eventos>();
        Eventos e = new Eventos(" "," "," "," "," "," ");
        eventos.add(e);
        return eventos;
    }

}
