package ua.test.task.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Arrays;
import java.util.List;

import ua.test.task.R;
import ua.test.task.adapter.JokesAdapter;
import ua.test.task.model.Joke;

public class JokesActivity extends AppCompatActivity {
    private List<Joke> jokes;
    private RecyclerView jokesRV;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> adapter;
    private String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        init();
        getJokes();
        setJokes();
    }

    private void init() {
        jokesRV = findViewById(R.id.jokesRV);
        category = getIntent().getStringExtra("category");
    }

    private void getJokes() {
        jokes = new Gson().fromJson(getIntent().getStringExtra("jokes"),
                new TypeToken<List<Joke>>() {
                }.getType());
        adapter = new JokesAdapter(jokes);
    }

    private void setJokes() {
        jokesRV.setHasFixedSize(true);
        jokesRV.setLayoutManager(new LinearLayoutManager(this));
        jokesRV.setAdapter(adapter);
    }

}