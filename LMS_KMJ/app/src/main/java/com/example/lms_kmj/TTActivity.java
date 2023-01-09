package com.example.lms_kmj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.conn.CommonMethod;
import com.example.lms_kmj.common.Common;
import com.example.lms_kmj.lecture.LectureVO;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class TTActivity extends AppCompatActivity {
    Toolbar top_toolbar;
    ArrayList<LinearLayout> linearLayouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt);

        // 상단바
        top_toolbar = findViewById(R.id.top_toolbar);
        linearLayouts.add(findViewById(R.id.ln_layout1));
        linearLayouts.add(findViewById(R.id.ln_layout2));
        linearLayouts.add(findViewById(R.id.ln_layout3));
        linearLayouts.add(findViewById(R.id.ln_layout4));
        linearLayouts.add(findViewById(R.id.ln_layout5));
        linearLayouts.add(findViewById(R.id.ln_layout6));
        linearLayouts.add(findViewById(R.id.ln_layout7));

        top_toolbar.setTitle(LoginInfo.check_id+"의 시간표");

        // 상단바 뒤로가기 버튼
        top_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Common common = new Common();

        top_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.top_toolbar_more) {
                    new CommonMethod().setParams("teacher_code",common.getLoginInfo().getMember_code())
                            .sendPost("ttlist.mj", new CommonMethod.CallBackResult() {
                                @Override
                                public void result(boolean isResult, String data) {
                                    ArrayList<LectureVO> list = new Gson().fromJson(data , new TypeToken<ArrayList<LectureVO>>(){}.getType());
                                    for (int i = 0 ; i < list.size() ; i++){
                                        int index = 0 ;
                                        if(list.get(i).getvDay().equals("월")){
                                            index= 1;
                                        }else if(list.get(i).getvDay().equals("화")){
                                            index= 2;
                                        }else if(list.get(i).getvDay().equals("수")){
                                            index= 3;
                                        }else if(list.get(i).getvDay().equals("목")){
                                            index= 4;
                                        }else if(list.get(i).getvDay().equals("금")){
                                            index= 5;
                                        }else{
                                            index= 6;  // 여기 타면 오류임
                                        }
                                        linearLayouts.get(index).addView(
                                                getTextView(list.get(i).getLecture_name()+list.get(i).getTimetable_code())
                                        );
                                    }
                                }
                            });
                    return true;
                }
                return false;
            }
        });
    }//onCreate()       // id: teacher1  , pw: 000aA

    TextView getTextView(String data){
        TextView temp_tv = new TextView(this);
        temp_tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        temp_tv.setText(data);
        return temp_tv;
    }//getTextView()
}