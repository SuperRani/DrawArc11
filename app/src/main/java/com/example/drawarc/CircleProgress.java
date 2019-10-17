package com.example.drawarc;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;


import com.dinuscxj.progressbar.CircleProgressBar;


public class CircleProgress extends AppCompatActivity implements View.OnClickListener {
    private static final String DEFAULT_PATTERN = "%%";
    private CircleProgressBar mSolidProgressBar;
    private Button circleStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress_bar);


        mSolidProgressBar = findViewById(R.id.custom_progress2);
        circleStartButton = findViewById(R.id.circleStartButton);

        mSolidProgressBar.setProgressFormatter(new CircleProgressBar.ProgressFormatter() {
            @Override
            public CharSequence format(int progress, int max) {


                return String.format(progress + DEFAULT_PATTERN, (int) ((float) progress / (float) max * 100));
            }
        });

        circleStartButton.setOnClickListener(this);


    }

    private void simulateProgress() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                mSolidProgressBar.setProgress(progress);
            }
        });
//        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circleStartButton: {
                simulateProgress();
                break;
            }

        }
    }


}
