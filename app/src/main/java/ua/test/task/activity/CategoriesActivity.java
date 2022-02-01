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
        getCategories();
        setCategories();
    }

    private void init() {
        categoriesRV = findViewById(R.id.categoriesRV);
    }

    private void getCategories() {
        items = getIntent().getStringArrayListExtra("categories");
        adapter = new CategoriesAdapter(items, this);
    }

    private synchronized void setCategories() {
        categoriesRV.setAdapter(adapter);
        categoriesRV.setHasFixedSize(true);
        categoriesRV.setLayoutManager(new LinearLayoutManager(this));
    }
}