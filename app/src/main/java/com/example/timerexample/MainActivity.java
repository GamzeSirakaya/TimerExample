package com.example.timerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.BoringLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Timer mTimer = null;
    private Handler mHandler;
    private TimerTask timerTask;
    private int counter = 0;
    private static Boolean DEBUG = true;
    private static String TAG = MainActivity.class.getSimpleName();
    TextView txt;
    Button startBtn, resetBtn, pauseBtn;

    private static final int delay = 1000 * 10;
    private static final int interval = 1000 * 5;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log("TimerExampleOncreate");
        startBtn = findViewById(R.id.startBtn);
        resetBtn = findViewById(R.id.resetBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        txt = findViewById(R.id.txt);


        startBtn.setOnClickListener(v -> {
            Log("StartCounter");
            startCounter();

        });
        resetBtn.setOnClickListener(v -> {
            Log("ResetCounter");
            resetCounter();
        });
        pauseBtn.setOnClickListener(v -> {
            Log("PauseCounter");
            pauseCounter();

        });
    }

    private void resetCounter() {
        counter = 0;
        changeText(counter);

    }

    private void pauseCounter() {
        if (mTimer != null)
            mTimer.cancel();
    }

    private void startCounter() {
        mTimer = new Timer();
        mHandler = new Handler();

        mTimer.scheduleAtFixedRate(new myTask(), delay, interval);
    }

    private void changeText(int counter) {
        txt.setText(String.valueOf(counter));
    }

    private void Log(String message) {
        if (DEBUG)
            Log.d(TAG, message);
    }

    class myTask extends TimerTask{

        @Override
        public void run() {
            counter++;
            changeText(counter);
        }
    }

}

