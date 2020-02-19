package com.example.retrofitmovieexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;

import com.example.retrofitmovieexample.R;
import com.example.retrofitmovieexample.model.Movie;
import com.example.retrofitmovieexample.model.MovieDbResponse;
import com.example.retrofitmovieexample.service.MovieDataService;
import com.example.retrofitmovieexample.service.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> mMovies;
    private RecyclerView mRecyclerView;

    public MovieAdapter mMovieAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setTitle("TMDB Popular movies Today");

        getpopularMovies();
    }

    private void getpopularMovies() {

        MovieDataService movieDataService = RetrofitInstance.getService();

        Call<MovieDbResponse> call = movieDataService.getPopularMovies(getResources().getString(R.string.api_key));

        call.enqueue(new Callback<MovieDbResponse>() {
            @Override
            public void onResponse(Call<MovieDbResponse> call, Response<MovieDbResponse> response) {
                 MovieDbResponse movieDbResponse = response.body();

                 if (movieDbResponse != null && movieDbResponse.getResults() != null)
                 {
                     mMovies = (ArrayList<Movie>) movieDbResponse.getResults();

                     showRecyclerView();
                 }

            }

            @Override
            public void onFailure(Call<MovieDbResponse> call, Throwable t) {

            }
        });
    }

    private void showRecyclerView() {

        mRecyclerView = findViewById(R.id.movie_recyclerView);
        mMovieAdapter = new MovieAdapter(this,mMovies);


        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        }

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMovieAdapter);
        mMovieAdapter.notifyDataSetChanged();

    }
}
