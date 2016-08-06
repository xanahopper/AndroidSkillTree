package tech.xana.androidskilltree.component.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import tech.xana.androidskilltree.R;

public class DemoService extends Service {
    public static final String TAG = "DemoService";
    public static final int SERVICE_ID = 0x1211;

    private PendingIntent notificationIntent;
    private Notification mNotification;
    private DemoBinder mDemoBinder;

    private NotificationManager mNotificationManager;

    @Override
    public void onDestroy() {
        Log.i(TAG, "DemoService onDestory");
        Toast.makeText(this, "DemoService onDestory", Toast.LENGTH_SHORT).show();
        stopForeground(true);
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "DemoService onUnbind");
        Toast.makeText(this, "DemoService onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "DemoService onStartCommand");
        Toast.makeText(this, "DemoService onStartCommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "DemoService onCreate");
        Toast.makeText(this, "DemoService onCreate", Toast.LENGTH_SHORT).show();
        Intent controlIntent = new Intent(this, ServiceActivity.class);
        mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        notificationIntent = PendingIntent.getActivity(this, 0, controlIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mNotification = new Notification.Builder(this)
                .setContentTitle("DemoService")
                .setContentText("这是一个练习用的服务，点击可以打开控制界面。")
                .setContentIntent(notificationIntent)
                .setSmallIcon(R.drawable.ic_notification_icon1)
                .build();
        startForeground(SERVICE_ID, mNotification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "DemoService onBind");
        Toast.makeText(this, "DemoService onBind", Toast.LENGTH_SHORT).show();
        if (mDemoBinder == null) mDemoBinder = new DemoBinder();
        return mDemoBinder;
    }

    public interface IDemoBinder {
        void play(String name);

        void stop();

        void exit();
    }

    private class DemoBinder extends Binder implements IDemoBinder {

        @Override
        public void play(String name) {
            Notification notification = new Notification.Builder(DemoService.this)
                    .setContentTitle("音乐播放")
                    .setContentText(name)
                    .setContentIntent(notificationIntent)
                    .build();
            mNotificationManager.notify(SERVICE_ID, mNotification);
            Toast.makeText(DemoService.this, "DemoService Play", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void stop() {
            Notification notification = new Notification.Builder(DemoService.this)
                    .setContentTitle("音乐播放")
                    .setContentText("停止播放")
                    .setContentIntent(notificationIntent)
                    .build();
            mNotificationManager.notify(SERVICE_ID, mNotification);
            Toast.makeText(DemoService.this, "DemoService Stop", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void exit() {
            stopSelf();
        }
    }
}
