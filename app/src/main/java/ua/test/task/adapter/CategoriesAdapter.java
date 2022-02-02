package ua.test.task.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.test.task.R;
import ua.test.task.activity.JokesActivity;
import ua.test.task.service.JokeService;

public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<String> categories;
    private final Context context;

    public CategoriesAdapter(List<String> categories, Context context) {
        this.categories = categories;
        this.context = context;
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
        TextView categoryName = holder.itemView.findViewById(R.id.categoryName);
        categoryName.setText(categories.get(position));

        categoryName.setOnClickListener(click -> {
            new JokeService(context, categories.get(position)).getRandomJokes(15);
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
