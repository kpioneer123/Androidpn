package org.androidpn.client;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import org.androidpn.demoapp.R;

/**
 * Created by Xionghu at 2017/3/22 21:21
 * function:
 * version:
 */

public class BootCompletedReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Boot completed",Toast.LENGTH_SHORT).show();
        SharedPreferences pref = context.getSharedPreferences(Constants.SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE);
        if(pref.getBoolean(Constants.SETTINGS_AUTO_START,true)){
            ServiceManager serviceManager = new ServiceManager(context);
            serviceManager.setNotificationIcon(R.drawable.notification);
            serviceManager.startService();
        }
    }
}
