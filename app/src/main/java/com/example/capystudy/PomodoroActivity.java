package com.example.capystudy;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PomodoroActivity extends AppCompatActivity {

    private TextView textViewTimer;
    private Button buttonStart;
    private Button buttonReset;
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning = false;
    private long timeLeftInMillis = 1500000; // 25 minutos em milissegundos

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro);

        textViewTimer = findViewById(R.id.textView_timer);
        buttonStart = findViewById(R.id.button_start);
        buttonReset = findViewById(R.id.button_reset);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                isTimerRunning = false;
                buttonStart.setText("Iniciar");
            }
        }.start();

        isTimerRunning = true;
        buttonStart.setText("Pausar");
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        isTimerRunning = false;
        buttonStart.setText("Iniciar");
    }

    private void resetTimer() {
        timeLeftInMillis = 1500000; // 25 minutos em milissegundos
        updateCountDownText();
        if (isTimerRunning) {
            countDownTimer.cancel();
            isTimerRunning = false;
        }
        buttonStart.setText("Iniciar");
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        textViewTimer.setText(timeFormatted);
    }
}
