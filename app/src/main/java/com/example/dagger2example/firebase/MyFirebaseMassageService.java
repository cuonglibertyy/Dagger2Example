package com.example.dagger2example.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.example.dagger2example.R;
import com.example.dagger2example.constans.Constans;
import com.example.dagger2example.model.CancelTripEvent;
import com.example.dagger2example.model.NewTripEvent;
import com.example.dagger2example.ui.main.MainActivity;
import com.example.dagger2example.untils.StringUtils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class MyFirebaseMassageService extends FirebaseMessagingService {

    public MyFirebaseMassageService() {
    }

    String TAG = "adasdqweq";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            Map<String, String> data = remoteMessage.getData();
            if (data == null) {
                return;
            }
            String tripId = data.get(Constans.KEY_FIREBASE_SERVICE_ID);
            String code = data.get(Constans.KEY_NOTIFICATION_CODE);
            String content = data.get(Constans.KEY_NOTIFICATION_CONTENT);

            Log.d("tripId: %s", tripId);
            Log.d("adasdsad", code);
            Log.d("dadasdas", content);


            if(StringUtils.isEmpty(tripId)){
                return;
            }
            if (StringUtils.isEmpty(code)){

            }
            if (StringUtils.isEmpty(content)){

            }
            if (code.equals("211")){
                EventBus.getDefault().post(new NewTripEvent(tripId));
                return;
            }
            else if (code.equals("213")){
                EventBus.getDefault().post(new CancelTripEvent(tripId));
            }
            getdata(content);


        }







    private void getdata(String content) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.project_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_marker_drof_off)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_marker_drof_off))
                        .setContentTitle(getString(R.string.app_name))
                        .setContentText(content)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH);
//                        .addAction(new NotificationCompat.Action(
//                                android.R.drawable.sym_call_missed,
//                                "Cancel",
//                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)))
//                        .addAction(new NotificationCompat.Action(
//                                android.R.drawable.sym_call_outgoing,
//                                "OK",
//                                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT)));

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }
}
