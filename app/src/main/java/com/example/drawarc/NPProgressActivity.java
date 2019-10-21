package com.example.drawarc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NPProgressActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edit_input_time;
    private Button clickButton, resetButton, setButton;
    private TextView nppText;
    private NewCountTimer timer;
    //    private CountDownTimer timer;
    private Boolean mTimeRunning = false;
    public static int millisInFuture = 10000;
    public static final int countDownInterval = 100;

    public long mTimeLeftInMills = millisInFuture;


    private int progress;
    private Context context;
    private com.example.drawarc.NewPieProgress newPg;
    public NewPieProgress.OnProgressListener mOnProgressListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_npprogress);
        context = this;
        newPg = findViewById(R.id.newPg);
        clickButton = (Button) findViewById(R.id.clickButton);
        setButton = findViewById(R.id.setButton);
        resetButton = findViewById(R.id.resetButton);
        nppText = findViewById(R.id.nppText);
        edit_input_time = findViewById(R.id.edit_input_time);

        clickButton.setOnClickListener(this);
        resetButton.setOnClickListener(this);
        setButton.setOnClickListener(this);


        mOnProgressListener = new NewPieProgress.OnProgressListener() {
            @Override
            public void onProgress(int percent) {
                Toast.makeText(context, percent + "%가 진행되었습니다.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                Toast.makeText(context, "끝났습니다.", Toast.LENGTH_SHORT).show();
            }
        };


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickButton: {
                if (mTimeRunning) {
                    pauseTimer();
                } else {
                    clickStart();
                }
                break;
            }
            case R.id.resetButton: {
                resetTimer();
                break;
            }

            case R.id.setButton: {
                String input = edit_input_time.getText().toString();


                if (input != null && input.length() != 0) {

                    setTime((Integer.parseInt(input)));

                    if (timer != null) {
                        timer.cancel();
                    }
                    timer = new NewCountTimer(Integer.parseInt(input) * 100, countDownInterval);

                    Log.e("IntegerparseInt", String.valueOf(Integer.parseInt(input) * 100 / 100));
                    timer.onTick(Integer.parseInt(input) * 100);
                    mOnProgressListener.onProgress(Integer.parseInt(input));

                } else {
                    Toast.makeText(this, "값을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;

                }


                break;
            }
        }

    }


    //click
    private void clickStart() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new NewCountTimer(millisInFuture, countDownInterval);
        timer.start();
        mTimeRunning = true;
        clickButton.setText("PAUSE");
        resetButton.setVisibility(View.INVISIBLE);

    }

    //pause
    private void pauseTimer() {
        timer.cancel();
        mTimeRunning = false;
        clickButton.setText("CLICK");
        resetButton.setVisibility(View.VISIBLE);


    }

    //reset
    private void resetTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new NewCountTimer(millisInFuture, countDownInterval);
        timer.onTick(millisInFuture);

    }

    //set
    public void setTime(long millseconds) {
        mTimeLeftInMills = millseconds;
    }


    public class NewCountTimer extends CountDownTimer {

        public NewCountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public NewCountTimer(String input, long countDownInterval) {
            super(Long.parseLong(input),countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTimeLeftInMills = millisUntilFinished;
            progress = (int)(mTimeLeftInMills / 100);
            nppText.setText(progress + "%");
            newPg.setProgress(progress);


        }

        @Override
        public void onFinish() {
            mOnProgressListener.onComplete();
            scheduleNextTimer();


        }

        private void scheduleNextTimer() {
            if (progress < 100) {
                timer = new NewCountTimer(millisInFuture, countDownInterval);
                timer.start();
            }
        }
    }
}
