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


public class Wareki_Age_Activity extends Activity implements View.OnClickListener {

    private Button button_re;
    private Button button_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wareki__age);


        button_re = (Button) findViewById(R.id.button8);
        button_re.setOnClickListener(this);
        button_end = (Button) findViewById(R.id.button9);
        button_end.setOnClickListener(this);

        TextView txt = (TextView)findViewById(R.id.textView26);
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

        data[0] = intent.getIntExtra("year",0);
        data[1] = intent.getIntExtra("Wa_month", 0);
        data[2] = intent.getIntExtra("Wa_day", 0);
        data[3] = intent.getIntExtra("Gengou", 0);
        data[4] = intent.getIntExtra("Wa_year", 0);


        Date now = new Date();
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH) + 1;
        int day = cl.get(Calendar.DAY_OF_MONTH);
        int age;

        if (data[1] > month) {
            age = year - data[0] - 1;
        } else if (data[1] == month && data[2] > day) {
            age = year - data[0] - 1;
        } else {
            age = year - data[0];
        }


        //計算結果表示
        switch (data[3]) {
            case 1:
                TextView textMeiji = (TextView) findViewById(R.id.textView27);
                textMeiji.setText("明治");
                break;

            case 2:
                TextView textTaisyo = (TextView) findViewById(R.id.textView27);
                textTaisyo.setText("大正");
                break;

            case 3:
                TextView textSyouwa = (TextView) findViewById(R.id.textView27);
                textSyouwa.setText("昭和");
                break;

            case 4:
                TextView textHeisei = (TextView) findViewById(R.id.textView27);
                textHeisei.setText("平成");
                break;

            default:
                TextView textDefault = (TextView) findViewById(R.id.textView27);
                textDefault.setText("ミス");
                break;
        }

        TextView textyear = (TextView) findViewById(R.id.textView28);
        textyear.setText(String.valueOf(data[4]));
        textyear.setTextColor(Color.BLUE);

        TextView textmonth = (TextView) findViewById(R.id.textView30);
        textmonth.setText(String.valueOf(data[1]));
        textmonth.setTextColor(Color.BLUE);

        TextView textday = (TextView) findViewById(R.id.textView32);
        textday.setText(String.valueOf(data[2]));
        textday.setTextColor(Color.BLUE);

        TextView textage = (TextView) findViewById(R.id.textView36);
        textage.setText(String.valueOf(age));
        textage.setTextColor(Color.BLUE);
    }


    //最初に戻るボタン・アプリ終了ボタン
    public void onClick(View v) {
        if (v == button_re) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 0);
        } else {
            if (v == button_end) {
                moveTaskToBack(true);
            }
        }
    }
}

