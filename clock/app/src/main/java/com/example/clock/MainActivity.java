package com.example.clock;
import android.app.AlarmManager;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //隐藏导航栏
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //设置音乐播放器
        // MediaPlayer player = MediaPlayer.create(this,R.raw.d);
        //MediaPlayer player = new MediaPlayer();
        //player.start()
        //提示时日期间
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int month = calendar.get(Calendar.MONTH);
        month += 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);

        if (hour>=22 |hour <= 24){
            Intent intent = new Intent(MainActivity.this,clockActivity.class);
            startActivity(intent);
         }

        // 半点报时
        if (minute%2 != 0){
            if (minute < 10)
                Toast.makeText(this, month + "月" + day + "日" + hour + ":0" + minute, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, month + "月" + day + "日" + hour + ":" + minute, Toast.LENGTH_LONG).show();
        }

//        ImageView image1 = (ImageView)findViewById(R.id.iv);
//        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

    }


}

