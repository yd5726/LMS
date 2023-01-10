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
    Toolbar tt_toolbar;
    ArrayList<LinearLayout> linearLayouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tt);

        tt_toolbar = findViewById(R.id.tt_toolbar);
        linearLayouts.add(findViewById(R.id.ln_layout0));
        linearLayouts.add(findViewById(R.id.ln_layout1));
        linearLayouts.add(findViewById(R.id.ln_layout2));
        linearLayouts.add(findViewById(R.id.ln_layout3));
        linearLayouts.add(findViewById(R.id.ln_layout4));
        linearLayouts.add(findViewById(R.id.ln_layout5));
        linearLayouts.add(findViewById(R.id.ln_layout6));
        linearLayouts.add(findViewById(R.id.ln_layout7));
        linearLayouts.add(findViewById(R.id.ln_layout8));
        linearLayouts.add(findViewById(R.id.ln_layout9));
        linearLayouts.add(findViewById(R.id.ln_layout10));
        linearLayouts.add(findViewById(R.id.ln_layout11));
        linearLayouts.add(findViewById(R.id.ln_layout12));
        linearLayouts.add(findViewById(R.id.ln_layout13));
        linearLayouts.add(findViewById(R.id.ln_layout14));
        linearLayouts.add(findViewById(R.id.ln_layout15));
        linearLayouts.add(findViewById(R.id.ln_layout16));
        linearLayouts.add(findViewById(R.id.ln_layout17));

        Common common = new Common();
        // 상단바
        tt_toolbar.setTitle(common.getLoginInfo().getMember_name()+"의 시간표");

        // 상단바 뒤로가기 버튼
        tt_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        tt_toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.tt_toolbar_reloading) {
                    new CommonMethod().setParams("teacher_code",common.getLoginInfo().getMember_code())
                            .sendPost("ttlist.mj", new CommonMethod.CallBackResult() {
                                @Override
                                public void result(boolean isResult, String data) {
                                    ArrayList<LectureVO> list = new Gson().fromJson(data, new TypeToken<ArrayList<LectureVO>>() {}.getType());
                                    Log.d("로그", "list.size(): "+list.size());   // list.size():15
                                    Log.d("로그", "list: "+list);
                                    Log.d("로그", "getvDay(0): "+list.get(0).getvDay());
                                    Log.d("로그", "getvDay(1): "+list.get(1).getvDay());
                                    Log.d("로그", "getvDay(2): "+list.get(2).getvDay());
                                    Log.d("로그", "getvDay(3): "+list.get(3).getvDay());
                                    Log.d("로그", "getvDay(4): "+list.get(4).getvDay());
                                    Log.d("로그", "getvDay(5): "+list.get(5).getvDay());
                                    for (int i = 0; i < 3; i++) {
                                    //for (int i = 0; i < Integer.parseInt(list.get(i).getTimetable_code()); i++) {
                                        for (int k = 0; k < 6; k++) {
                                            //linearLayouts.get(k).addView(getTextView("("+i+","+k+")"));
                                            /*
                                            int index = 0;
                                            if (list.get(k).getvDay().equals("월") && Integer.parseInt(list.get(k).getTimetable_code()) == i) {
                                                index = ((k+1)*1);
                                                Log.d("로그", "i: "+i+",k: "+k+", index: "+index);
                                            } else if (list.get(k).getvDay().equals("화")) {
                                                index = ((k+1)*2);
                                                Log.d("로그", "i: "+i+",k: "+k+", index: "+index);
                                            } else if (list.get(k).getvDay().equals("수")) {
                                                index = ((k+1)*3);
                                                Log.d("로그", "i: "+i+",k: "+k+", index: "+index);
                                            } else if (list.get(k).getvDay().equals("목")) {
                                                index = ((k+1)*4);
                                                Log.d("로그", "i: "+i+",k: "+k+", index: "+index);
                                            } else if (list.get(k).getvDay().equals("금")) {
                                                index = ((k+1)*5);
                                                Log.d("로그", "i: "+i+",k: "+k+", index: "+index);
                                            } else {
                                                index = 12;  // 여기 타면 오류
                                            }
                                            linearLayouts.get(index).addView(
                                                    getTextView(list.get(index).getLecture_name())
                                            );
                                            */

                                            /*
                                            int index = 0;
                                            if (list.get(i).getvDay().equals("월") && Integer.parseInt(list.get(i).getTimetable_code())==1) {
                                                index = 0;
                                            } else if (list.get(i).getvDay().equals("화")) {
                                                index = 1;
                                            } else if (list.get(i).getvDay().equals("수")) {
                                                index = 2;
                                            } else if (list.get(i).getvDay().equals("목")) {
                                                index = 3;
                                            } else if (list.get(i).getvDay().equals("금")) {
                                                index = 4;
                                            } else {
                                                index = list.size() + 1;  // 여기 타면 오류  //list.size():15
                                            }
                                            linearLayouts.get(index).addView(
                                                    getTextView(list.get(i).getLecture_name())
                                            );
                                            */
                                        }
                                    }
                                }
                            });
                    return true;
                }
                return false;
            }
        });
    }//onCreate()

    TextView getTextView(String data){
        TextView temp_tv = new TextView(this);
        temp_tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        temp_tv.setText(data);
        return temp_tv;
    }//getTextView()
}