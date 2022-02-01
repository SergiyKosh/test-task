package ua.test.task.service;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.annimon.stream.Optional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.test.task.activity.CategoriesActivity;
import ua.test.task.api.Api;
import ua.test.task.util.Props;

public class CategoryService {
    private final Context context;

    public CategoryService(Context context) {
        this.context = context;
    }

    public void getAllCategories() {
        Api api = Props.getRetrofit().create(Api.class);
        Call<List<String>> categoriesCall = api.getAllCategories();

        categoriesCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                List<String> categories = response.body();
                Intent intent = new Intent(context, CategoriesActivity.class);
                intent.putStringArrayListExtra("categories", (ArrayList<String>) categories);
                context.startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(context, Optional.ofNullable(t.getMessage())
                                .orElse("Request was failed"), Toast.LENGTH_LONG).show();
            }
        });
    }
}
