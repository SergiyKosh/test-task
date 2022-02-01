package ua.test.task.service;

import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Joke> getRandomJokes(int count) {
        Api api = Props.getRetrofit().create(Api.class);
        int i = 0;
        while (i < count) {
            try {
                jokes.add(api.getRandomJoke(category).execute().body());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
        return jokes;
    }
}
