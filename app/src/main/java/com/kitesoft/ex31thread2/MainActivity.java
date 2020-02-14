package com.kitesoft.ex31thread2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    MyThread tread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //액티비티가 메모리에서 없어질때 자동으로 실행되는 콜백메소드
    @Override
    protected void onDestroy() {
        //별도의 Thread를 종료 및 제거
        if( tread != null){
            tread.stopThread();
        }

        super.onDestroy();
    }


    public void clickBtn(View v){

        tread= new MyThread();
        tread.start();
    }//clickBtn() Method...

    //이너 클래스
    class MyThread extends Thread{
        //스레드의 멤버변수
        boolean isRun= true;

        @Override
        public void run() {

            while(isRun){

                //현재시간 출력
                Date now= new Date();
                final String s= now.toString();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });

                //5초간격
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }//run Method...

        public void stopThread(){
            isRun=false;
        }

//        public void pauseThread(){
//
//        }
//
//        public void resumeThread(){
//            synchronized (this){
//                this.notify();
//            }
//        }

    }//MyThread class...


}//MainActivity class.