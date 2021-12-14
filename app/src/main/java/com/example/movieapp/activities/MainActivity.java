package com.example.movieapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.movieapp.R;
import com.example.movieapp.adapters.TVShowsAdapter;
import com.example.movieapp.databinding.ActivityMainBinding;
import com.example.movieapp.listeners.TVShowsListener;
import com.example.movieapp.models.TvShow;

import com.example.movieapp.viewmodels.MostPopularTVShowsViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements TVShowsListener {

    private ActivityMainBinding  activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TvShow > tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        doIntialization();


    }

    private void doIntialization() {
        activityMainBinding.tvshowRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows,this);
        activityMainBinding.tvshowRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvshowRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!activityMainBinding.tvshowRecyclerView.canScrollVertically(1)){
                    if(currentPage <= totalAvailablePages){
                        currentPage += 1;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imageWatchlist.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),WatchlistActivity.class)));
        activityMainBinding.imageSearch.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), SearchActivity.class)));
        getMostPopularTVShows();
    }

    private void getMostPopularTVShows(){

        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this,mostPopularTVShowsResponse ->{

            toggleLoading();


            if(mostPopularTVShowsResponse != null) {
                totalAvailablePages = mostPopularTVShowsResponse.getTotalPages();
                if(mostPopularTVShowsResponse.getTvShows()  != null) {
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                    tvShowsAdapter.notifyItemRangeInserted(oldCount,  tvShows.size());
                }
            }
                }

        );
    }
    private void toggleLoading(){
        if(currentPage==1 ){

            if(activityMainBinding.getIsLoading() != null  && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);

            }

                else{

                    activityMainBinding.setIsLoading(true);

                }
            }else{
            if(activityMainBinding.getIsLoadingMore() !=null && activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoadingMore(false);

            }else{
                activityMainBinding.setIsLoadingMore(true);
            }

          }
        }

    @Override
    public void onTVShowClicked(TvShow tvShow) {


        Intent intent = new Intent(getApplicationContext(), TVShowDetailsActivity.class);
        intent.putExtra("tvShow",tvShow);

        startActivity(intent);
    }
}

