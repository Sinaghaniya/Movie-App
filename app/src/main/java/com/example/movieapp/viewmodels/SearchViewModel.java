package com.example.movieapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.movieapp.repositories.SearchTVShowRepository;
import com.example.movieapp.responses.TVShowsResponse;

public class SearchViewModel extends ViewModel {

    private SearchTVShowRepository searchTVShowRepository;

    public SearchViewModel(){
        searchTVShowRepository = new SearchTVShowRepository();

    }

    public LiveData<TVShowsResponse> searchTVShow(String query,int page){
        return searchTVShowRepository.searchTVShow(query, page);

    }

}
