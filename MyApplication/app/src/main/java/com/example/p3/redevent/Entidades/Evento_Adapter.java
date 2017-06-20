package com.example.p3.redevent.Entidades;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.p3.redevent.R;

import java.util.ArrayList;

/**
 * Created by Augusto on 20/06/2017.
 */

public class Evento_Adapter extends ArrayAdapter<Eventos_Exibi> {
    private final Context context;
    private final ArrayList<Eventos_Exibi> elementos;

    public Evento_Adapter(Context context, ArrayList<Eventos_Exibi> elementos) {
        super(context, R.layout.evento, elementos);
        this.context = context;
        this.elementos = elementos;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater= (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.evento,parent,false);

        TextView titleEvento = (TextView) rowView.findViewById(R.id.title);
        TextView dataEvento = (TextView) rowView.findViewById(R.id.data);

        titleEvento.setText(elementos.get(position).getTitle());
        dataEvento.setText(elementos.get(position).getData());

        return rowView;


    }
}

