package com.example.lipeipei.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
public class Battery_Activity extends AppCompatActivity {

//    public TextView time1 = findViewById(R.id.time1);
//    public TextView battery1=findViewById(R.id.battery1);
//    public TextView voltage1 = findViewById(R.id.voltage1);
//    TextView tmep1 = findViewById(R.id.temp1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);            //去除标题栏；
        setContentView(R.layout.activity_battery);

        Toast toast = Toast.makeText(this,"永不休眠已打开",Toast.LENGTH_SHORT);
        toast.setGravity(0,0,560);
        toast.show();

    }
//
//    class BatteryChangedReceiver extends BroadcastReceiver                      //电池状态监控
//    {
//        @Override
//        public void onReceive(Context arg0, Intent arg1)
//        {
//            int voltage = arg1.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
//            voltage1.setText("电压:" + voltage + "mV ");
//
//            int temperature = arg1.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
//            time1.setText("温度:" + temperature / 10 + "℃");
//
//            int level = arg1.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
//            int scale = arg1.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
//            int levelPercent = (int) (((float) level / scale) * 100);
//            battery1.setText("电量:" + levelPercent + "%");
//
//        }
//    }
}
