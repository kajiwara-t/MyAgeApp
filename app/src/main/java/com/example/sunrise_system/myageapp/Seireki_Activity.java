package com.example.sunrise_system.myageapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sunrise_system.myageapp.Year_Check_Sub.check1;

public class Seireki_Activity extends Activity implements View.OnClickListener {

    //int data[] = new int[12];
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

    private Button buttonSe;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seireki_);

        buttonSe = (Button) findViewById(R.id.KeisanBt1);
        buttonSe.setOnClickListener(this);

        TextView txt = (TextView)findViewById(R.id.textView3);
        txt.setTextColor(Color.BLUE);

    }

    public void onClick(View view) {

        final int data[] = new int[12];

        final EditText yearText = (EditText) findViewById(R.id.YearText1);
        final EditText monthText = (EditText) findViewById(R.id.MonthText1);
        final EditText dayText = (EditText) findViewById(R.id.DayText1);


        //“計算する”ボタン押下で画面遷移
        if (yearText.getText().toString().equals("") == false && monthText.getText().toString().equals("") == false
                && dayText.getText().toString().equals("") == false) {

            data[0] = Integer.parseInt(yearText.getText().toString());
            data[1] = Integer.parseInt(monthText.getText().toString());
            data[2] = Integer.parseInt(dayText.getText().toString());

            check1(data);
            error(data);

        } else {

            Toast.makeText(getApplicationContext(), "未入力です", Toast.LENGTH_SHORT).show();

        }
    }


    //エラーチェック & 画面遷移
    public void error(int data[]) {
        Intent intent = new Intent(this, Error_Activity.class);
        if (data[8] == 1) {
            intent.putExtra("Year_error", 1);
            startActivity(intent);

        } else if (data[9] == 1) {
            intent.putExtra("Future_error", 1);
            startActivity(intent);

        } else if (data[10] == 1) {
            intent.putExtra("Month_error", 1);
            startActivity(intent);

        } else if (data[11] == 1) {
            intent.putExtra("Day_error", 1);
            startActivity(intent);

        } else {
            //エラーでなければ西暦用の年齢計算結果画面に遷移
            Intent seAge = new Intent(this, Seireki_Age_Activity.class);
            seAge.putExtra("YEAR", data[0]);
            seAge.putExtra("MONTH", data[1]);
            seAge.putExtra("DAY", data[2]);
            startActivity(seAge);
        }
    }
}
