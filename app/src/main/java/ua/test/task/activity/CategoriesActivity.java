package ua.test.task.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

import ua.test.task.R;
import ua.test.task.adapter.CategoriesAdapter;
import ua.test.task.service.CategoryService;

public class CategoriesActivity extends AppCompatActivity {
    private RecyclerView categoriesRV;
    private List<String> items;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        init();
        try {
            getCategories();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setCategories();

    }

    private void init() {
        categoriesRV = findViewById(R.id.categoriesRV);
    }

    private void getCategories() throws InterruptedException {
        new Thread(() -> {
            try {
                items = new CategoryService().getAllCategories();
                adapter = new CategoriesAdapter(items, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        Thread.sleep(500);
    }

    private synchronized void setCategories() {
        categoriesRV.setAdapter(adapter);
        categoriesRV.setHasFixedSize(true);
        categoriesRV.setLayoutManager(new LinearLayoutManager(this));
    }
}