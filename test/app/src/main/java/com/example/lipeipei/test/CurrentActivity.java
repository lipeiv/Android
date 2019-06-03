package com.example.lipeipei.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.FileReader;
import static android.os.SystemClock.sleep;


public class CurrentActivity extends AppCompatActivity {

    public TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10,textView12;
    public String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current);

        int result1,result2, result3,result4,result5,result6,result7,result8,result9,result10,sum,average;

        result1=getCurrent();
        textView1 = findViewById(R.id.textCurrent1);
        textView1.setText(result1+"mA");

        result2=getCurrent();
        textView2 = findViewById(R.id.textCurrent2);
        textView2.setText(result2+"mA");

        result3=getCurrent();
        textView3 = findViewById(R.id.textCurrent3);
        textView3.setText(result3+"mA");

        result4=getCurrent();
        textView4 = findViewById(R.id.textCurrent4);
        textView4.setText(result4+"mA");

        result5=getCurrent();
        textView5 = findViewById(R.id.textCurrent5);
        textView5.setText(result5+"mA");

        result6=getCurrent();
        textView6 = findViewById(R.id.textCurrent6);
        textView6.setText(result6+"mA");

        result7=getCurrent();
        textView7 = findViewById(R.id.textCurrent7);
        textView7.setText(result7+"mA");

        result8=getCurrent();
        textView8 = findViewById(R.id.textCurrent8);
        textView8.setText(result8+"mA");

        result9=getCurrent();
        textView9 = findViewById(R.id.textCurrent9);
        textView9.setText(result9+"mA");

        result10=getCurrent();
        textView10 = findViewById(R.id.textCurrent10);
        textView10.setText(result10+"mA");



        sum=(result1+result2+result3+result4+result5+result6+result7+result8+result9+result10);
        average =sum / 10;
        textView12 = findViewById(R.id.textCurrent12);
        textView12.setText(average+"mA");


    }
    //private void bindView(){

    //  }
    private int getCurrent() {
        int result = 0;
        try {
            //  Class systemProperties = Class.forName("android.os.SystemProperties");
            // Method get = systemProperties.getDeclaredMethod("get", String.class);
            //  String platName = (String) get.invoke(null, "ro.hardware");
            // if (platName.startsWith("mt") || platName.startsWith("MT"))
            // MTK平台该值不区分充放电，都为负数，要想实现充放电电流增加广播监听充电状态即可

            String filePath = "/sys/class/power_supply/battery/device/FG_Battery_CurrentConsumption";
            result = Math.round(getMeanCurrentVal(filePath, 5, 0) / 10f);


            // result += ", 电压为：" + readFile("/sys/class/power_supply/battery/batt_vol", 0) + "mV";
          /*    else if (platName.startsWith("qcom")) {
                String filePath ="/sys/class/power_supply/battery/current_now";
                int current = Math.round(getMeanCurrentVal(filePath, 5, 0) / 10.0f);
                int voltage = readFile("/sys/class/power_supply/battery/voltage_now", 0) / 1000;
                // 高通平台该值小于0时电池处于放电状态，大于0时处于充电状态
                if (current < 0) {
                    result = "充电电流为：" + (-current) + "mA, 电压为：" + voltage + "mV";
                } else {
                    result = "放电电流为：" + current + "mA, 电压为：" + voltage + "mV";
                }
            } **/
        }

        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private float getMeanCurrentVal(String filePath, int totalCount, int intervalMs) {
        float meanVal = 0.0f;
        if (totalCount <= 0) {
            return 0.0f;
        }
        for (int i = 0; i < totalCount; i++) {
            try {
                float f = Float.valueOf(readFile(filePath, 0));
                meanVal += f / totalCount;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (intervalMs <= 0) {
                continue;
            }
            try {
                sleep(intervalMs);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return meanVal;
    }

    private int readFile(String path, int defaultValue) {
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            int i = Integer.parseInt(bufferedReader.readLine(), 10);
            bufferedReader.close();
            return i;
        }
        catch (Exception localException)
        {
        }
        return defaultValue;
    }

   /* private void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(CurrentActivity.this, content, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(content);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }****/

}
