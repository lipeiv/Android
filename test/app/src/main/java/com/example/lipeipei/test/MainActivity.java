package com.example.lipeipei.test;
import android.app.Activity;
import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.hardware.camera2.CameraManager;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity
{
    private Switch aSwitch, bSwitch, cSwitch;                                       //实例化
    private TextView mTvVoltage, mTvTemperature, mTvLevel, mTvStatus, mTvScreen;
    private Vibrator myVibrator;
    private BatteryChangedReceiver receiver = new BatteryChangedReceiver();
    private CameraManager manager;
    private WifiManager mWifiManager;
    BluetoothAdapter bt = BluetoothAdapter.getDefaultAdapter();


    @Override
    protected void onPostResume() {
        // TODO Auto-generated method stub
        super.onPostResume();}


    @Override
    protected void onCreate(Bundle savedInstanceState)                       //onCreate Bundle
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvVoltage = findViewById(R.id.voltage);
        mTvTemperature = findViewById(R.id.temp);
        mTvLevel = findViewById(R.id.level);
        mTvStatus = findViewById(R.id.status);
        mTvScreen = findViewById(R.id.screen);
        aSwitch = findViewById(R.id.switch1);
        bSwitch = findViewById(R.id.switch2);
        cSwitch = findViewById(R.id.switch3);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        registerReceiver(receiver, getFilter());
        myVibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);


        Toast toast = Toast.makeText(MainActivity.this,"永不休眠已打开！",Toast.LENGTH_SHORT);
        toast.setGravity(0,0,1560);
        toast.show();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,basicActivity.class);
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(MainActivity.this,CartonActivity.class);
              startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Battery_Activity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(MainActivity.this,CurrentActivity.class);
             startActivity(intent);
            }
        });

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()   //监听switch1
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                if(isChecked)
                {

                }
                else
                {

                }
            }
        });

        bSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()   //监听switch2
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                if(isChecked)
                {
                    myVibrator.vibrate(new long[]{0,2000,0,2000},0);
                }
                if(!isChecked)
                {
                    myVibrator.cancel();
                }
            }
        });




        cSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()   //监听switch3
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked)
            {
                if(isChecked)
                {                                                           //camera.startPreview();
                    torch_on();
                    openwifi();

                }
                else
                {
                    torch_off();
                    closewifi();
                }
            }
        });


    }
    void openwifi()
    {   bt.enable();

        mWifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiManager.setWifiEnabled(true);
    }

    void closewifi()
    {   bt.disable();

        mWifiManager = (WifiManager) this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiManager.setWifiEnabled(false);
    }


    void torch_on()  {                                                        //打开补光灯
        try {
            manager = (CameraManager) this.getSystemService(Context.CAMERA_SERVICE);
            manager.setTorchMode("0", true);// "0"是主闪光灯
        } catch (Exception e) {}
    }


    void  torch_off() {                                                           //关闭补光灯
        try {
            if (manager == null) {
                return;
            }
            manager.setTorchMode("0", false);
        } catch (Exception e) {}

    }


    private IntentFilter getFilter()                                            //定义电池监控
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_BATTERY_OKAY);
        filter.addAction(Intent.ACTION_MANAGE_NETWORK_USAGE);
        filter.addAction(Intent.ACTION_CALL);
        return filter;
    }
    private void setLight(Activity context, int brightness)                     //定义亮度函数
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        context.getWindow().setAttributes(lp);
    }

    class BatteryChangedReceiver extends BroadcastReceiver                      //电池状态监控
    {
        @Override
        public void onReceive(Context arg0, Intent arg1)
        {
            int voltage = arg1.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
            mTvVoltage.setText("电压:" + voltage + "mV ");

            int temperature = arg1.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
            mTvTemperature.setText("温度:" + temperature / 10 + "℃");


            if(temperature >= 300)
            {
                mTvTemperature.setTextColor(Color.RED);
            } else {
                mTvTemperature.setTextColor(Color.BLUE);
            }
            int level = arg1.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            int scale = arg1.getIntExtra(BatteryManager.EXTRA_SCALE, 0);
            int levelPercent = (int) (((float) level / scale) * 100);
            mTvLevel.setText("电量:" + levelPercent + "%");
            if (level <= 10)
            {
                mTvLevel.setTextColor(Color.RED);
            }
            else
            {
                mTvLevel.setTextColor(Color.BLUE);
            }
            int status = arg1.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
            String strStatus="未知";
            String strScreen="默认";
            switch (status) {
                case BatteryManager.BATTERY_STATUS_CHARGING:
                    strStatus = "充电中…";
                    strScreen ="最暗";
                    setLight(MainActivity.this,10);
                    break;
                case BatteryManager.BATTERY_STATUS_DISCHARGING:
                    strStatus = "放电中…";
                    strScreen = "最亮";
                    setLight(MainActivity.this,255);
                    break;
                case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                    strStatus = "未充电";
                    strScreen = "最亮";
                    setLight(MainActivity.this,255);
                    break;
                case BatteryManager.BATTERY_STATUS_FULL:
                    strStatus = "充电完成";
                    break;
            }
            mTvStatus.setText("状态:" + strStatus);
            mTvScreen.setText("屏幕:"+strScreen);
        }
    }


}
