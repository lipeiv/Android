package com.example.lipeipei.test;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.Toast;

public class basicActivity extends AppCompatActivity {

    Button bt1;
    Button bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);



        }

        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event){

        Button bt2 = findViewById(R.id.button2);

        if(keyCode== KeyEvent.KEYCODE_VOLUME_UP) {
            Toast.makeText(this,"音量增", Toast.LENGTH_SHORT).show();
            return true;
        }
        if(keyCode== KeyEvent.KEYCODE_VOLUME_DOWN) {
            Toast.makeText(this,"音量减", Toast.LENGTH_SHORT).show();
            bt2.setTextColor(Color.GREEN);
            return true;
            }
        if(keyCode== KeyEvent.KEYCODE_POWER) {
            Toast.makeText(this,"开关键", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onKeyDown(keyCode,event);
        }
}


