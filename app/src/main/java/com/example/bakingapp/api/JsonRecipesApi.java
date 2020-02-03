package com.example.bakingapp.api;

import com.example.bakingapp.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonRecipesApi {

    @GET("baking.json")
    Call<List<Recipe>> getRecipes();

}
