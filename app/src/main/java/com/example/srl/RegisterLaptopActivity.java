package com.example.srl;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.srl.HttpRequest.API;
import com.example.srl.HttpRequest.ApiService;
import com.example.srl.entidades.Almacenamiento;
import com.example.srl.entidades.HDD;
import com.example.srl.entidades.Laptop;
import com.example.srl.entidades.MemoriaRam;
import com.example.srl.entidades.Pantalla;
import com.example.srl.entidades.Procesador;
import com.example.srl.entidades.SSD;
import com.example.srl.entidades.TarjetaVideo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterLaptopActivity extends AppCompatActivity {
    EditText etModelo;
    EditText etMarca;
    EditText etProcesador;
    EditText etPantalla;
    EditText etTarjetaVideo;
    EditText etAlmacenamiento;
    EditText etRam;
    String usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_laptop);
        Button btRegistrarLaptop = (Button) findViewById(R.id.button4);
        Button btCancelarRegistro = (Button) findViewById(R.id.btCancelar);
        usuario = getIntent().getExtras().getString("Usuario");
        etModelo = (EditText) findViewById(R.id.etModelo);
        etMarca = (EditText) findViewById(R.id.etModelo);
        etProcesador = (EditText) findViewById(R.id.etProcesador);
        etPantalla = (EditText) findViewById(R.id.etPantalla);
        etTarjetaVideo = (EditText) findViewById(R.id.etTarjetaVideo);
        etAlmacenamiento = (EditText) findViewById(R.id.etAlmacenamiento);
        etRam = (EditText) findViewById(R.id.etRam);
        btRegistrarLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarCampo();
            }
        });
        btCancelarRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regresarAInicio();
            }
        });

    }

    public void validarCampo(){
        if(etModelo.getText().toString().equals("") || etMarca.getText().toString().equals("")||etProcesador.getText().equals("")||etAlmacenamiento.getText().equals("")||etPantalla.getText().equals("")||etRam.getText().equals("")||etTarjetaVideo.getText().equals("")){
            mostrarAlertaCampos();
        }else{
            registrarLaptop();
        }
    }

    public void mostrarAlertaCampos(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterLaptopActivity.this);
        alerta.setMessage("No se pueden dejar campos vacios").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Campos Vacíos");
        titulo.show();
    }

    public void registrarLaptop(){
        Laptop laptop = new Laptop();
        laptop = crearLaptop();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Laptop> call = apiService.registrarLaptop(laptop);
        call.enqueue(new Callback<Laptop>() {
            @Override
            public void onResponse(Call<Laptop> call, Response<Laptop> response) {
                if(response.isSuccessful()){
                    Laptop laptopAuxiliar = new Laptop();
                    laptopAuxiliar = response.body();
                    System.out.println(laptopAuxiliar.getIdRegistro());
                    crearComponentes(laptopAuxiliar.getIdRegistro());
                }else{
                    mostrarAlertaRegistroError();
                }
            }

            @Override
            public void onFailure(Call<Laptop> call, Throwable t) {
                mostrarError();
            }
        });
    }

    public Laptop crearLaptop(){
        Laptop laptop = new Laptop("x",etModelo.getText().toString(),etRam.getText().toString(),etTarjetaVideo.getText().toString(),etPantalla.getText().toString(),etAlmacenamiento.getText().toString(),etProcesador.getText().toString());
        return laptop;
    }
    public MemoriaRam crearMemoriaRam(String idRegistro){
        MemoriaRam memoriaRam = new MemoriaRam(idRegistro,etRam.getText().toString());
        memoriaRam.setCantidadMemoria(0);
        memoriaRam.setEcc(0);
        memoriaRam.setCantidadMemorias(0);
        memoriaRam.setVelocidad(0);
        memoriaRam.setMarca("ND");
        memoriaRam.setTipoMemoria("ND");
        return  memoriaRam;
    }
    public Procesador crearProcesador(String idRegistro){
        Procesador procesador = new Procesador(idRegistro,etProcesador.getText().toString());
        procesador.setMarca("ND");
        procesador.setLitografia(0);
        procesador.setNumeroHilos(0);
        procesador.setNumeroNucleos(0);
        procesador.setVelocidadMaxima(0);
        procesador.setVelocidadMinima(0);
        return  procesador;
    }
    public Pantalla crearPantalla(String idRegistro){
        Pantalla pantalla = new Pantalla(idRegistro,etPantalla.getText().toString());
        pantalla.setTipoPantalla("ND");
        pantalla.setCalidad("ND");
        pantalla.setFrecuenciaRefresco(0);
        pantalla.setResolucion("ND");
        pantalla.setTamanio("ND");
        return pantalla;
    }
    public TarjetaVideo crearTarjetaVideo(String idRegistro){
        TarjetaVideo tarjetaVideo = new TarjetaVideo(idRegistro,etTarjetaVideo.getText().toString());
        tarjetaVideo.setBits(0);
        tarjetaVideo.setMarca("ND");
        tarjetaVideo.setTipoMemoria("ND");
        tarjetaVideo.setCantidadVram(0);
        tarjetaVideo.setVelocidadReloj(0);
        tarjetaVideo.setTipo("ND");
        return tarjetaVideo;
    }
    public Almacenamiento crearAlmacenamiento(String idRegistro,String tipoAlmacenamiento){
        Almacenamiento almacenamiento = new Almacenamiento(idRegistro,"");
        almacenamiento.setTipoAlmacenamiento(tipoAlmacenamiento);
        return almacenamiento;
    }
    public SSD crearSSD(String idRegistro){
        SSD ssd = new SSD(idRegistro);
        ssd.setCapacidad(0);
        ssd.setDurabilidad("ND");
        ssd.setMarca("ND");
        ssd.setFactorForma("ND");
        ssd.setProtocolo("ND");
        ssd.setTipoMemorias("ND");
        ssd.setVelocidadEscritura("ND");
        ssd.setVelocidadLectura("ND");
        return ssd;
    }
    public HDD crearHHD(String idRegistro){
        HDD hdd = new HDD(idRegistro);
        hdd.setCapacidad(0);
        hdd.setCache(0);
        hdd.setRevoluciones(0);
        hdd.setMarca("ND");
        hdd.setModelo("ND");
        hdd.setInterfaz("ND");
        hdd.setTamanio("ND");
        return hdd;
    }

    public void regitrarRam(MemoriaRam memoriaRam){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<MemoriaRam> call = apiService.registrarMemoriaRam(memoriaRam);
        call.enqueue(new Callback<MemoriaRam>() {
            @Override
            public void onResponse(Call<MemoriaRam> call, Response<MemoriaRam> response) {

            }

            @Override
            public void onFailure(Call<MemoriaRam> call, Throwable t) {

            }
        });
    }

    public void registrarPantalla(Pantalla pantalla){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Pantalla> call = apiService.registrarPantalla(pantalla);
        call.enqueue(new Callback<Pantalla>() {
            @Override
            public void onResponse(Call<Pantalla> call, Response<Pantalla> response) {

            }

            @Override
            public void onFailure(Call<Pantalla> call, Throwable t) {

            }
        });
    }

    public void registrarTarjetaVideo(TarjetaVideo tarjetaVideo){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<TarjetaVideo> call = apiService.registrarTarjetaVideo(tarjetaVideo);
        call.enqueue(new Callback<TarjetaVideo>() {
            @Override
            public void onResponse(Call<TarjetaVideo> call, Response<TarjetaVideo> response) {

            }

            @Override
            public void onFailure(Call<TarjetaVideo> call, Throwable t) {

            }
        });
    }

    public void registrarAlmacenamiento(Almacenamiento almacenamiento){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Almacenamiento> call = apiService.registrarAlmacenamiento(almacenamiento);
        call.enqueue(new Callback<Almacenamiento>() {
            @Override
            public void onResponse(Call<Almacenamiento> call, Response<Almacenamiento> response) {
                if(response.isSuccessful()){
                    mostrarAlertaRegistro();
                }else{
                    mostrarAlertaRegistroError();
                }
            }

            @Override
            public void onFailure(Call<Almacenamiento> call, Throwable t) {
                mostrarError();
            }
        });
    }

    public void crearComponentes(String idRegistro){
        regitrarRam(crearMemoriaRam(idRegistro));
        registrarProcesador(crearProcesador(idRegistro));
        registrarPantalla(crearPantalla(idRegistro));
        registrarTarjetaVideo(crearTarjetaVideo(idRegistro));
        registrarAlmacenamiento(crearAlmacenamiento(idRegistro,etAlmacenamiento.getText().toString()));
    }

    private void registrarProcesador(Procesador procesador) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Procesador> call = apiService.registrarProcesador(procesador);
        call.enqueue(new Callback<Procesador>() {
            @Override
            public void onResponse(Call<Procesador> call, Response<Procesador> response) {

            }

            @Override
            public void onFailure(Call<Procesador> call, Throwable t) {

            }
        });
    }


    public void mostrarAlertaRegistro(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterLaptopActivity.this);
        alerta.setMessage("Laptop registrada con exito").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                regresarAInicio();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Laptop Registrada");
        titulo.show();
    }

    public void mostrarError(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterLaptopActivity.this);
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

    public void mostrarAlertaRegistroError(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(RegisterLaptopActivity.this);
        alerta.setMessage("intentelo nuevamente").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("No se ha podido registrar la laptop");
        titulo.show();
    }

    public void regresarAInicio(){
        Intent mainAdminActivity = new Intent(this,MainAdminActivity.class);
        mainAdminActivity.putExtra("Usuario",usuario);
        startActivity(mainAdminActivity);
    }
    @Override
    public void onBackPressed(){

    }
}