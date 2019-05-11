package com.example.clock;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class ClockActivity extends AppCompatActivity {
    SensorManager sm;
    Sensor lightSensor;
    SensorEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //隐藏状态栏与标题栏
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_clock);

        //隐藏导航栏
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //获取SensorManager对象
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //获取Sensor对象
        lightSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        float light = event.values[0];
        setLight(ClockActivity.this, light);

    }


    void setLight(Activity context, float brightness)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.screenBrightness = brightness * (1f / 255f);
        context.getWindow().setAttributes(lp);
    }

}
