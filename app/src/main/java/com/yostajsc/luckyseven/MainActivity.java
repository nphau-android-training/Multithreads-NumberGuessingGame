package com.yostajsc.luckyseven;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2, text3, textTitle;
    Button btnPlay;

    boolean isPlay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {

        text1 = (TextView) findViewById(R.id.text_1);
        text2 = (TextView) findViewById(R.id.text_2);
        text3 = (TextView) findViewById(R.id.text_3);
        textTitle = (TextView) findViewById(R.id.text_title);

        btnPlay = (Button) findViewById(R.id.btn_play);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay) pause();
                else play();
            }
        });
        autoChangeColor();
    }

    int colorIndex = 0;

    private void autoChangeColor() {

        final int colorArr[] = new int[3];
        colorArr[0] = Color.RED;
        colorArr[1] = Color.GREEN;
        colorArr[2] = Color.BLUE;

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (colorIndex == 2) {
                    colorIndex = 0;
                }
                colorIndex++;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textTitle.setTextColor(colorArr[colorIndex]);
                    }
                });
            }
        }, 1000, 1000);
    }

    private Handler handler02;
    private Handler handler03;

    private void pause() {

        lock(true);

        isPlay = false;
        btnPlay.setText("Chạy");

        if (timer1 != null)
            timer1.cancel();

        if (handler02 == null)
            handler02 = new android.os.Handler();
        handler02.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timer2 != null)
                    timer2.cancel();
                handler02 = null;
            }
        }, 1000);

        if (handler03 == null)
            handler03 = new android.os.Handler();
        handler03.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (timer3 != null)
                    timer3.cancel();
                handler03 = null;
                confirmStore();
                lock(false);
            }
        }, 2000);


        String num1 = text1.getText().toString();
        String num2 = text2.getText().toString();
        String num3 = text3.getText().toString();

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);
        int n3 = Integer.parseInt(num3);
        if (n1 == n2 && n2 == n3 && n1 == 7)
            Toast.makeText(this, "BINGO", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Chúc bạn may mắn lần sau!", Toast.LENGTH_LONG).show();
    }

    private void lock(boolean isLock) {
        btnPlay.setClickable(!isLock);
        btnPlay.setEnabled(!isLock);
    }

    // Tạo hộp thoại UI thông báo xác nhận hành động
    private void confirmStore() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Bạn muốn lưu kết quả này không?")
                .setMessage("Lưu kết quả cao nhất để xếp hạng, bạn có muốn tiếp tục không?")
                .setCancelable(false)
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_LONG).show();
                    }
                })
                .create()
                .show();
    }

    private void play() {
        isPlay = true;
        btnPlay.setText("Dừng");
        makeData1();
        makeData2();
        makeData3();
    }

    private Timer timer1 = null;

    private void makeData1() {

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

    private Timer timer2 = null;

    private void makeData2() {
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

    private Timer timer3 = null;

    private void makeData3() {

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
