package com.example.lms_kmj;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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
    //MemberVO vo;
    String type_result, gender_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // 상단바
        top_toolbar = findViewById(R.id.top_toolbar);
        top_toolbar.setTitle("회원가입");
        top_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.top_toolbar_more:
                        Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        // 상단바 뒤로가기 버튼
        top_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // 회원 가입에 필요한 정보 담을 객체 생성
        //vo = new MemberVO();

        // 학생 or 강사 선택
        radioGroup1 = findViewById(R.id.radioGroup1);
        stud_rd = findViewById(R.id.stud_rd);
        teach_rd = findViewById(R.id.teach_rd);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.stud_rd){
                    type_result = stud_rd.getText().toString();
                    //vo.setType(stud_rd.getText().toString());
                }else if(checkedId == R.id.teach_rd){
                    type_result = teach_rd.getText().toString();
                    //vo.setType(teach_rd.getText().toString());
                }
            }
        });

        // 아이디 입력
        id_et = findViewById(R.id.id_et);
        //vo.setId(id_et.getText().toString());

        // 비밀번호 입력
        pw_et = findViewById(R.id.pw_et);
        //vo.setPw(pw_et.getText().toString());

        // 이름 입력
        name_et = findViewById(R.id.name_et);
        //vo.setMember_name(name_et.getText().toString());

        // 남 or 여 선택
        radioGroup2 = findViewById(R.id.radioGroup2);
        male_rd = findViewById(R.id.male_rd);
        female_rd = findViewById(R.id.female_rd);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.male_rd){
                    gender_result = male_rd.getText().toString();
                    //vo.setGender(stud_rd.getText().toString());
                }else if(checkedId == R.id.female_rd){
                    gender_result = female_rd.getText().toString();
                    //vo.setGender(teach_rd.getText().toString());
                }
            }
        });

        // 이메일 입력
        email_et = findViewById(R.id.email_et);
        //vo.setEmail(email_et.getText().toString());

        // 생년월일 입력
        birth_et = findViewById(R.id.birth_et);
        birth_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(JoinActivity.this,
                        setDate, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        // 전화번호 입력
        phone_et = findViewById(R.id.phone_et);
        //vo.setPhone(phone_et.getText().toString());

        // 취소 버튼
        cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // 확인 버튼
        confirm_btn = findViewById(R.id.confirm_btn);
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
                vo.setPhone(phone_et.getText()+"");

                new CommonMethod().setParams("param",vo)
                    .sendPost("join.mj", new CommonMethod.CallBackResult() {
                        @Override
                        public void result(boolean isResult, String data) {
                            Log.d("로그", "data:" + data);        // null
                            Log.d("로그", "isResult:" + isResult);    // true
                            Log.d("로그", "vo.getId():" + vo.getId());    //aa
                            /*
                            if(data.equals("null")){
                                Toast.makeText(JoinActivity.this, "입력 값을 확인해주세요!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(JoinActivity.this, "회원가입 완료!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(JoinActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();   // finish 하고 다음 화면으로 이동
                            }
                            */
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
        String format = "YYYY/MM/dd";
        SimpleDateFormat simpleDateFormat = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
        }
        birth_et.setText(simpleDateFormat.format(myCalendar.getTime()));
    }//updateDate()
}