package com.example.estruch18.examenmultimedia;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {
    //Atributos de la clase
    private ArrayList<Pagament> pagos;
    private ArrayList<Cobrament> cobros;

    //REQUEST_CODE
    private static final int SUB_ACTIVITY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ejecución de métodos
        declaracionViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        //Declaración de objetos
        pagos = new ArrayList<Pagament>();
        cobros = new ArrayList<Cobrament>();
    }

    //LISTENERS DE LOS BOTONES
    public void accionBtnCobraments(View v){
        //Cambio de Activity mediante Intent (con paso de datos)
        Intent subActivity = new Intent(getApplicationContext(), SubActivity.class);
        subActivity.putExtra("cobro", true);
        startActivityForResult(subActivity, SUB_ACTIVITY);
    }

    public void accionBtnPagaments(View v){
        Intent subActivity = new Intent(getApplicationContext(), SubActivity.class);
        subActivity.putExtra("cobro", false);
        startActivityForResult(subActivity, SUB_ACTIVITY);
    }

    public void accionBtnLlistaCobraments(View v){
        //Cambio de Activity mediante Intent (con paso de datos)
        Intent actListas = new Intent(getApplicationContext(), ListasCobrosYpagos.class);
        actListas.putExtra("cobro", true);
        startActivity(actListas);

    }

    public void accionBtnLlistaPagaments(View v){
        //Cambio de Activity mediante Intent (con paso de datos)
        Intent actListas = new Intent(getApplicationContext(), ListasCobrosYpagos.class);
        actListas.putExtra("pago", false);
        startActivity(actListas);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Gestión del requestCode
        switch (requestCode){
            case SUB_ACTIVITY:
                this.gestionSubActivity(resultCode, data);
                break;
        }
    }

    public void gestionSubActivity(int resultCode, Intent data){
        //Comprobación del resultCode
        if(resultCode == RESULT_OK){
            Bundle datosObtenidos = data.getExtras().getBundle("datosCapturados");
            //Añadir a los ArrayList los datos capturados
            String pagador = datosObtenidos.getString("nombrePagador");
            String cobrador = datosObtenidos.getString("cobrador");
            float importe = Float.parseFloat(datosObtenidos.getString("importe"));
            String concepto = datosObtenidos.getString("concepto");
            String tipoPersona = datosObtenidos.getString("tipoPersona");

            if(tipoPersona.equals("pagador")){
                pagos.add(new Pagament(pagador, importe, concepto));
                //COMPROBACIÓN DE AÑADIDOS A ARRAYLIST DE PAGOS
                for(int x=0; x<this.pagos.size(); x++){
                    Toast.makeText(getApplicationContext(), this.pagos.get(x).getNombrePagador(), Toast.LENGTH_SHORT).show();
                }
            }
            else{
                cobros.add(new Cobrament(cobrador, importe, concepto));
                //COMPROBACIÓN DE AÑADIDOS A ARRAYLIST DE COBROS
                for(int z=0; z<this.cobros.size(); z++){
                    Toast.makeText(getApplicationContext(), this.cobros.get(z).getNombreCobrador(), Toast.LENGTH_SHORT).show();
                }
            }

            //Creación de una notificación
            Notification.Builder nb = new Notification.Builder(getApplicationContext());
            nb.setContentTitle("Se ha añadido un cobro correctamente.");
            nb.setSmallIcon(R.mipmap.ic_launcher);
            //Notificación expandida
            Notification.InboxStyle is = new Notification.InboxStyle();
            is.addLine("De: "+datosObtenidos.getString("nombrePagador"));
            is.addLine("A: "+datosObtenidos.getString("cobrador"));
            is.addLine("Concepto: " + datosObtenidos.getString("concepto"));
            is.addLine("Importe: " + datosObtenidos.getString("importe")+" €");
            //Aplicación de estilo
            nb.setStyle(is);
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            nm.notify(1, nb.build());
        }
    }
}
