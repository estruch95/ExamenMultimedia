package com.example.estruch18.examenmultimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListasCobrosYpagos extends Activity {
    //Atributos de la clase
    private ListView listView;
    private ArrayList<ElementoMod> elementosLista;
    private Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listascobros_pagos);

        //Ejecución de métodos
        declaracionViews();
        actualizarTituloActivity();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listas_cobros_ypagos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void declaracionViews(){
        listView = (ListView)findViewById(R.id.listView);
        elementosLista = new ArrayList<ElementoMod>();
        cargarDatosLista();
        adaptador = new Adaptador(this, R.layout.elemento_mod, elementosLista) {
            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {
                    TextView pagador = (TextView) view.findViewById(R.id.tvPagador);
                    if (pagador != null)
                        pagador.setText(((ElementoMod) entrada).getPagador());

                    TextView cobrador = (TextView) view.findViewById(R.id.tvCobrador);
                    if (cobrador != null)
                        cobrador.setText(((ElementoMod) entrada).getCobrador());

                    TextView importe = (TextView) view.findViewById(R.id.tvImporte);
                    if(importe != null){
                        importe.setText(String.valueOf(((ElementoMod) entrada).getImporte())+" €");
                    }

                    TextView concepto = (TextView) view.findViewById(R.id.tvConcepto);
                    if(concepto != null){
                        concepto.setText(((ElementoMod) entrada).getConcepto());
                    }

                    ImageView img = (ImageView) view.findViewById(R.id.img);
                    if(img != null){
                        img.setImageResource(((ElementoMod) entrada).getIdImagen());
                    }
                }
            }
        };
        listView.setAdapter(adaptador);
    }

    public void cargarDatosLista(){
        elementosLista.add(new ElementoMod(R.mipmap.ic_launcher, "Pepe", "Sancho", (float) 450.0, "PS4"));
        elementosLista.add(new ElementoMod(R.mipmap.ic_launcher, "Ivan", "Alvaro", (float) 1500.0, "Giant"));
        elementosLista.add(new ElementoMod(R.mipmap.ic_launcher, "Joaquin", "David", (float) 1399.0, "MacBook Pro Retina 13'"));
    }

    public void actualizarTituloActivity(){

        Bundle datos = this.getIntent().getExtras();
        if(datos.getBoolean("cobro")){
            this.getActionBar().setTitle("Llista de cobraments");
        }
        else if(datos.getBoolean("pago")==false){
            this.getActionBar().setTitle("Llista de pagaments");
        }


    }

    public void accionBtnAtras(View v){
        Intent actPrincipal = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(actPrincipal);
    }
}
