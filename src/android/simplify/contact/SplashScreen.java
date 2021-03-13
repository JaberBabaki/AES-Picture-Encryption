package android.simplify.contact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.simplify.contact.customcontrol.CustomProgress;
import android.view.WindowManager;


public class SplashScreen extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        G.currentActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        CustomProgress cp = (CustomProgress) findViewById(R.id.csp);
        cp.setColor("#2989DD");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                finish();

                Intent intent = new Intent(G.currentActivity, SimplifyContact.class);
                G.currentActivity.startActivity(intent);

            }

        }, 5000);
    }
}
