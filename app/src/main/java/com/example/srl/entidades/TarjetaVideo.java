package com.example.srl.entidades;

import android.widget.EditText;

public class TarjetaVideo {

    private String idRegistro;
    private String modelo;
    private String marca;
    private int cantidadVram;
    private String tipoMemoria;
    private int bits;
    private int velocidadReloj;
    private String tipo;


    public TarjetaVideo(String idRegistro, String toString) {
        this.idRegistro=idRegistro;
        this.modelo = toString;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getVelocidadReloj() {
        return velocidadReloj;
    }

    public void setVelocidadReloj(int velocidadReloj) {
        this.velocidadReloj = velocidadReloj;
    }

    public int getBits() {
        return bits;
    }

    public void setBits(int bits) {
        this.bits = bits;
    }

    public String getTipoMemoria() {
        return tipoMemoria;
    }

    public void setTipoMemoria(String tipoMemoria) {
        this.tipoMemoria = tipoMemoria;
    }

    public int getCantidadVram() {
        return cantidadVram;
    }

    public void setCantidadVram(int cantidadVram) {
        this.cantidadVram = cantidadVram;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
