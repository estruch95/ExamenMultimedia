package com.example.estruch18.examenmultimedia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by estruch18 on 23/11/15.
 */
public abstract class Adaptador extends BaseAdapter{
    //Atributos de la clase
    private Context contexto;
    private int idLayout;
    private ArrayList<?> datos;

    //Constructor de la clase
    public Adaptador(Context contexto, int idLayout, ArrayList<?> datos) {
        this.contexto = contexto;
        this.idLayout = idLayout;
        this.datos = datos;
    }

    //Getters y Setters de la clase
    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //Método encargado de inflar el XML
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            //Acceso a servicio para hinchar layouts
            LayoutInflater li = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Hinchamos el layout
            convertView = li.inflate(idLayout, null);
        }

        onEntrada (datos.get(position), convertView); //Llamada a método "onEntrada()"
        return convertView;
    }

    //Método abstracto "onEntrada" encargado de la fusión de XML y los datos
    public abstract void onEntrada(Object objeto, View v);
}
