package com.kym.layoutparam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<LinearLayout> linearLayouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃 7개를 ArrayList 에 넣는다.
        linearLayouts.add(findViewById(R.id.ln_layout1));
        linearLayouts.add(findViewById(R.id.ln_layout2));
        linearLayouts.add(findViewById(R.id.ln_layout3));
        linearLayouts.add(findViewById(R.id.ln_layout4));
        linearLayouts.add(findViewById(R.id.ln_layout5));
        linearLayouts.add(findViewById(R.id.ln_layout6));
        linearLayouts.add(findViewById(R.id.ln_layout7));

        // addView(View child, ViewGroup.LayoutParams params)
        // addView() : ViewGroup 클래스에 정의된 메서드로 부모 뷰그룹에 자식뷰를 추가한다.
        // 첫번째 매개변수로 추가할 자식뷰를, 두번째매개변수로 레이아웃 파라미터 객체를 넣어준다.
        for (int i = 0;i<linearLayouts.size(); i++) {
            //linearLayouts.get(i).addView(getTextView());    // 요일
            for (int j = 0;j< 7; j++) {
                linearLayouts.get(j).addView(getTextView2());   // test2
            }
        }
    }

    TextView getTextView(){
        TextView temp_tv = new TextView(this);
        temp_tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        temp_tv.setText("요일");
        return temp_tv;
    }

    TextView getTextView2(){
        TextView temp_tv = new TextView(this);
        temp_tv.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT));
        temp_tv.setText("test2");
        return temp_tv;
    }
}