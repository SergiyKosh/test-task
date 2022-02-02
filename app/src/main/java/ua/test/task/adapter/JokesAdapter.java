package ua.test.task.adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.atomic.AtomicReference;

import ua.test.task.R;
import ua.test.task.model.Joke;

public class JokesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Joke> jokes;
    private TextView jokeText;
    private TextView idTV;
    private TextView catTV;
    private TextView urlTV;
    private TextView crTV;
    private TextView updTV;
    private ImageView imgIV;


    public JokesAdapter(List<Joke> jokes) {
        this.jokes = jokes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.one_joke_view, parent, false)
        ) {
        };
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        init(holder);
        try {
            setJokes(position);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void init(@NonNull RecyclerView.ViewHolder holder) {
        jokeText = holder.itemView.findViewById(R.id.jokeValue);
        idTV = holder.itemView.findViewById(R.id.idTV);
        catTV = holder.itemView.findViewById(R.id.catTV);
        urlTV = holder.itemView.findViewById(R.id.urlTV);
        crTV = holder.itemView.findViewById(R.id.crTV);
        updTV = holder.itemView.findViewById(R.id.updTV);
        imgIV = holder.itemView.findViewById(R.id.imgIV);
    }

    @SuppressLint("SetTextI18n")
    private void setJokes(int position) throws InterruptedException {
        jokeText.setText(jokes.get(position).getValue());
        idTV.setText("id: " + jokes.get(position).getId());
        catTV.setText("categories: " + Arrays.toString(jokes.get(position).getCategories())
                .replaceAll("\\[", "")
                .replaceAll("]", "")
        );
        urlTV.setText("url: " + jokes.get(position).getUrl());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        try {
            Date date = format.parse(jokes.get(position).getCreatedAt());
            crTV.setText("created at: " + date.toString());
            date = format.parse(jokes.get(position).getUpdatedAt());
            updTV.setText("updated at: " + date.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Exchanger<Bitmap> exchanger = new Exchanger<>();

        new Thread(() -> {
            try {
                exchanger.exchange(BitmapFactory.decodeStream(new URL(jokes.get(position).getIconUrl()).openConnection().getInputStream()));
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        imgIV.setImageBitmap(exchanger.exchange(null));
    }


    @Override
    public int getItemCount() {
        return jokes.size();
    }
}
