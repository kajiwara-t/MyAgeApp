package com.example.sunrise_system.myageapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.sunrise_system.myageapp.Year_Check_Sub.check2;

public class Wareki_Activity extends Activity implements View.OnClickListener, OnCheckedChangeListener {


    int gen = 0;

    private Button buttonWa;

    private RadioGroup rg1;
    private RadioButton Meiji, Taisho, Shouwa, Heisei;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wareki_);

        buttonWa = (Button) findViewById(R.id.button6);
        buttonWa.setOnClickListener(this);

        rg1 = (RadioGroup) findViewById(R.id.radiogroups1);
        rg1.setOnCheckedChangeListener(this);

        TextView txt = (TextView)findViewById(R.id.textView);
        txt.setTextColor(Color.BLUE);
    }


    //ラジオボタンによる元号選択
    public void onCheckedChanged(RadioGroup group, int buttonId) {

        Meiji = findViewById(R.id.meiji);
        Taisho = findViewById(R.id.taisho);
        Shouwa = findViewById(R.id.shouwa);
        Heisei = findViewById(R.id.heisei);

        if (group == rg1) {
            switch (buttonId) {
                case R.id.meiji:
                    gen = 1;
                    break;

                case R.id.taisho:
                    gen = 2;
                    break;

                case R.id.shouwa:
                    gen = 3;
                    break;

                case R.id.heisei:
                    gen = 4;
                    break;

                default:
                    break;
            }
        }
    }


    public void onClick(View v1) {

        final int data[] = new int[12];

        final EditText editNen = (EditText) findViewById(R.id.editText);
        final EditText editTsuki = (EditText) findViewById(R.id.editText2);
        final EditText editHi = (EditText) findViewById(R.id.editText3);

        //“計算する”ボタン押下で画面遷移
        if (v1 == buttonWa) {

            if ((gen >= 1) && editNen.getText().toString().equals("") == false && editTsuki.getText().toString().equals("") == false
                    && editHi.getText().toString().equals("") == false) {

                data[3] = gen;
                data[4] = Integer.parseInt(editNen.getText().toString());
                data[1] = Integer.parseInt(editTsuki.getText().toString());
                data[2] = Integer.parseInt(editHi.getText().toString());

                check2(data);
                error(data);

            } else {
                Toast.makeText(getApplicationContext(), "未入力です", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //エラーチェック & 画面遷移
    public void error(int data[]) {
        Intent intent = new Intent(this, Error_Activity.class);
        if (data[7] == 1) {
            intent.putExtra("Wareki_error", 1);
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

            //エラーがなかった場合、和暦用の年齢計算結果画面に遷移
        } else {
            Intent waAge = new Intent(this, Wareki_Age_Activity.class);
            waAge.putExtra("year", data[0]);
            waAge.putExtra("Wa_month", data[1]);
            waAge.putExtra("Wa_day", data[2]);
            waAge.putExtra("Gengou", data[3]);
            waAge.putExtra("Wa_year", data[4]);
            startActivity(waAge);
        }
    }
}