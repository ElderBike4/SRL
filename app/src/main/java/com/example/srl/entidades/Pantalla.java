package com.example.srl.entidades;

public class Pantalla {

    private String idRegistro;
    private String modelo;
    private String resolucion;
    private String calidad;
    private String tipoPantalla;
    private String tamanio;
    private int frecuenciaRefresco;

    public Pantalla(String idRegistro, String toString) {
        this.idRegistro=idRegistro;
        this.modelo=toString;
    }

    public int getFrecuenciaRefresco() {
        return frecuenciaRefresco;
    }

    public void setFrecuenciaRefresco(int frecuenciaRefresco) {
        this.frecuenciaRefresco = frecuenciaRefresco;
    }

    public String getTamanio() {
        return tamanio;
    }

    public void setTamanio(String tamanio) {
        this.tamanio = tamanio;
    }

    public String getTipoPantalla() {
        return tipoPantalla;
    }

    public void setTipoPantalla(String tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }
}
