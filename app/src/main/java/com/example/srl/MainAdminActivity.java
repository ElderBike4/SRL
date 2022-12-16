package com.example.srl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.srl.HttpRequest.API;
import com.example.srl.HttpRequest.ApiService;
import com.example.srl.entidades.Laptop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainAdminActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{
    List<Laptop> laptopsList;
    SearchView svBusqueda;
    RecyclerView rvLaptops;
    String usuario;
    AdapterLaptops adapterLaptops;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        usuario = getIntent().getExtras().getString("Usuario");
        Button btSesionAdmin = (Button) findViewById(R.id.btSesionAdmin);
        TextView tvUsuarioAdmin = (TextView) findViewById(R.id.tvUsuarioAdmin);
        rvLaptops = (RecyclerView) findViewById(R.id.recyclerView);
        svBusqueda = findViewById(R.id.svBusqueda);
        tvUsuarioAdmin.setText(usuario);
        getLaptops();
        btSesionAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfilPersonal(view);
            }
        });
        Button btRegistrarLaptop = (Button) findViewById(R.id.btRegistrarLaptop);
        btRegistrarLaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarLaptop(view);
            }
        });
        svBusqueda.setOnQueryTextListener(this);
    }

    public void perfilPersonal(View view){
        Intent personalActivity = new Intent(this,PersonalActivity.class);
        personalActivity.putExtra("Usuario",usuario);
        startActivity(personalActivity);
    }

    public void registrarLaptop(View view){
        Intent registerLaptopActivity = new Intent(this,RegisterLaptopActivity.class);
        registerLaptopActivity.putExtra("Usuario", usuario);
        startActivity(registerLaptopActivity);
    }

    private void getLaptops() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.getUrl()).addConverterFactory((GsonConverterFactory.create())).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<Laptop[]> call = apiService.getLaptops();
        call.enqueue(new Callback<Laptop[]>() {
            @Override
            public void onResponse(Call<Laptop[]> call, Response<Laptop[]> response) {
                if(!response.isSuccessful()){
                    Log.d("notas","onResponse: "+ response.code());
                }else{
                    laptopsList = Arrays.asList(response.body());
                    mostrarLaptops();
                }
            }

            @Override
            public void onFailure(Call<Laptop[]> call, Throwable t) {

            }
        });
    }

    public void mostrarLaptops(){
        ArrayList<String> listDatos = new ArrayList<>();
        for(int i=0;i<laptopsList.size();i++){
            listDatos.add(laptopsList.get(i).getModelo() );
        }
        rvLaptops.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapterLaptops = new AdapterLaptops(listDatos, new AdapterLaptops.OnItemClickListener() {
            @Override
            public void onItemClickListener(String item) {
                for(int i=0;i<laptopsList.size();i++){
                    if(laptopsList.get(i).getModelo().equals(item)){
                        irADetalles(laptopsList.get(i).getIdRegistro());
                        break;
                    }
                }
            }
        });
        rvLaptops.setAdapter(adapterLaptops);
    }
    public void irADetalles(String idRegistro){
        Intent detailsLaptopActivity = new Intent(this,DetailsLaptopActivity.class);
        detailsLaptopActivity.putExtra("Usuario",usuario);
        detailsLaptopActivity.putExtra("idRegistro",idRegistro);
        startActivity(detailsLaptopActivity);
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        adapterLaptops.filtrado(s);
        return false;
    }
    @Override
    public void onBackPressed(){

    }
}