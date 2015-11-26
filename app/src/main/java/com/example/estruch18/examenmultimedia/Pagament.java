package com.example.estruch18.examenmultimedia;

/**
 * Created by estruch18 on 23/11/15.
 */
public class Pagament {
    //Atributos de la clase
    private String nombrePagador;
    private float importe;
    private String concepto;

    //Contructor de la clase
    public Pagament(String nombrePagador, float importe, String concepto) {
        this.nombrePagador = nombrePagador;
        this.importe = importe;
        this.concepto = concepto;
    }

    //Getters y Setters de la clase
    public String getNombrePagador() {
        return nombrePagador;
    }

    public void setNombrePagador(String nombrePagador) {
        this.nombrePagador = nombrePagador;
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
