package sweet.home.pinerria1.FCM;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import sweet.home.pinerria1.Activity.MainActivitie;
import sweet.home.pinerria1.R;


/**
 * Created by Andriod Avnish on 06-Mar-18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    //  private NotificationUtils notificationUtils;

    Bitmap image;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "Fromgdfhgdfghfgjf: " + remoteMessage.getFrom());
        Log.e(TAG, "Fromgdfhgdfghfgjf: " + remoteMessage.getData());
        Log.e(TAG, "Fromgdfhgdfghfgjf: " + remoteMessage.getMessageType());


        String data= String.valueOf(remoteMessage.getData());
        String org=data.replace("message=","");

        //org.substring(1, org.length()-1);
        //Log.d("ffdgdfgdfgd",  org.substring(1, org.length()-1).toString());

        JSONObject jsonObject= null;
        try {
            jsonObject = new JSONObject(org.substring(1, org.length()-1).toString());
            Log.d("ffdgdfgdfgd",jsonObject.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        JSONObject jsonObject1=jsonObject.optJSONObject("data");
//        Log.d("fsdgdgsdfgfdgdf",jsonObject1.optString("title"));
//        Log.d("fsdgdgsdfgfdgdf",jsonObject1.toString());
        //Log.d("fgdfgdfhdfhfghf",MyPrefrences.getNotiStatus(getApplicationContext())+"");


        //Todo notification
       // if (MyPrefrences.getNotiStatus(getApplicationContext())==true) {

        try {
            URL url = new URL(jsonObject.optString("image").toString());
            Log.d("fgdgdfgd",jsonObject.optString("image").toString());
            image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch(IOException e) {
            System.out.println(e);
        }


            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            inboxStyle.addLine(jsonObject.optString("message").toString());
            Notification notification;
            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                    getApplicationContext());
            Intent notificationIntent = new Intent(getApplicationContext(), MainActivitie.class);
            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
            notification = mBuilder.setSmallIcon(R.drawable.logo).setTicker("New Notification").setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(jsonObject.optString("title").toString())
                    .setTicker(jsonObject.optString("title").toString())
//                .setContentIntent(resultPendingIntent)
                    .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                    .setStyle(inboxStyle)
//                .setWhen(getTimeMilliSec(timeStamp))
                    .setSmallIcon(R.drawable.logo)
                    .setContentIntent(contentIntent)
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image))
//                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icon))
                    .setContentText(jsonObject.optString("message").toString())
                    .build();


            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, notification);
     //   }

    }

    /**
     * Showing notification with text only
     */
//    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
//        notificationUtils = new NotificationUtils(context);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
//    }

    /**
     * Showing notification with text and image
     */
//    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
//        notificationUtils = new NotificationUtils(context);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
//    }
}