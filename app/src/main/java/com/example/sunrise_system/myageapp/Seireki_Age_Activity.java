package com.example.sunrise_system.myageapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class Seireki_Age_Activity extends Activity implements View.OnClickListener {

    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seireki__age);


        button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button7);
        button3.setOnClickListener(this);

        TextView txt = (TextView)findViewById(R.id.textView16);
        txt.setTextColor(Color.BLUE);

        int data[] = new int[12];
        //data[0] 西暦年
        //data[1] 入力月
        //data[2] 入力日
        //data[3] 和暦元号
        //data[4] 和暦年
        //data[5] 閏年sw
        //data[6] 西暦和暦区分
        //data[7] 和暦年エラー
        //data[8] 西暦年エラー
        //data[9] 未来エラー
        //data[10] 入力月エラー
        //data[11] 入力日エラー

        //年齢計算メソッドを呼び出す
        keisan(data);
    }

    //年齢計算処理
    public void keisan(int data[]) {

        Intent intent = getIntent();

        data[0] = intent.getIntExtra("YEAR", 0);
        data[1] = intent.getIntExtra("MONTH",0);
        data[2] = intent.getIntExtra("DAY", 0);

        Date now = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH) + 1;
        int day = cl.get(Calendar.DAY_OF_MONTH);

        int age = year - data[0];

        if (month < data[1]) {
            age--;
        } else {
            if (month == data[1] && day < data[2]) {
                age--;
            }
        }


        //計算結果表示
        TextView textyear = (TextView) findViewById(R.id.textView18);
        textyear.setText(String.valueOf(data[0]));
        TextView textmonth = (TextView) findViewById(R.id.textView19);
        textmonth.setText(String.valueOf(data[1]));
        TextView textday = (TextView) findViewById(R.id.textView21);
        textday.setText(String.valueOf(data[2]));
        TextView textage = (TextView) findViewById(R.id.textView15);
        textage.setText(String.valueOf(age));
    }


    //最初に戻る・アプリを終了する
    public void onClick(View v) {
        if (v == button2) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 0);
        } else {
            if (v == button3) {
                moveTaskToBack(true);

            }
        }
    }
}
