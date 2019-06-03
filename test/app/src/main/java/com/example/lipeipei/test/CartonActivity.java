package com.example.lipeipei.test;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class CartonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);            //去除标题栏；
        setContentView(R.layout.activity_carton);

        ImageView imageView = findViewById(R.id.anim);
        imageView.setBackgroundResource(R.drawable.anim);
        final AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
        anim.start();
    }


}