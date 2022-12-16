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
import com.example.srl.entidades.Laptop;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsLaptopActivity extends AppCompatActivity {
    TextView tvModelo;
    TextView tvProcesador;
    TextView tvTarjetaVideo;
    TextView tvRam;
    TextView tvPantalla;
    TextView tvAlmacenamiento;
    String usuario;
    String idRegistro;
    Laptop laptop;
    Button btEliminarLaptop;
    Button btEditarLaptop;
    Button btSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_laptop);
        usuario = getIntent().getExtras().getString("Usuario","");
        idRegistro = getIntent().getExtras().getString("idRegistro","");
        tvModelo = (TextView) findViewById(R.id.tvDetailsModelo);
        tvProcesador = (TextView) findViewById(R.id.tvDetailsProcesador);
        tvTarjetaVideo = (TextView) findViewById(R.id.tvDetailsTajetaVideo);
        tvRam = (TextView) findViewById(R.id.tvDetailsRam);
        tvPantalla = (TextView) findViewById(R.id.tvDetailsPantalla);
        tvAlmacenamiento = (TextView) findViewById(R.id.tvDetailsAlmacenamiento);
        btEliminarLaptop = (Button) findViewById(R.id.btEliminarLaptop);
        btEditarLaptop = (Button) findViewById(R.id.btEditarLaptop);
        btSalir = (Button) findViewById(R.id.btSalirLaptop);
        mostrarDetalles();
        if(usuario.equals("")){
            btEliminarLaptop.setEnabled(false);
            btEditarLaptop.setEnabled(false);
        }else{
            btEliminarLaptop.setEnabled(true);
            btEditarLaptop.setEnabled(true);
        }
        btEliminarLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarLaptop();
            }
        });
        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salir();
            }
        });
    }

    public void mostrarDetalles(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Laptop> call = apiService.getLaptopById(idRegistro);
        call.enqueue(new Callback<Laptop>() {
            @Override
            public void onResponse(Call<Laptop> call, Response<Laptop> response) {
                if(response.isSuccessful()){
                    laptop = response.body();
                    mostrarDatos(laptop);
                }else{
                    mostrarAlerta();
                }
            }

            @Override
            public void onFailure(Call<Laptop> call, Throwable t) {
                mostrarError();
            }
        });
    }
    public void mostrarDatos(Laptop laptop){
        tvModelo.setText(laptop.getModelo());
        tvProcesador.setText(laptop.getProcesador());
        tvTarjetaVideo.setText(laptop.getTarjetaVideo());
        tvRam.setText(laptop.getMemoriaRam());
        tvPantalla.setText(laptop.getPantalla());
        tvAlmacenamiento.setText(laptop.getAlmacenamiento());
    }
    public void mostrarError(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(DetailsLaptopActivity.this);
        alerta.setMessage("Intentelo más tarde").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Ha ocurrido un error");
        titulo.show();
    }

    public void mostrarAlerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(DetailsLaptopActivity.this);
        alerta.setMessage("No se ha podido recuperar los detalles de la laptop").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("No se puede recuperar la laptop");
        titulo.show();
    }

    public void eliminarLaptop(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Laptop> call = apiService.eliminarLaptop(laptop.getIdRegistro());
        call.enqueue(new Callback<Laptop>() {
            @Override
            public void onResponse(Call<Laptop> call, Response<Laptop> response) {
                if(response.isSuccessful()){
                    mostrarAlertaEliminar();
                }else{

                }
            }

            @Override
            public void onFailure(Call<Laptop> call, Throwable t) {
                mostrarError();
            }
        });
    }
    public void mostrarAlertaEliminar(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(DetailsLaptopActivity.this);
        alerta.setMessage("La laptop ha sido eliminada").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                if(usuario.equals("")){
                    ventanaPrincipal();
                }else{
                    ventanaAdmin();
                }
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Laptop Eliminada");
        titulo.show();
    }

    public void ventanaPrincipal(){
        Intent mainActivity = new Intent(this,MainActivity.class);
        startActivity(mainActivity);
    }

    public void ventanaAdmin(){
        Intent mainAdminActivity = new Intent(this,MainAdminActivity.class);
        mainAdminActivity.putExtra("Usuario",usuario);
        startActivity(mainAdminActivity);
    }

    public void salir(){
        if(usuario.equals("")){
            ventanaPrincipal();
        }else{
            ventanaAdmin();
        }
    }

    @Override
    public void onBackPressed(){

    }
}