package com.example.estruch18.examenmultimedia;

/**
 * Created by estruch18 on 23/11/15.
 */
public class Cobrament {
    //Atributos de la clase
    private String nombreCobrador;
    private float importe;
    private String concepto;

    //Constructor de la clase
    public Cobrament(String nombreCobrador, float importe, String concepto) {
        this.nombreCobrador = nombreCobrador;
        this.importe = importe;
        this.concepto = concepto;
    }

    //Getters y Setters de la clase
    public String getNombreCobrador() {
        return nombreCobrador;
    }

    public void setNombreCobrador(String nombreCobrador) {
        this.nombreCobrador = nombreCobrador;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
}
