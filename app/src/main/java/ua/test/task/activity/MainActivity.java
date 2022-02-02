package ua.test.task.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.onesignal.OneSignal;

import ua.test.task.R;
import ua.test.task.service.CategoryService;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar2);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        boolean isAppStartedFromPush;

        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
        OneSignal.initWithContext(this);
        OneSignal.setAppId("0ece53c3-ff06-4f4c-a8b3-b4ffd14052b2");
        OneSignal.setNotificationOpenedHandler(click -> {
            getIntent().putExtra("startedFromPush", true);
        });

        if (getIntent().getExtras() != null) {
            if (!getIntent().getExtras().containsKey("startedFromPush")) {
                isAppStartedFromPush = false;
            } else {
                isAppStartedFromPush = true;
            }
        } else {
            isAppStartedFromPush = false;
        }

        if (!isAppStartedFromPush) {
            new CategoryService(this).getAllCategories();
        } else {
            Intent intent = new Intent(this, WebViewActivity.class);
            startActivity(intent);
        }
        progressBar.setProgress(100);
    }
}