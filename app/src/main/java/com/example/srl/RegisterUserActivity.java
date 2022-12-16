package com.example.srl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.srl.HttpRequest.API;
import com.example.srl.HttpRequest.ApiService;
import com.example.srl.entidades.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterUserActivity extends AppCompatActivity {

    EditText etNombre;
    EditText etApellido;
    EditText etCorreo;
    EditText etUsuario;
    EditText etContrasena;
    EditText etConfirmarContrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etCorreo = (EditText) findViewById(R.id.etCorreo);
        etUsuario = (EditText) findViewById(R.id.etUsuario);
        etContrasena = (EditText) findViewById(R.id.etpContrasena);
        etConfirmarContrasena = (EditText) findViewById(R.id.etpConfirmarContrasena);
        Button btRegistroUsuario = (Button) findViewById(R.id.btRegistroUsuario);
        Button btCancelarRegistro = (Button) findViewById(R.id.btCancelarRegistroUsuario);
        btRegistroUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });
        btCancelarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarRegistro();
            }
        });
    }

    public void validarCampos(){
        boolean validacion = true;
        if(etNombre.getText().toString().equals("")){
            validacion = false;
        }
        if(etApellido.getText().toString().equals("")){
            validacion = false;
        }
        if(etUsuario.getText().toString().equals("")){
            validacion = false;
        }
        if(etCorreo.getText().toString().equals("")){
            validacion = false;
        }
        if(etContrasena.getText().toString().equals("")){
            validacion = false;
        }
        if(etConfirmarContrasena.getText().toString().equals("")){
            validacion = false;
        }
        if(validacion){
            if(verificarPassword()){
                registrarUsuario(crearUsuario());
            }else{
                mostrarAlertaPassword();
            }
        }else{
            mostrarAlertaCampos();
        }
    }
    public Usuario crearUsuario(){
        Usuario usuario = new Usuario(etUsuario.getText().toString(),etNombre.getText().toString(),etApellido.getText().toString(),etCorreo.getText().toString(),
                etContrasena.getText().toString(),0);
        return usuario;
    }
    public void registrarUsuario(Usuario usuario){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Usuario> call = apiService.registrarUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    mostrarAlertaRegistro();
                }else{

                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    public void mostrarAlertaRegistro(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterUserActivity.this);
        alerta.setMessage("Se ha creado el usuario de manera satisfactoria").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                regresarMain();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Registro Exitoso");
        titulo.show();
    }
    public void regresarMain(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void cerrarRegistro(){
        Intent loginActivity = new Intent(this,LoginActivity.class);
        startActivity(loginActivity);
    }

    public boolean verificarPassword(){
        boolean validacion = false;
        if(etContrasena.getText().toString().equals(etConfirmarContrasena.getText().toString())){
            validacion = true;
        }
        return validacion;
    }

    public void mostrarAlertaPassword(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterUserActivity.this);
        alerta.setMessage("Las contraseña no coinciden").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Contraseñas diferentes");
        titulo.show();
    }

    public void mostrarAlertaCampos(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterUserActivity.this);
        alerta.setMessage("No se pueden quedar campos vacios").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Campos Vacíos");
        titulo.show();
    }
    @Override
    public void onBackPressed(){

    }
}