package com.example.retrofitmovieexample.service;

import com.example.retrofitmovieexample.model.MovieDbResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDataService {


    @GET("movie/popular")
    Call<MovieDbResponse> getPopularMovies(@Query("api_key") String api_key);


}
