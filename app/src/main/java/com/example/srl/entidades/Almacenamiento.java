package com.example.srl.entidades;

public class Almacenamiento {

    private String idRegistro;
    private String tipoAlmacenamiento;

    public Almacenamiento(String idRegistro,String tipoAlmacenamiento) {
        this.idRegistro =idRegistro;
        this.tipoAlmacenamiento = tipoAlmacenamiento;
    }

    public String getTipoAlmacenamiento() {
        return tipoAlmacenamiento;
    }

    public void setTipoAlmacenamiento(String tipoAlmacenamiento) {
        this.tipoAlmacenamiento = tipoAlmacenamiento;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }
}
