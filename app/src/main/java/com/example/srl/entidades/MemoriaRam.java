package com.example.srl.entidades;

public class MemoriaRam {
    private String idRegistro;
    private String modelo;
    private String marca;
    private String tipoMemoria;
    private int cantidadMemoria;
    private int cantidadMemorias;
    private int velocidad;
    private int ecc;

    public MemoriaRam(String idRegistro, String toString) {
        this.idRegistro = idRegistro;
        this.modelo = toString;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setEcc(int ecc) {
        this.ecc = ecc;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public void setCantidadMemorias(int cantidadMemorias) {
        this.cantidadMemorias = cantidadMemorias;
    }

    public void setCantidadMemoria(int cantidadMemoria) {
        this.cantidadMemoria = cantidadMemoria;
    }

    public void setTipoMemoria(String tipoMemoria) {
        this.tipoMemoria = tipoMemoria;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getEcc() {
        return ecc;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getCantidadMemorias() {
        return cantidadMemorias;
    }

    public int getCantidadMemoria() {
        return cantidadMemoria;
    }

    public String getTipoMemoria() {
        return tipoMemoria;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }
}
