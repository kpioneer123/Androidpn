/*
 * Copyright (C) 2010 Moduad Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.androidpn.client;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/** 
 * Broadcast receiver that handles push notification messages from the server.
 * This should be registered as receiver in AndroidManifest.xml. 
 * 
 * @author Sehwan Noh (devnoh@gmail.com)
 */
public final class ShowNotificationReceiver extends BroadcastReceiver {

    private static final String LOGTAG = LogUtil
            .makeLogTag(ShowNotificationReceiver.class);

    //    private NotificationService notificationService;
    public static final String TYPE = "type"; //这个type是为了Notification更新信息的，这个不明白的朋友可以去搜搜，很多
    private int getNotificationIcon() {
        return sharedPrefs.getInt(Constants.NOTIFICATION_ICON, 0);
    }
    private SharedPreferences sharedPrefs;
    public ShowNotificationReceiver() {
//        this.sharedPrefs = context.getSharedPreferences(
//                Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
//
//        Intent intentClick = new Intent(context, ShowNotificationReceiver.class);
//        intentClick.setAction(Constants.ACTION_NOTIFICATION_CLICKED);
//        PendingIntent pendingIntentClick = PendingIntent.getBroadcast(context, 0, intentClick, PendingIntent.FLAG_ONE_SHOT);
//
//        Intent intentCancel = new Intent(context, ShowNotificationReceiver.class);
//        intentCancel.setAction(Constants.ACTION_NOTIFICATION_CLEARED);
//        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(context, 0, intentCancel, PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        Notification.Builder builder  = new  Notification.Builder(context)
//                .setSmallIcon(getNotificationIcon())
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntentClick)
//                .setDeleteIntent(pendingIntentCancel);
//        Notification notification=builder.getNotification();
//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(random.nextInt() /* ID of notification */, notification);

    }
    private static final Random random = new Random(System.currentTimeMillis());

    //    public ShowNotificationReceiver(NotificationService notificationService) {
    //        this.notificationService = notificationService;
    //    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(LOGTAG, "ShowNotificationReceiver.onReceive()...");
        String action = intent.getAction();
        Log.d(LOGTAG, "action=" + action);
        this.sharedPrefs = context.getSharedPreferences(
                Constants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);

            String notificationId = intent
                    .getStringExtra(Constants.NOTIFICATION_ID);
            String notificationApiKey = intent
                    .getStringExtra(Constants.NOTIFICATION_API_KEY);
            String notificationTitle = intent
                    .getStringExtra(Constants.NOTIFICATION_TITLE);
            String notificationMessage = intent
                    .getStringExtra(Constants.NOTIFICATION_MESSAGE);
            String notificationUri = intent
                    .getStringExtra(Constants.NOTIFICATION_URI);
            String notificationImageUrl = intent
                    .getStringExtra(Constants.NOTIFICATION_IMAGE_URL);

        //设置点击通知栏的动作为启动另外一个广播
        Intent broadcastIntent = new Intent(context, NotificationReceiver.class);
        broadcastIntent.putExtras(intent.getExtras());
        PendingIntent pendingIntent = PendingIntent.
                getBroadcast(context, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder  = new  Notification.Builder(context);
        builder.setContentTitle(notificationTitle)
                .setContentIntent(pendingIntent)
                .setContentText(notificationMessage)
                .setSmallIcon(getNotificationIcon());


        Log.i("repeat", "showNotification");
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification=builder.getNotification();
        manager.notify(random.nextInt(), notification);

//
//        if (Constants.ACTION_SHOW_NOTIFICATION.equals(action)) {
//            String notificationId = intent
//                    .getStringExtra(Constants.NOTIFICATION_ID);
//            String notificationApiKey = intent
//                    .getStringExtra(Constants.NOTIFICATION_API_KEY);
//            String notificationTitle = intent
//                    .getStringExtra(Constants.NOTIFICATION_TITLE);
//            String notificationMessage = intent
//                    .getStringExtra(Constants.NOTIFICATION_MESSAGE);
//            String notificationUri = intent
//                    .getStringExtra(Constants.NOTIFICATION_URI);
//            String notificationImageUrl = intent
//                    .getStringExtra(Constants.NOTIFICATION_IMAGE_URL);
//            Log.d(LOGTAG, "notificationId=" + notificationId);
//            Log.d(LOGTAG, "notificationApiKey=" + notificationApiKey);
//            Log.d(LOGTAG, "notificationTitle=" + notificationTitle);
//            Log.d(LOGTAG, "notificationMessage=" + notificationMessage);
//            Log.d(LOGTAG, "notificationUri=" + notificationUri);
//            Log.d(LOGTAG, "notificationImageUrl=" + notificationImageUrl);
//
//            Notifier notifier = new Notifier(context);
//            notifier.notify(notificationId, notificationApiKey,
//                    notificationTitle, notificationMessage, notificationUri,notificationImageUrl);
//        } else if (Constants.ACTION_NOTIFICATION_CLICKED.equals(action)) {
//                    String notificationId = intent
//                            .getStringExtra(Constants.NOTIFICATION_ID);
//                    String notificationApiKey = intent
//                            .getStringExtra(Constants.NOTIFICATION_API_KEY);
//                    String notificationTitle = intent
//                            .getStringExtra(Constants.NOTIFICATION_TITLE);
//                    String notificationMessage = intent
//                            .getStringExtra(Constants.NOTIFICATION_MESSAGE);
//                    String notificationUri = intent
//                    .getStringExtra(Constants.NOTIFICATION_URI);
//                   String notificationImageUrl = intent
//                    .getStringExtra(Constants.NOTIFICATION_IMAGE_URL);
//                    Log.e(LOGTAG, "notificationId=" + notificationId);
//                    Log.e(LOGTAG, "notificationApiKey=" + notificationApiKey);
//                    Log.e(LOGTAG, "notificationTitle=" + notificationTitle);
//                    Log.e(LOGTAG, "notificationMessage=" + notificationMessage);
//                    Log.e(LOGTAG, "notificationUri=" + notificationUri);
//                    Log.e(LOGTAG, "notificationImageUrl=" + notificationImageUrl);
//                    Intent detailsIntent = new Intent();
//                    detailsIntent.setClass(context, NotificationDetailsActivity.class);
//                    detailsIntent.putExtras(intent.getExtras());
//                    //            detailsIntent.putExtra(Constants.NOTIFICATION_ID, notificationId);
//                    //            detailsIntent.putExtra(Constants.NOTIFICATION_API_KEY, notificationApiKey);
//                    //            detailsIntent.putExtra(Constants.NOTIFICATION_TITLE, notificationTitle);
//                    //            detailsIntent.putExtra(Constants.NOTIFICATION_MESSAGE, notificationMessage);
//                    //            detailsIntent.putExtra(Constants.NOTIFICATION_URI, notificationUri);
//                    detailsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    detailsIntent.setFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//
//                    try {
//                        context.startActivity(detailsIntent);
//                    } catch (ActivityNotFoundException e) {
//                        Toast toast = Toast.makeText(context,
//                                "No app found to handle this request",
//                                Toast.LENGTH_LONG);
//                        toast.show();
//                    }
//
//                } else if (Constants.ACTION_NOTIFICATION_CLEARED.equals(action)) {
//                    //
//                }

    }

}
