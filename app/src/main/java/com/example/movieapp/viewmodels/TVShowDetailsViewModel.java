package com.example.movieapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.movieapp.dao.TVShowDao;
import com.example.movieapp.database.TVShowDatabase;
import com.example.movieapp.models.TvShow;
import com.example.movieapp.repositories.TVShowDetailsRepository;
import com.example.movieapp.responses.TVShowDetailsResponse;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TVShowDetailsViewModel  extends AndroidViewModel {

    private TVShowDetailsRepository tvShowDetailsRepository;
    private TVShowDatabase tvShowDatabase;

    public TVShowDetailsViewModel(@NonNull Application application) {

        super(application);

        tvShowDetailsRepository = new TVShowDetailsRepository();
        tvShowDatabase = TVShowDatabase.getTvShowDatabase(application);
    }

    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId){

        return tvShowDetailsRepository.getTVShowDetails(tvShowId);
    }
    public Completable addToWatchlist(TvShow tvShow) {
        return tvShowDatabase.tvShowDao().addToWatchlist(tvShow);
    }
    public Flowable<TvShow> getTVShowFromWatchlist(String tvShowId){
        return tvShowDatabase.tvShowDao().getTVShowFromWatchlist(tvShowId);

    }
    public Completable removeTVShowFromWatchlist(TvShow tvShow){
        return tvShowDatabase.tvShowDao().removeFromWatchlist(tvShow);

    }


}
