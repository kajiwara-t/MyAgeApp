package com.example.sunrise_system.myageapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    private Button button_segue1;
    private Button button_segue2;
    private Button button_segue3;


    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        button_segue1 = (Button) findViewById(R.id.button2);
        button_segue1.setOnClickListener(this);
        button_segue2 = (Button) findViewById(R.id.button3);
        button_segue2.setOnClickListener(this);
        button_segue3 = (Button) findViewById(R.id.button4);
        button_segue3.setOnClickListener(this);

        TextView txt = (TextView)findViewById(R.id.textView4);
        txt.setTextColor(Color.BLUE);

    }

    public void onClick(View v) {

        if (v == button_segue1) {
            Intent intent = new Intent(this, Seireki_Activity.class);
            startActivityForResult(intent, 0);
        } else if (v == button_segue2) {
            Intent intent = new Intent(this, Wareki_Activity.class);
            startActivityForResult(intent, 0);
        } else if (v == button_segue3) {
            moveTaskToBack(true);
        }
    }
}
