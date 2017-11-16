package com.android.interview;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButtonLaunchMode = null;

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d("MLJ","thread id=" + handler.getLooper().getThread().getId() + ",name=" + handler.getLooper().getThread().getName());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("MLJ","main thread id=" + this.getMainLooper().getThread().getId() + ",name2=" +this.getMainLooper().getThread().getName());


        handler.post(new Runnable() {
            @Override
            public void run() {
                Log.d("MLJ","thread id2=" + handler.getLooper().getThread().getId() + ",name2=" + handler.getLooper().getThread().getName());
            }
        });
        mButtonLaunchMode = (Button) findViewById(R.id.id_btn_launch_mode);
        mButtonLaunchMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMode();
            }
        });
    }

    private void launchMode() {
        sendNotification();
    }

    private void sendNotification() {
        //获取NotificationManager实例
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //实例化NotificationCompat.Builde并设置相关属性
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                //设置小图标
                .setSmallIcon(R.mipmap.ic_launcher)
                //设置通知标题
                .setContentTitle("最简单的Notification")
                //设置通知内容
                .setContentText("只有小图标、标题、内容");
        //设置通知时间，默认为系统发出通知的时间，通常不用设置
        //.setWhen(System.currentTimeMillis());
        //通过builder.build()方法生成Notification对象,并发送通知,id=1


        //跳转活动
        Intent intent =new Intent (MainActivity.this,SingleTopActivity.class);
        PendingIntent pi = PendingIntent.getActivities(MainActivity.this, 0, new Intent[]{intent}, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pi);
        //创建通知栏对象，显示通知信息
        notifyManager.notify(1, builder.build());
    }

}
