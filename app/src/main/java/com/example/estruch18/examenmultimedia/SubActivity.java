package com.example.estruch18.examenmultimedia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class SubActivity extends Activity {
    //Atributos de la clase
    private EditText nombrePagador, importe, concepto;
    private Spinner spCobradores;
    private Button btnEnviarDatos;

    private String cobradorSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subactivity);

        //Ejecución de métodos
        declaracionViews();
        loadCobradores();
        getCobrador();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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
        //Declaración de atributos y objetos de la clase
        nombrePagador = (EditText)findViewById(R.id.etNombrePagador);
        importe = (EditText)findViewById(R.id.etImporte);
        concepto = (EditText)findViewById(R.id.etConcepto);
        spCobradores = (Spinner)findViewById(R.id.spCobradores);
        btnEnviarDatos = (Button)findViewById(R.id.btnEnviarDatos);
    }

    //Método encargado de cargar datos en el Spinner
    public void loadCobradores(){
        ArrayAdapter adaptador = ArrayAdapter.createFromResource(this, R.array.cobradores, android.R.layout.simple_spinner_item);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCobradores.setAdapter(adaptador);
    }

    //Método encargado de obtener el cobrador seleccionado en el Spinner
    public void getCobrador(){
        spCobradores.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cobradorSeleccionado = spCobradores.getSelectedItem().toString();
                //Comprobación
                //Toast.makeText(getApplicationContext(), cobradorSeleccionado, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //Método cuya función es capturar los datos que nos interesan acerca de un nuevo pago/cobro
    public void capturarDatos(){
        Bundle datos = this.getIntent().getExtras();
        String tipoPersona = null;

        if(datos.getBoolean("cobro")){
            tipoPersona = "cobrador";
            //Toast.makeText(getApplicationContext(), tipoPersona, Toast.LENGTH_SHORT).show();
        }
        else if(datos.getBoolean("pago")==false){
            tipoPersona = "pagador";
            //Toast.makeText(getApplicationContext(), tipoPersona, Toast.LENGTH_SHORT).show();
        }

        Intent intentActual = this.getIntent();

        //Capturamos los datos
        Bundle datosCapturados = new Bundle();

        datosCapturados.putString("nombrePagador", this.nombrePagador.getText().toString());
        datosCapturados.putString("cobrador", this.cobradorSeleccionado);
        datosCapturados.putString("importe", this.importe.getText().toString());
        datosCapturados.putString("concepto", this.concepto.getText().toString());
        datosCapturados.putString("tipoPersona", tipoPersona);

        intentActual.putExtra("datosCapturados", datosCapturados);

        setResult(RESULT_OK, intentActual);
        finish();
    }

    //Comprobación de campos vacios
    public void accionBtnEnviarDatos(View v){
        if(nombrePagador.getText().toString().length()!=0 && importe.getText().toString().length()!=0 && concepto.getText().toString().length()!=0){
            capturarDatos();
        }
        else{
            Toast.makeText(getApplicationContext(), "Hay campos vacios, rellene porfavor", Toast.LENGTH_SHORT).show();
        }

    }
}
