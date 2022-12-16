package com.example.srl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.srl.HttpRequest.API;
import com.example.srl.HttpRequest.ApiService;
import com.example.srl.entidades.Usuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    String usuario;
    String contrasena;
    EditText etLoginUsuario;
    EditText etContrasena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btIniciarSesion = (Button) findViewById(R.id.btIniciarSesion);
        btIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampos();
            }
        });

        Button btRegistrarse = (Button) findViewById(R.id.btRegistrarUsuario);
        btRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarse(view);
            }
        });
    }

    public void registrarse(View view){
        Intent ventanaRegistrarUsuario = new Intent(this, RegisterUserActivity.class);
        startActivity(ventanaRegistrarUsuario);
    }

    public void validarCampos(){
        etLoginUsuario = (EditText) findViewById(R.id.etLoginUsuario);
        etContrasena = (EditText) findViewById(R.id.etpLoginContrasena);
        usuario = etLoginUsuario.getText().toString();
        contrasena = etContrasena.getText().toString();
        if(usuario.equals("")||contrasena.equals("")){
            mostrarAlerta();
        }else{
            api();
        }
    }
    public void api(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Usuario> call = apiService.getUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    Usuario usuario = response.body();
                    iniciarSesion(usuario);
                }else{
                    mostrarAlertaUsuario();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mostrarError();
            }
        });
    }

    public void mostrarAlerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
        alerta.setMessage("No se puede dejar el usuario o contraseña vacío").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Usuario o Contraseña vacío");
        titulo.show();
    }

    public void iniciarSesion(Usuario objetcUsuario){
        if(objetcUsuario!=null){
            if(objetcUsuario.getNombreUsuario().equals(etLoginUsuario.getText().toString())&objetcUsuario.getContrasena().equals(etContrasena.getText().toString())){
                Intent mainAdminActivity = new Intent(this, MainAdminActivity.class);
                mainAdminActivity.putExtra("Usuario",objetcUsuario.getNombreUsuario());
                startActivity(mainAdminActivity);
            }else{
                mostrarAlertaUsuario();
            }
        }else{
            mostrarAlertaUsuario();
        }
    }

    private void mostrarAlertaUsuario() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
        alerta.setMessage("La contraseña o el usuario es incorrecto, verifique que este bien escrito").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Usuario o contraseña incorrectos");
        titulo.show();
    }

    public void mostrarError(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(LoginActivity.this);
        alerta.setMessage("intentelo más tarde").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Ha ocurrido un error");
        titulo.show();
    }


    @Override
    public void onBackPressed(){
        Intent mainAcitivity = new Intent(this, MainActivity.class);
        startActivity(mainAcitivity);
    }
}