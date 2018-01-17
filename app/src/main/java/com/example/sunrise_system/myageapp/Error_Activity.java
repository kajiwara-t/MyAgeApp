package com.example.sunrise_system.myageapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Error_Activity extends Activity implements View.OnClickListener {

    private Button  errorBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_);

        errorBt = (Button) findViewById(R.id.ErrorButton);
        errorBt.setOnClickListener(this);

        Intent intent = getIntent();

        TextView errorText = (TextView) findViewById(R.id.ErrorText);

        //data[7] 和暦年エラー
        //data[8] 西暦年エラー
        //data[9] 未来エラー
        //data[10] 入力月エラー
        //data[11] 入力日エラー

        int gengou = intent.getIntExtra("Wareki_error", 0);
        int seireki = intent.getIntExtra("Year_error", 0);
        int future = intent.getIntExtra("Future_error", 0);
        int month = intent.getIntExtra("Month_error", 0);
        int day = intent.getIntExtra("Day_error", 0);

        if(gengou ==1){
            errorText.setText(String.valueOf("和暦年がエラーです"));

        } else if(seireki == 1){

            errorText.setText(String.valueOf("西暦年がエラーです"));

        } else if(future == 1){
            errorText.setText(String.valueOf("未来の日付です"));

        } else if (month == 1 ){
            errorText.setText(String.valueOf("入力月がエラーです"));

        } else if (day == 1) {
            errorText.setText(String.valueOf("入力日がエラーです"));

        }

    }

    public void onClick(View v) {

        if (v == errorBt) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent, 0);
        }
    }
}
