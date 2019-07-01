package com.example.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    public SurfaceView sView;
    public boolean isRecording = false;
    public File videoFile;
    public MediaRecorder mRecorder;
    public ImageButton record = findViewById(R.id.record);
    public ImageButton stop = findViewById(R.id.stop);

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



        record.setOnClickListener(this);
        stop.setOnClickListener(this);

        sView = this.findViewById(R.id.sView);
        sView.getHolder().setFixedSize(320, 280);
        sView.getHolder().setKeepScreenOn(true);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View source) {
        switch (source.getId()){
            //单击录制按钮
            case R.id.record:
                if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    Toast.makeText(MainActivity.this, "SD卡不存在", Toast.LENGTH_LONG).show();
                    return;
                }
                try{
                    videoFile = new File(Environment.getExternalStorageDirectory().getCanonicalFile() + "/myvideo.mp4");

                    mRecorder = new MediaRecorder();
                    mRecorder.reset();

                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
                    mRecorder.setVideoSize(320, 280);
                    mRecorder.setVideoFrameRate(4);
                    mRecorder.setOutputFile(videoFile.getAbsoluteFile());
                    mRecorder.setPreviewDisplay(sView.getHolder().getSurface());
                    mRecorder.prepare();
                    mRecorder.start();
                    System.out.println("---recording---");
                    record.setEnabled(true);
                    isRecording = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stop:
                if(isRecording)
                {
                    mRecorder.stop();
                    mRecorder.release();
                    mRecorder = null;
                    record.setEnabled(true);
                    stop.setEnabled(false);
                }
                break;
        }

    }
}
