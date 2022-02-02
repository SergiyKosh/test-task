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

//        new CategoryService(this).getAllCategories();
        Intent intent = new Intent(this, WebViewActivity.class);
        startActivity(intent);
    }
}