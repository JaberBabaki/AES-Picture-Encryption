package com.android.internal.telephony;

import java.lang.reflect.Method;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.raykaco.andriod.database.Check;


public class main extends BroadcastReceiver {

    private static final String TAG    = null;
    String                      incommingNumber;
    String                      incno1 = "+989117124345";


    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (null == bundle) {
            return;
        }
        try {
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            Class c = Class.forName(tm.getClass().getName());
            Method m = c.getDeclaredMethod("getITelephony");
            m.setAccessible(true);
            Bundle b = intent.getExtras();
            incommingNumber = b.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

            Check check = new Check();
            if (check.selectId() == 1) {
                ITelephony telephonyService = (ITelephony) m.invoke(tm);
                telephonyService = (ITelephony) m.invoke(tm);
                telephonyService.endCall();
                telephonyService.silenceRinger();
            }

        }
        catch (Exception e) {
            Log.i("LOG", e.toString());
        }

    }
}