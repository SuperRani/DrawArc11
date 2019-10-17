package com.example.drawarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NPProgressActivity extends AppCompatActivity implements View.OnClickListener {
    private Button clickButton;
    private TextView nppText;
    private CountDownTimer timer;


    private int progress;
    private Context context;
    private com.example.drawarc.NewPieProgress newPg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npprogress);

        newPg = findViewById(R.id.newPg);
        clickButton = (Button) findViewById(R.id.clickButton);
        clickButton.setOnClickListener(this);
        nppText = findViewById(R.id.nppText);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickButton: {
                if (timer != null) {
                    timer.cancel();
                }
                timer = new NewCountTimer(10000, 100);

                timer.start();



                break;
            }
        }

    }




    public class NewCountTimer extends CountDownTimer {

        public NewCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            progress = (int) (millisUntilFinished / 100);
            nppText.setText(progress + "%");
            newPg.setProgress(progress);




        }

        @Override
        public void onFinish() {
            scheduleNextTimer();



        }

        private void scheduleNextTimer() {
            if (progress < 100) {
                timer = new NewCountTimer(10000, 100);
                timer.start();
            }
        }
    }
}
