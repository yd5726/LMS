package com.example.lms_kmj.drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.conn.CommonMethod;
import com.example.lms_kmj.R;
import com.example.lms_kmj.board.BoardVO;
import com.example.lms_kmj.common.Common;
import com.example.lms_kmj.member.MemberVO;
import com.example.lms_kmj.tt_recv.TTAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class MyInfoActivity extends AppCompatActivity {
    Toolbar top_toolbar;
    TextView member_code_data,id_data,pw_data,member_name_data
            ,gender_data,email_data,birth_data,phone_data,type_data,modify_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        top_toolbar = findViewById(R.id.top_toolbar);
        member_code_data = findViewById(R.id.member_code_data);
        id_data = findViewById(R.id.id_data);
        pw_data = findViewById(R.id.pw_data);
        member_name_data = findViewById(R.id.member_name_data);
        gender_data = findViewById(R.id.gender_data);
        email_data = findViewById(R.id.email_data);
        birth_data = findViewById(R.id.birth_data);
        phone_data = findViewById(R.id.phone_data);
        type_data = findViewById(R.id.type_data);
        modify_btn = findViewById(R.id.modify_btn);

        // 상단바
        top_toolbar.setTitle("");

        // 상단바 뒤로가기 버튼
        top_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Common common = new Common();

        // 회원 정보 불러오기
        new CommonMethod().setParams("id",common.getLoginInfo().getId())
                .sendPost("my_info.mj", new CommonMethod.CallBackResult() {
                    @Override
                    public void result(boolean isResult, String data) {
                        MemberVO my_info = new Gson().fromJson(data, MemberVO.class);
                        member_code_data.setText(my_info.getMember_code());
                        id_data.setText(my_info.getId());
                        pw_data.setText(my_info.getPw());
                        member_name_data.setText(my_info.getMember_name());
                        gender_data.setText(my_info.getGender());
                        email_data.setText(my_info.getEmail());
                        birth_data.setText(my_info.getBirth());     // TODO : 회원 생일 정보 yyyy-MM-dd로 짜르기
                        phone_data.setText(my_info.getPhone());
                        type_data.setText(my_info.getType());
                    }
                });

        // 회원 정보 수정 버튼
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }//onCreate()
}