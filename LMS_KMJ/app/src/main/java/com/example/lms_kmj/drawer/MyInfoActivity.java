package com.example.lms_kmj.drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conn.CommonMethod;
import com.example.lms_kmj.JoinActivity;
import com.example.lms_kmj.LoginActivity;
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
    TextView member_code_data,id_data,pw_data
            ,member_name_data_tv,gender_data_tv,email_data_tv,birth_data_tv,phone_data_tv
            ,type_data,modify_btn,cancel_btn,confirm_btn;
    EditText member_name_data_et,gender_data_et,email_data_et,birth_data_et,phone_data_et;
    LinearLayout modify_ln1,modify_ln2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        top_toolbar = findViewById(R.id.top_toolbar);
        member_code_data = findViewById(R.id.member_code_data);
        id_data = findViewById(R.id.id_data);
        pw_data = findViewById(R.id.pw_data);

        member_name_data_tv = findViewById(R.id.member_name_data_tv);
        member_name_data_et = findViewById(R.id.member_name_data_et);

        gender_data_tv = findViewById(R.id.gender_data_tv);
        gender_data_et = findViewById(R.id.gender_data_et);

        email_data_tv = findViewById(R.id.email_data_tv);
        email_data_et = findViewById(R.id.email_data_et);

        birth_data_tv = findViewById(R.id.birth_data_tv);
        birth_data_et = findViewById(R.id.birth_data_et);

        phone_data_tv = findViewById(R.id.phone_data_tv);
        phone_data_et = findViewById(R.id.phone_data_et);


        type_data = findViewById(R.id.type_data);
        modify_btn = findViewById(R.id.modify_btn);
        cancel_btn = findViewById(R.id.cancel_btn);
        confirm_btn = findViewById(R.id.confirm_btn);

        modify_ln1 = findViewById(R.id.modify_ln1);
        modify_ln2 = findViewById(R.id.modify_ln2);

        // 상단바
        top_toolbar.setTitle("나의 정보");

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

                        member_name_data_tv.setText(my_info.getMember_name());
                        gender_data_tv.setText(my_info.getGender());
                        email_data_tv.setText(my_info.getEmail());

                        Log.d("로그", "my_info.getBirth(): "+my_info.getBirth());
                        String bitrh_str = my_info.getBirth();
                        birth_data_tv.setText(bitrh_str.substring(0,10));

                        phone_data_tv.setText(my_info.getPhone());
                        type_data.setText(my_info.getType());
                    }
                });

        // 수정 버튼
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify_ln1.setVisibility(View.GONE);
                modify_ln2.setVisibility(View.VISIBLE);

                // 회원 이름 정보
                member_name_data_tv.setVisibility(View.GONE);
                member_name_data_et.setVisibility(View.VISIBLE);
                member_name_data_et.setText(member_name_data_tv.getText());
                member_name_data_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            member_name_data_et.setText("");
                            member_name_data_et.setHint(member_name_data_tv.getText());
                        }
                    }
                });

                // 회원 성별 정보
                gender_data_tv.setVisibility(View.GONE);
                gender_data_et.setVisibility(View.VISIBLE);
                gender_data_et.setText(gender_data_tv.getText());
                gender_data_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            gender_data_et.setText("");
                            gender_data_et.setHint(gender_data_tv.getText());
                        }
                    }
                });

                // 회원 이메일 정보
                email_data_tv.setVisibility(View.GONE);
                email_data_et.setVisibility(View.VISIBLE);
                email_data_et.setText(email_data_tv.getText());
                email_data_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            email_data_et.setText("");
                            email_data_et.setHint(email_data_tv.getText());
                        }
                    }
                });

                // 회원 생일 정보
                birth_data_tv.setVisibility(View.GONE);
                birth_data_et.setVisibility(View.VISIBLE);
                birth_data_et.setText(birth_data_tv.getText());
                birth_data_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            birth_data_et.setText("");
                            birth_data_et.setHint(birth_data_tv.getText());
                        }
                    }
                });

                // 회원 전화번호 정보
                phone_data_tv.setVisibility(View.GONE);
                phone_data_et.setVisibility(View.VISIBLE);
                phone_data_et.setText(phone_data_tv.getText());
                phone_data_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if(hasFocus){
                            phone_data_et.setText("");
                            phone_data_et.setHint(phone_data_tv.getText());
                        }
                    }
                });
            }
        });

        // 취소 버튼
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 레이아웃 교체
                modify_ln1.setVisibility(View.VISIBLE);
                modify_ln2.setVisibility(View.GONE);

                // 회원 이름 정보
                member_name_data_tv.setVisibility(View.VISIBLE);
                member_name_data_et.setVisibility(View.GONE);

                // 회원 성별 정보
                gender_data_tv.setVisibility(View.VISIBLE);
                gender_data_et.setVisibility(View.GONE);

                // 회원 이메일 정보
                email_data_tv.setVisibility(View.VISIBLE);
                email_data_et.setVisibility(View.GONE);

                // 회원 생일 정보
                birth_data_tv.setVisibility(View.VISIBLE);
                birth_data_et.setVisibility(View.GONE);

                // 회원 전화번호 정보
                phone_data_tv.setVisibility(View.VISIBLE);
                phone_data_et.setVisibility(View.GONE);
            }
        });

        // 저장 버튼
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberVO vo = new MemberVO();
                vo.setId(common.getLoginInfo().getId());
                vo.setMember_name(member_name_data_et.getText().toString());
                vo.setGender(gender_data_et.getText().toString());
                vo.setEmail(email_data_et.getText().toString());
                vo.setBirth(birth_data_et.getText().toString());
                vo.setPhone(phone_data_et.getText().toString());

                new CommonMethod().setParams("member", new Gson().toJson(vo))
                    .sendPost("modify_my_info.mj", new CommonMethod.CallBackResult() {
                        @Override
                        public void result(boolean isResult, String data) {
                            Toast.makeText(MyInfoActivity.this, "회원정보수정 완료! 다시 로그인해주세요.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MyInfoActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            }
        });
    }//onCreate()
}