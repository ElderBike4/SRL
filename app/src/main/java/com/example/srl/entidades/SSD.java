package com.example.srl.entidades;

public class SSD {

    private String idRegistro;
    private String marca;
    private String modelo;
    private int capacidad;
    private String factorForma;
    private String durabilidad;
    private String tipoMemorias;
    private String velocidadLectura;
    private String velocidadEscritura;
    private String protocolo;

    public SSD(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getVelocidadEscritura() {
        return velocidadEscritura;
    }

    public void setVelocidadEscritura(String velocidadEscritura) {
        this.velocidadEscritura = velocidadEscritura;
    }

    public String getVelocidadLectura() {
        return velocidadLectura;
    }

    public void setVelocidadLectura(String velocidadLectura) {
        this.velocidadLectura = velocidadLectura;
    }

    public String getTipoMemorias() {
        return tipoMemorias;
    }

    public void setTipoMemorias(String tipoMemorias) {
        this.tipoMemorias = tipoMemorias;
    }

    public String getDurabilidad() {
        return durabilidad;
    }

    public void setDurabilidad(String durabilidad) {
        this.durabilidad = durabilidad;
    }

    public String getFactorForma() {
        return factorForma;
    }

    public void setFactorForma(String factorForma) {
        this.factorForma = factorForma;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }
}
