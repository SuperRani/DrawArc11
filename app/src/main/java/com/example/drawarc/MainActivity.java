package com.example.drawarc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public PieProgressDrawable pieProgressDrawable;
    public ImageView timeProgress;
    public TextView textPercent;
    public Button buttonStart, nextButton, newButton;
    private int progress1;
    MyCountDownTimer myCountDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart = findViewById(R.id.buttonStart);
        nextButton = findViewById(R.id.nextButton);
        newButton = findViewById(R.id.newButton);
        textPercent = findViewById(R.id.textPercent);

        buttonStart.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        newButton.setOnClickListener(this);


        pieProgressDrawable = new PieProgressDrawable();
        pieProgressDrawable.setColor(ContextCompat.getColor(getApplicationContext(), R.color.blue));
        timeProgress = findViewById(R.id.time_progress);
        timeProgress.setImageDrawable(pieProgressDrawable);

//        updateTime(0);   //0~100


    }

    public void updateTime(int progress) {
        pieProgressDrawable.setLevel(progress);
        timeProgress.invalidate();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonStart: {
                if (myCountDownTimer != null) {
                    myCountDownTimer.cancel();
                }
                    myCountDownTimer = new MyCountDownTimer(1000, 10);
                    myCountDownTimer.start();
//                    timeProgress.setImageDrawable(pieProgressDrawable);




                break;
            }

            case R.id.nextButton: {
                Intent i = new Intent(getApplicationContext(), CircleProgress.class);
                startActivity(i);
                break;
            }
            case R.id.newButton: {
                Intent i = new Intent(getApplicationContext(), NPProgressActivity.class);
                startActivity(i);
                break;
            }


        }
    }


    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int progress = (int) (millisUntilFinished / 10);
            textPercent.setText(progress + "%");
            updateTime(progress);

        }

        @Override
        public void onFinish() {
            Toast.makeText(MainActivity.this, "끝났습니다", Toast.LENGTH_SHORT).show();
            scheduleNextTimer();
        }

        private void scheduleNextTimer() {
            if (progress1 < 100) {
                myCountDownTimer = new MyCountDownTimer(1000, 10);
                myCountDownTimer.start();
            }
        }

    }
}
