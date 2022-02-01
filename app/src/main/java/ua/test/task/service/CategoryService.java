package ua.test.task.service;

import com.annimon.stream.Optional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import retrofit2.Call;
import ua.test.task.api.Api;
import ua.test.task.util.Props;

public class CategoryService {
    public List<String> getAllCategories() throws IOException {
        Api api = Props.getRetrofit().create(Api.class);
        Call<List<String>> categoriesCall = api.getAllCategories();
        return Optional.ofNullable(categoriesCall.execute().body()).orElse(new ArrayList<>());
    }
}
