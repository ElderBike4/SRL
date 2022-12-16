package com.example.srl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.srl.HttpRequest.API;
import com.example.srl.HttpRequest.ApiService;
import com.example.srl.entidades.Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonalActivity extends AppCompatActivity {

    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        usuario = getIntent().getExtras().getString("Usuario");
        Button btEditarPerfil = (Button) findViewById(R.id.btEditarPerfil);
        Button btEliminarPerfil = (Button) findViewById(R.id.btEliminarPerfil);
        Button btSalir = (Button) findViewById(R.id.btSalir);
        Button btCerrarSesion = (Button) findViewById(R.id.btCerrarSesion); 
        api();
        btEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarPerdil(view);
            }
        });
        btEliminarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarPerfil();
            }
        });
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarMain();
            }
        });
        btCerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cerrarSesion();
            }
        });
    }
    
    public void cerrarSesion(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void regresarMain(){
        Intent mainAdminActivity = new Intent(this, MainAdminActivity.class);
        mainAdminActivity.putExtra("Usuario",usuario);
        startActivity(mainAdminActivity);
    }

    public void editarPerdil(View view){
        Intent editUserActivity = new Intent(this,EditUserActivity.class);
        editUserActivity.putExtra("Usuario",usuario);
        startActivity(editUserActivity);
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
                    llenarDatos(usuario);
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {

            }
        });
    }

    public void llenarDatos(Usuario usuario){
        TextView tvNombre = (TextView) findViewById(R.id.tvNombre);
        TextView tvNombreUsuario = (TextView) findViewById(R.id.tvNombreUsuario);
        TextView tvCorreoElectronico = (TextView) findViewById(R.id.tvCorreoElectronico);
        TextView tvTipoUsuario = (TextView) findViewById(R.id.tvTipoUsuario);
        tvNombre.setText(usuario.getNombre());
        tvNombreUsuario.setText(usuario.getNombreUsuario());
        tvCorreoElectronico.setText(usuario.getCorreoElectronico());
        if(usuario.getAdministrador()==0){
            tvTipoUsuario.setText("Administrador");
        }else{
            tvTipoUsuario.setText("No Administrador");
        }
    }
    
    public void eliminarPerfil(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Usuario> call = apiService.eliminarUsuario(usuario);
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    mostrarPerfilEliminado();
                }else{
                    mostrarError();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mostrarError();
            }
        });
    }
    
    public void mostrarPerfilEliminado(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(PersonalActivity.this);
        alerta.setMessage("Su perfil ha sido eliminado, se redireccionará a la pantalla de inicio").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                irMenuPrincipal();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Perfil Eliminado");
        titulo.show();
    }

    public void mostrarError(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(PersonalActivity.this);
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
    public void irMenuPrincipal(){
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
    @Override
    public void onBackPressed(){

    }
}
