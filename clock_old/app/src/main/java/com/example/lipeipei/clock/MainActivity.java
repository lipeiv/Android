package com.example.lipeipei.clock;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏状态栏与标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //隐藏导航栏
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //设置音乐播放器
        // MediaPlayer player = MediaPlayer.create(this,R.raw.d);
        //MediaPlayer player = new MediaPlayer();
        //player.start()
        //提示时日期间
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int month = calendar.get(Calendar.MONTH);
        month += 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //int year = calendar.get(Calendar.YEAR);
        if (minute < 10)
            Toast.makeText(this, month + "月" + day + "日" + hour + ":0" + minute, Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, month + "月" + day + "日" + hour + ":" + minute, Toast.LENGTH_LONG).show();

        setLight(MainActivity.this,0);
    }


    void setLight(Activity context, int brightness)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.screenBrightness = Float.valueOf(brightness) * (1f / 255f);
        context.getWindow().setAttributes(lp);
    }




}
