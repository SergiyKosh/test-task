package ua.test.task.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.test.task.R;
import ua.test.task.model.Joke;

public class JokesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Joke> jokes;

    public JokesAdapter(List<Joke> jokes) {
        this.jokes = jokes;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecyclerView.ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.one_category_view, parent, false)
        ) {};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView jokeText = holder.itemView.findViewById(R.id.jokeText);
        jokeText.setText(jokes.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return jokes.size();
    }
}
