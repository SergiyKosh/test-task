package ua.test.task.api;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ua.test.task.model.Joke;

public interface Api {
    @GET("categories")
    Call<List<String>> getAllCategories();

    @GET("random")
    Call<Joke> getRandomJoke(@Query("category") String category);
}
