package com.example.lms_kmj.drawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import com.example.lms_kmj.R;
import com.example.lms_kmj.tt_recv.TTAdapter;
import com.example.lms_kmj.tt_recv.TT_DTO;

import java.util.ArrayList;

public class AcCalendarActivity extends AppCompatActivity {
    Toolbar top_toolbar;
    RecyclerView tt_recv_list;
    CalendarView calendarView;
    ArrayList<TT_DTO> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac_calendar);

        // 상단바
        top_toolbar = findViewById(R.id.top_toolbar);
        top_toolbar.setTitle("학원 일정");

        // 상단바 뒤로가기 버튼
        top_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 캘린더
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("로그", "year: " + year);
                Log.d("로그", "month: " + (month+1));
                Log.d("로그", "dayOfMonth: " + dayOfMonth);
            }
        });

        // 일정들
        tt_recv_list = findViewById(R.id.tt_recv_list);
        list = new ArrayList<>();
        list.add(new TT_DTO("1월 6일","3:00 ~ 5:02","일정 회의가 있습니다."));
        tt_recv_list.setAdapter(new TTAdapter(getLayoutInflater(), list));
        tt_recv_list.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }
}