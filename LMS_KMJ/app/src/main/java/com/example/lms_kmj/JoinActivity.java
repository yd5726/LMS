package com.example.lms_kmj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.conn.CommonMethod;
import com.example.lms_kmj.member.MemberVO;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class JoinActivity extends AppCompatActivity {
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener setDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDate();
        }
    };
    TextInputEditText id_et, pw_et, name_et, email_et, birth_et, phone_et;
    Toolbar top_toolbar;
    RadioGroup radioGroup1,radioGroup2;
    TextView cancel_btn, confirm_btn, id_ck_tv;
    RadioButton stud_rd, teach_rd, male_rd, female_rd;
    String type_result ="STUD", gender_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 상단바
        top_toolbar = findViewById(R.id.top_toolbar);
        top_toolbar.setTitle("회원가입");

        // 상단바 뒤로가기 버튼
        top_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 회원 정보
        radioGroup1 = findViewById(R.id.radioGroup1);
        stud_rd = findViewById(R.id.stud_rd);
        teach_rd = findViewById(R.id.teach_rd);
        id_et = findViewById(R.id.id_et);
        pw_et = findViewById(R.id.pw_et);
        name_et = findViewById(R.id.name_et);
        radioGroup2 = findViewById(R.id.radioGroup2);
        male_rd = findViewById(R.id.male_rd);
        female_rd = findViewById(R.id.female_rd);
        email_et = findViewById(R.id.email_et);
        birth_et = findViewById(R.id.birth_et);
        phone_et = findViewById(R.id.phone_et);

        // 취소/확인 버튼
        cancel_btn = findViewById(R.id.cancel_btn);
        confirm_btn = findViewById(R.id.confirm_btn);

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.stud_rd){
                    type_result = stud_rd.getText().toString();
                }else if(checkedId == R.id.teach_rd){
                    type_result = teach_rd.getText().toString();
                }
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.male_rd){
                    gender_result = male_rd.getText().toString();
                }else if(checkedId == R.id.female_rd){
                    gender_result = female_rd.getText().toString();
                }
            }
        });

        birth_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(JoinActivity.this,
                        setDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // 취소 버튼
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // 확인 버튼
        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemberVO vo = new MemberVO();
                vo.setType(type_result);
                vo.setId(id_et.getText()+"");
                vo.setPw(pw_et.getText()+"");
                vo.setMember_name(name_et.getText()+"");
                vo.setGender(gender_result);
                vo.setEmail(email_et.getText()+"");
                vo.setBirth(birth_et.getText()+"");
                vo.setPhone(phone_et.getText()+"");
                new CommonMethod().setParams("param", vo).setParams("member", new Gson().toJson(vo)).
                        sendPost("join.mj", new CommonMethod.CallBackResult() {
                        @Override
                        public void result(boolean isResult, String data) {
                            if(data ==null || data.equals("null")){
                                Toast.makeText(JoinActivity.this, "입력 값을 확인해주세요!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(JoinActivity.this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
            }
        });

        // 아이디 중복확인 버튼
        id_ck_tv = findViewById(R.id.id_ck_tv);
        id_ck_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JoinActivity.this, "아이디 중복확인 완료!", Toast.LENGTH_SHORT).show();
            }
        });
    }//onCreate()

    // 날짜 형식 변환
    public void updateDate(){
        String format = "YY/MM/dd";
        SimpleDateFormat simpleDateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
        }
        birth_et.setText(simpleDateFormat.format(myCalendar.getTime()));
    }//updateDate()
}