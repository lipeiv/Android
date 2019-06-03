package com.example.lipeipei.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
public class Battery_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);            //去除标题栏；
        setContentView(R.layout.activity_battery_);


      Toast toast = Toast.makeText(this,"永不休眠已打开",Toast.LENGTH_SHORT);
      toast.setGravity(0,0,560);
      toast.show();


    }
}
