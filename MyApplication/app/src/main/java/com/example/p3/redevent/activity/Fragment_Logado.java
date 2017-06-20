package com.example.p3.redevent.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.p3.redevent.Entidades.Evento_Adapter;
import com.example.p3.redevent.Entidades.Eventos_Exibi;
import com.example.p3.redevent.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_Logado extends Fragment {

    ListView lista;
    ArrayAdapter adapter;


    public Fragment_Logado() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view = inflater.inflate(R.layout.fragment_fragment__logado, container, false);
        lista = (ListView) view.findViewById(R.id.list);
        ArrayList<Eventos_Exibi> eventos = insere();
        adapter = new Evento_Adapter(getActivity(),eventos);
        lista.setAdapter(adapter);
        return view;
    }

    private ArrayList<Eventos_Exibi> insere(){
        ArrayList<Eventos_Exibi> eventos = new ArrayList<Eventos_Exibi>();
        Eventos_Exibi e = new Eventos_Exibi("Teste","teste");
        eventos.add(e);
        e = new Eventos_Exibi("Teste2","Teste2");
        eventos.add(e);
        return eventos;
    }

}
