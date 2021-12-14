package com.example.movieapp.listeners;

import com.example.movieapp.models.TvShow;

public interface WatchlistListener {

    void onTVShowClicked(TvShow tvShow);

    void removeTVShowFromWatchlist(TvShow tvShow, int position);
}

