package ua.test.task.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.annimon.stream.Stream;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import ua.test.task.R;
import ua.test.task.service.CategoryService;
import ua.test.task.service.JokeService;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String dtStart = "2020-01-05 13:42:26.766831";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SS");
        try {
            TextView dateTV = findViewById(R.id.date);
            Date date = format.parse(dtStart);
            dateTV.setText(date.toString());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        new CategoryService(this).getAllCategories();

    }
}