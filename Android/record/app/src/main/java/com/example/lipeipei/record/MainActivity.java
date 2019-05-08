package com.example.lipeipei.record;

import android.media.AudioRecord;
import android.support.design.chip.ChipGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import static android.media.MediaRecorder.OutputFormat.THREE_GPP;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToggleButton button1 = (ToggleButton) findViewById(R.id.toggleButton);

        ChipGroup.OnCheckedChangeListener listener = new ChipGroup.OnCheckedChangeListener();{
            @Override
            public void onCheckedChanged(CompoundButton button,boolean isChecked)

            {
                if (isChecked)

                {
                AudioRecord audioRecord = new AudioRecord(3,33,1,THREE_GPP,55);

                audioRecord.startRecording();
                }

            }
        }
        button1.setOnCheckedChangeListener(listener);
    }
}
