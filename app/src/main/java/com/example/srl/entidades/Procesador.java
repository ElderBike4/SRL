package com.example.srl.entidades;

public class Procesador {

    private String idRegistro;
    private String modelo;
    private String marca;
    private int numeroNucleos;
    private int numeroHilos;
    private int velocidadMaxima;
    private int velocidadMinima;
    private int litografia;

    public Procesador(String idRegistro, String toString) {
        this.idRegistro=idRegistro;
        this.modelo = toString;
    }

    public int getLitografia() {
        return litografia;
    }

    public void setLitografia(int litografia) {
        this.litografia = litografia;
    }

    public int getVelocidadMinima() {
        return velocidadMinima;
    }

    public void setVelocidadMinima(int velocidadMinima) {
        this.velocidadMinima = velocidadMinima;
    }

    public int getVelocidadMaxima() {
        return velocidadMaxima;
    }

    public void setVelocidadMaxima(int velocidadMaxima) {
        this.velocidadMaxima = velocidadMaxima;
    }

    public int getNumeroHilos() {
        return numeroHilos;
    }

    public void setNumeroHilos(int numeroHilos) {
        this.numeroHilos = numeroHilos;
    }

    public int getNumeroNucleos() {
        return numeroNucleos;
    }

    public void setNumeroNucleos(int numeroNucleos) {
        this.numeroNucleos = numeroNucleos;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
