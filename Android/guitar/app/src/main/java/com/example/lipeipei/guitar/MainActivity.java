package com.example.lipeipei.guitar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    Button button1 =(Button)findViewById(R.id.button1);
    Button button2 =(Button)findViewById(R.id.button2);

    button1.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         Intent intent = new Intent(MainActivity.this,harmActivity.class);
         startActivity(intent);
       }
    });


    button2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this,harm2Activity.class);
            startActivity(intent);
        }
    });

     }
}
