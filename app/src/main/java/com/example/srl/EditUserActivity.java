package com.example.srl;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import com.example.srl.HttpRequest.API;
import com.example.srl.HttpRequest.ApiService;
import com.example.srl.entidades.Usuario;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditUserActivity extends Activity {
    String usuario;
    Usuario usuarioCuenta;
    EditText etEditNombre;
    EditText etEditApellido;
    EditText etEditUsuario;
    EditText etEditCorreo;
    EditText etEditContrasena;
    EditText etEditConfirmarContrasena;
    int administrador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        usuario = getIntent().getExtras().getString("Usuario");
        etEditNombre = (EditText) findViewById(R.id.etEditNombre);
        etEditApellido = (EditText) findViewById(R.id.etEditApellido);
        etEditUsuario = (EditText) findViewById(R.id.etEditUsuario);
        etEditCorreo = (EditText) findViewById(R.id.etEditCorreo);
        etEditContrasena = (EditText) findViewById(R.id.etEditContrasena);
        etEditConfirmarContrasena = (EditText) findViewById(R.id.etpEditConfirmarContrasena);
        api();
        Button btGuardarUsuario = (Button) findViewById(R.id.btGuardarUsuario);
        Button btCancelar = (Button) findViewById(R.id.btCancelarEdicionUsuario);
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelarEdicion();
            }
        });
        btGuardarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarDatos();
            }
        });
    }

    public void cancelarEdicion(){
        Intent personalAcitivity = new Intent(this,PersonalActivity.class);
        personalAcitivity.putExtra("Usuario",usuario);
        startActivity(personalAcitivity);
    }

    private void api() {
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
                mostrarError();
            }
        });
    }

    public void llenarDatos(Usuario usuario){
        etEditNombre.setText(usuario.getNombre());
        etEditApellido.setText(usuario.getApellido());
        etEditUsuario.setText(usuario.getNombreUsuario());
        etEditCorreo.setText(usuario.getCorreoElectronico());
        etEditContrasena.setText(usuario.getContrasena());
        administrador = usuario.getAdministrador();
    }

    public void validarDatos(){
        boolean validacion = true;
        if(etEditNombre.getText().toString().equals("")){
            validacion = false;
        }
        if(etEditUsuario.getText().toString().equals("")){
            validacion = false;
        }
        if(etEditUsuario.getText().toString().equals("")){
            validacion = false;
        }
        if(etEditCorreo.getText().toString().equals("")){
            validacion = false;
        }
        if(etEditContrasena.getText().toString().equals("")){
            validacion = false;
        }
        if(etEditConfirmarContrasena.getText().toString().equals("")){
            validacion = false;
        }
        if(validacion){
            if(etEditContrasena.getText().toString().equals(etEditConfirmarContrasena.getText().toString())){
                modificarUsuario();
            }else{
                mostrarContrasenaIncorrecta();
            }
        }else{
            mostrarCamposInvalido();
        }
    }

    public void mostrarContrasenaIncorrecta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(EditUserActivity.this);
        alerta.setMessage("Las contraseñas no coinciden verifiquen que esten bien escritas").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Contraseñas incorrectas ");
        titulo.show();
    }

    private void mostrarCamposInvalido() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(EditUserActivity.this);
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

    public void modificarUsuario(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        crearUsuario();
        Call<Usuario> call = apiService.actualizarUsuario2(usuario,usuarioCuenta.getNombreUsuario(),usuarioCuenta.getNombre(),usuarioCuenta.getApellido(),usuarioCuenta.getCorreoElectronico(),usuarioCuenta.getContrasena(),usuarioCuenta.getAdministrador());
        call.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                if(response.isSuccessful()){
                    mostrarAlertaActualizacion();
                }else{
                    mostrarAlerta();
                }
            }

            @Override
            public void onFailure(Call<Usuario> call, Throwable t) {
                mostrarError();
            }
        });
    }

    public void crearUsuario(){
        usuarioCuenta = new Usuario(etEditUsuario.getText().toString(),etEditNombre.getText().toString(),etEditApellido.getText().toString(),etEditCorreo.getText().toString(),etEditContrasena.getText().toString(),administrador);
    }

    public void mostrarError(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(EditUserActivity.this);
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
    public void mostrarAlertaActualizacion(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(EditUserActivity.this);
        alerta.setMessage("El usuario se ha actualizado de manera exitosa").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                actualizarVentana();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Usuario Actualizado");
        titulo.show();
    }
    public void mostrarAlerta(){
        AlertDialog.Builder alerta = new AlertDialog.Builder(EditUserActivity.this);
        alerta.setMessage("No se ha podido actualizar el usuario").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog titulo = alerta.create();
        titulo.setTitle("Error al actualizar");
        titulo.show();
    }

    public void actualizarVentana(){
        Intent editUserActivity = new Intent(this,EditUserActivity.class);
        editUserActivity.putExtra("Usuario",usuario);
        startActivity(editUserActivity);
    }

    @Override
    public void onBackPressed(){

    }
}
