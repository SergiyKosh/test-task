package ua.test.task.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.annimon.stream.Optional;

import java.util.ArrayList;
import java.util.List;

import ua.test.task.R;
import ua.test.task.adapter.JokesAdapter;
import ua.test.task.model.Joke;
import ua.test.task.service.JokeService;

public class JokesActivity extends AppCompatActivity {
    private List<Joke> jokes;
    private RecyclerView jokesRV;
    private RecyclerView.Adapter adapter;
    private String category;

//    public JokesActivity(String category) {
//        this.category = category;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        init();
        try {
            getJokes();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        setJokes();
    }

    private void init() {
        jokesRV = findViewById(R.id.jokesRV);
        category = getIntent().getStringExtra("category");
    }

    private void getJokes() throws InterruptedException {
        new Thread(() -> {
            jokes = new JokeService(this, category).getRandomJokes(15);
            adapter = new JokesAdapter(Optional.ofNullable(jokes).orElse(new ArrayList<>()));
        }).start();
        Thread.sleep(500);
    }
    private void setJokes() {
        jokesRV.setHasFixedSize(true);
        jokesRV.setLayoutManager(new LinearLayoutManager(this));
        jokesRV.setAdapter(adapter);
    }

}