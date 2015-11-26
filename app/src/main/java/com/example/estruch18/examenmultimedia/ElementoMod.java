package com.example.estruch18.examenmultimedia;

/**
 * Created by estruch18 on 23/11/15.
 */
public class ElementoMod {

    private int idImagen;
    private String pagador, cobrador;
    private float importe;
    private String concepto;

    public ElementoMod(int idImagen, String pagador, String cobrador, float importe, String concepto) {
        this.idImagen = idImagen;
        this.pagador = pagador;
        this.cobrador = cobrador;
        this.importe = importe;
        this.concepto = concepto;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getPagador() {
        return pagador;
    }

    public void setPagador(String pagador) {
        this.pagador = pagador;
    }

    public String getCobrador() {
        return cobrador;
    }

    public void setCobrador(String cobrador) {
        this.cobrador = cobrador;
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
