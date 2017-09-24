package com.yostajsc.luckyseven;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2, text3;
    Button bnPlay;

    boolean isPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    void initViews() {

        text1 = (TextView) findViewById(R.id.text_1);
        text2 = (TextView) findViewById(R.id.text_2);
        text3 = (TextView) findViewById(R.id.text_3);
        bnPlay = (Button) findViewById(R.id.btn_play);

        bnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay) {
                    pause();
                } else {
                    play();
                }
            }
        });
    }

    void pause() {
        isPlay = false;
        bnPlay.setText("Chạy");
        if (timer1 != null)
            timer1.cancel();
        if (timer2 != null)
            timer2.cancel();
        if (timer3 != null)
            timer3.cancel();

        String num1 = text1.getText().toString();
        String num2 = text2.getText().toString();
        String num3 = text3.getText().toString();

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        int n3 = Integer.parseInt(num3);
        if (n1 == n2 && n2 == n3 && n1 == 7) {
            Toast.makeText(this, "BINGO", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Chúc bạn mai mắn lần sau!", Toast.LENGTH_LONG).show();
        }
    }

    void play() {
        isPlay = true;
        bnPlay.setText("Dừng");
        makeData1();
        makeData2();
        makeData3();
    }

    Timer timer1 = null;

    void makeData1() {

        final Random random = new Random();
        timer1 = new Timer();
        timer1.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text1.setText(String.valueOf(random.nextInt(10)));
                    }
                });
            }
        }, 10, 10);
    }

    Timer timer2 = null;

    void makeData2() {
        final Random random = new Random();
        timer2 = new Timer();

        timer2.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text2.setText(String.valueOf(random.nextInt(10)));
                    }
                });
            }
        }, 10, 10);

    }

    Timer timer3 = null;

    void makeData3() {
        final Random random = new Random();
        timer3 = new Timer();

        timer3.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        text3.setText(String.valueOf(random.nextInt(10)));
                    }
                });
            }
        }, 10, 10);
    }

}
