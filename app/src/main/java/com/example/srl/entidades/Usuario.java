package com.example.srl.entidades;

import com.google.gson.annotations.SerializedName;

public class Usuario {
    @SerializedName("nombreUsuario")

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String correoElectronico;
    private String contrasena;
    private int administrador;

    public Usuario(String nombreUsuario, String nombre, String apellido, String correoElectronico, String contrasena, int administrador){
        this.nombreUsuario= nombreUsuario;
        this.nombre=nombre;
        this.apellido=apellido;
        this.correoElectronico=correoElectronico;
        this.contrasena=contrasena;
        this.administrador=administrador;
    }

    public Usuario(){

    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int administrador) {
        this.administrador = administrador;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
