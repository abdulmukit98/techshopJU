package com.example.mywishlist.retrofit;

import com.example.mywishlist.modal.List_Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MyApi {

    @GET("wishlist.php")
    Call<List<List_Data>> geData();

}