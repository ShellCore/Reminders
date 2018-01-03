package com.shellcore.android.reminders;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by MOGC on 03/01/2018.
 */

public class Reminder extends Service {

    private static final String TAG = "Reminder Service";

    NotificationCompat.Builder builder;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate: Servicio creado");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: Servicio destruido");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStartCommand: Hemos arrancado el servicio con startCommand");
        builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Recordatorio")
                .setContentText("Tu bocadillo está listo para ser recogido!");

        new Thread(new Runnable() {
            @Override
            public void run() {

                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(0, builder.build());
                        cancel();
                    }
                }, 1000 * 20);

                /**
                 * Comentando la siguiente línea de código, el servicio queda en ejecución en
                 * segundo plano, incluso cuando la aplicación ha sido destruida. El problema recide
                 * en que tendría que implementarse la forma para poder detener el hilo de ejecución
                 * con ésta instrucción.
                 */
                stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }
}
