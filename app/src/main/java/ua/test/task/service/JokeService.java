package ua.test.task.service;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.test.task.activity.JokesActivity;
import ua.test.task.api.Api;
import ua.test.task.model.Joke;
import ua.test.task.util.Props;

public class JokeService {
    private final Context context;
    private final String category;
    private final List<Joke> jokes;

    public JokeService(Context context, String category) {
        this.context = context;
        this.category = category;
        this.jokes = new ArrayList<>();
    }

    public void getRandomJokes(int count) {
        Api api = Props.getRetrofit().create(Api.class);
        Call<Joke> call;
        ArrayList<Joke> jokes = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            call = api.getRandomJoke(category);
            call.enqueue(new Callback<Joke>() {
                @Override
                public void onResponse(Call<Joke> call, Response<Joke> response) {
                    Joke joke = response.body();
                    jokes.add(joke);

                    if (jokes.size() == count) {
                        String jsonJokes = new Gson().toJson(jokes);
                        Intent intent = new Intent(context, JokesActivity.class);
                        intent.putExtra("jokes", jsonJokes);
                        context.startActivity(intent);
                    }
                }

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onFailure(Call<Joke> call, Throwable t) {
                    Toast.makeText(context, Optional.ofNullable(t.getMessage())
                            .orElse("Request failed"), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
