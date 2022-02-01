package ua.test.task.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ua.test.task.api.Api;

public class Props {
    private static final String BASE_URL = "https://api.chucknorris.io/jokes/";
    private static Retrofit RETROFIT;

    static {
        RETROFIT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getRetrofit() {
        return RETROFIT;
    }
}
