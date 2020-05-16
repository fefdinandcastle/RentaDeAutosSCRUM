package com.example.servicejava;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class ForegroundService extends Service {

    private String CHANNEL_ID = "Foreground Service";
    private NotificationCompat.Builder notification;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    //Is called after onBind()
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    //Used only once
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //Called before service destroy
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void createNotification(){
        notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                .sets
    }
}
