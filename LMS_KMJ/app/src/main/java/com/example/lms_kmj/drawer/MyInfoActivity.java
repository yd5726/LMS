package com.example.lms_kmj.drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.lms_kmj.R;

public class MyInfoActivity extends AppCompatActivity {
    Toolbar top_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        // 상단바
        top_toolbar = findViewById(R.id.top_toolbar);
        top_toolbar.setTitle("");

        // 상단바 뒤로가기 버튼
        top_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }//onCreate()
}