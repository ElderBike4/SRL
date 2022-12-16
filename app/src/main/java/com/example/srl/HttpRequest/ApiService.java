package com.example.srl.HttpRequest;

import com.example.srl.entidades.Almacenamiento;
import com.example.srl.entidades.Catalogo;
import com.example.srl.entidades.HDD;
import com.example.srl.entidades.Laptop;
import com.example.srl.entidades.MemoriaRam;
import com.example.srl.entidades.Pantalla;
import com.example.srl.entidades.Procesador;
import com.example.srl.entidades.SSD;
import com.example.srl.entidades.TarjetaVideo;
import com.example.srl.entidades.Usuario;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiService {

    //Laptops

    @GET("laptops/")
    Call<Laptop[]> getLaptops();
    @GET("laptop/{idLaptop}")
    Call<Laptop> getLaptopById(@Path("idLaptop")String idLaptop);
    @GET("laptopModelo/{modeloLaptop}")
    Call<Laptop> getLaptopByModelo(@Path("modeloLaptop")String modeloLaptop);
    @DELETE("laptop/{idLaptop}")
    Call<Laptop> eliminarLaptop(@Path("idLaptop")String idLaptop);
    @PUT("laptop/{idLaptop}")
    Call<Laptop> actualizarLaptop(@Path("idLaptop")Laptop laptop);
    @POST("laptop")
    Call<Laptop> registrarLaptop(@Body Laptop laptop);

    //Memoria Ram
    @GET("memoria/{idRegistro}")
    Call<MemoriaRam> recuperarMemoriaRam(@Path("idRegistro")String idRegistro);
    @PUT("memoria/{idRegistro}")
    Call<MemoriaRam> actualizarMemoriaRam(@Path("idRegistro")MemoriaRam memoriaRam);
    @DELETE("memoria/{idRegistro}")
    Call<MemoriaRam> eliminarMemoriaRam(@Path("idRegistro")String idRegistro);
    @POST("memoria")
    Call<MemoriaRam> registrarMemoriaRam(@Body MemoriaRam memoriaRam);

    //Catalogo
    @GET("catalogos")
    Call<Catalogo[]> recuperarCatalogos();
    @GET("catalogo/{idRegistro}")
    Call<Catalogo> recuperarCatalogo(@Path("idRegistro")String idRegistro);
    @DELETE("catalogo/{idRegistro}")
    Call<Catalogo> eliminarCatalogo(@Path("idRegustro") String idRegistro);


    //Procesador
    @GET("procesador/{idRegistro}")
    Call<Procesador> recuperarProcesador(@Path("idRegistro")String idRegistro);
    @POST("procesador")
    Call<Procesador> registrarProcesador(@Body Procesador procesador);


    //TarjetaVideo
    @GET("tarjetaDeVideo/{idRegistro}")
    Call<TarjetaVideo> recuperarTarjetaVideo(@Path("idRegistro")String idRegistro);
    @POST("tarjetaDeVideo/")
    Call<TarjetaVideo> registrarTarjetaVideo(@Body TarjetaVideo tarjetaVideo);

    //Pantalla
    @GET("pantalla/{idRegistro}")
    Call<Pantalla> recuperarPantalla(@Path("idRegistro") String idRegistro);
    @POST("pantalla")
    Call<Pantalla> registrarPantalla(@Body Pantalla pantalla);

    //Almacenamiento
    @GET("almacenamiento/{idRegistro}")
    Call<Almacenamiento> recuperarALmacenamiento(@Path("idRegistro")String idRegistro);
    @POST("almacenamiento")
    Call<Almacenamiento> registrarAlmacenamiento(@Body Almacenamiento almacenamiento);

    //HDD
    @GET("hdd/{idRegistro}")
    Call<HDD> recuperarHDD(@Path("idRegistro")String idRegistro);
    @POST
    Call<HDD> registrarHDD(@Body HDD hdd);

    //SSD
    @GET("ssd/{idRegistro}")
    Call<SSD> recuperarSSD(@Path("idRegistro")String idRegistro);
    @POST("ssd")
    Call<SSD> registrarSSD(@Body SSD sdd);


    //Usuarios

    @GET("usuario/{nombreUsuario}")
    Call<Usuario> getUsuario(@Path("nombreUsuario")String nombreUsuario);
    @POST("usuario")
    Call<Usuario> registrarUsuario(@Body Usuario usuario);
    @Headers({"Content-Type: application/json"})
    @PUT("usuario/{nombreUsuario}")
    Call<ResponseBody> actualizarUsuario(@Path("nombreUsuario")String nombreUsuario, @Body Usuario usuario);
    @Multipart
    @PUT("usuario/{nombreUsu}")
    Call<Usuario> actualizarUsuario2(@Part("nombreUsu") String nombreUsuario, @Part("nombreUsuario")
            String usuario,@Part("nombre")String nombre, @Part("apellido") String apellido, @Part("correoElectronico") String correo
            , @Part("contrasena") String contrasena, @Part("administrador") int administrador);
    @DELETE("usuario/{nombreUsuario}")
    Call<Usuario> eliminarUsuario(@Path("nombreUsuario")String nombreUsuario);
}
