package sweet.home.homesweethome.FCM;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.List;

import sweet.home.homesweethome.Activity.MainActivitie;
import sweet.home.homesweethome.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    //  private NotificationUtils notificationUtils;

    Bitmap image;
    PendingIntent contentIntent;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "Fromgdfhgdfghfgjf: " + remoteMessage.getMessageType());
        Log.e(TAG, "Fromgdfhgdfghfgjf: " + remoteMessage.getData());
        // Log.e(TAG, "Fromgdfhgdfghfgjf: " + remoteMessage.getNotification().getBody());


//        Map<String, String> data2 = remoteMessage.getData();
//        String myCustomKey = data2.get("my_custom_key");
//
        Log.d("gdfgdfgdfgdfgdfgd", remoteMessage.getData().toString());

        JSONObject jsonObject = new JSONObject(remoteMessage.getData());

        Log.d("dfgfdgdfgtrd", String.valueOf(jsonObject));


        //Todo notification

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.addLine(jsonObject.optString("body"));
        Notification notification;
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                getApplicationContext());
        Intent notificationIntent = new Intent(getApplicationContext(), MainActivitie.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
        notification = mBuilder.setSmallIcon(R.drawable.app_logo_new).setTicker("HSH").setWhen(0)
                .setAutoCancel(true)
                .setContentTitle(jsonObject.optString("title"))
                .setTicker("Pineria")
//                .setContentIntent(resultPendingIntent)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setStyle(inboxStyle)
//                .setWhen(getTimeMilliSec(timeStamp))
                .setSmallIcon(R.drawable.app_logo_new)
                .setContentIntent(contentIntent)
                // .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image))
//                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                .setContentText(jsonObject.optString("body"))
                .build();


        NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);






    }

}