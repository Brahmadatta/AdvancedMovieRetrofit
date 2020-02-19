package com.example.retrofitmovieexample.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.retrofitmovieexample.R;
import com.example.retrofitmovieexample.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder>{

    private Context mContext;

    private ArrayList<Movie> mMovieArrayList;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        mContext = context;
        mMovieArrayList = movieArrayList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.title.setText(mMovieArrayList.get(position).getTitle());

        holder.rating.setText(Double.toString(mMovieArrayList.get(position).getVoteAverage()));

        String image_path = "https://image.tmdb.org/t/p/w500" + mMovieArrayList.get(position).getPosterPath();

        Glide.with(mContext).load(image_path).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mMovieArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder
    {

        TextView title,rating;
        ImageView image;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tvTitle);
            rating = itemView.findViewById(R.id.tvRating);
            image  = itemView.findViewById(R.id.ivMovie);
        }
    }
}
