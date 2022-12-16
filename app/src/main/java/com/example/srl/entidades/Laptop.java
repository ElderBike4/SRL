package com.example.srl.entidades;

import java.io.Serializable;

public class Laptop implements Serializable {

    private String idRegistro;
    private String modelo;
    private String memoriaRam;
    private String tarjetaVideo;
    private String pantalla;
    private String almacenamiento;
    private String procesador;

    public Laptop(String idRegistro, String modelo, String memoriaRam, String tarjetaVideo, String pantalla, String almacenamiento, String procesador) {
        this.idRegistro=idRegistro;
        this.modelo=modelo;
        this.memoriaRam=memoriaRam;
        this.tarjetaVideo=tarjetaVideo;
        this.pantalla=pantalla;
        this.procesador=procesador;
        this.almacenamiento = almacenamiento;
    }

    public Laptop() {

    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMemoriaRam() {
        return memoriaRam;
    }

    public String getTarjetaVideo() {
        return tarjetaVideo;
    }

    public String getPantalla() {
        return pantalla;
    }

    public String getAlmacenamiento() {
        return almacenamiento;
    }

    public String getProcesador() {
        return procesador;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setMemoriaRam(String memoriaRam) {
        this.memoriaRam = memoriaRam;
    }

    public void setTarjetaVideo(String tarjetaVideo) {
        this.tarjetaVideo = tarjetaVideo;
    }

    public void setPantalla(String pantalla) {
        this.pantalla = pantalla;
    }

    public void setAlmacenamiento(String almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public void setProcesador(String procesador) {
        this.procesador = procesador;
    }
}
