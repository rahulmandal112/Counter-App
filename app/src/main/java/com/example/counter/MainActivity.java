package com.example.counter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean isRunning=false;
    Button startBtn;
    Button stopBtn;
    TextView counterTV;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startBtn=findViewById(R.id.buttonStart);
        stopBtn=findViewById(R.id.buttonStop);
        counterTV=findViewById(R.id.textView);


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning=true;
                counter=0;
                new MyThred().start();
                startBtn.setEnabled(false);
                stopBtn.setEnabled(true);

            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRunning=false;
                startBtn.setEnabled(true);
                stopBtn.setEnabled(false);

            }
        });
    }
    Handler myhandler=new Handler(Looper.getMainLooper())
    {
        @Override
        public void handleMessage(@NonNull Message msg) {
            counterTV.setText(String.valueOf(msg.what));
        }
    };
    class MyThred extends  Thread
    {
        @Override
        public void run() {
            while (isRunning)
            {
                counter++;
                myhandler.sendEmptyMessage(counter);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
            }
        }
    }


}