package com.manuel.retrofitnjson2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Noah on 28/04/2018.
 */

public interface ApiCategoria {
    @GET("4A/webresources/categorias")
    Call<List<Categoria>> getCategorias();

    @GET("4A/webresources/categorias/{id}")
    Call<List<Produto>> getProdutos(@Path("id") int id);
}
