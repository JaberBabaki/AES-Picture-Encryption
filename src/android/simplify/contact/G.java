package android.simplify.contact;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Environment;
import android.simplify.contact.customcontrol.HelperIO;
import android.view.LayoutInflater;
import com.raykaco.andriod.database.DataAccess;


public class G extends Application {

    public static Context        context;
    public static Typeface       font1;
    public static Typeface       font2;
    public static LayoutInflater inflater;
    public static Activity       currentActivity;

    public static final String   DIR_SDCARD    = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String         DB_PATH       = DIR_SDCARD + "/simplyContact/";
    public static String         DB_NAME_Ass   = "db/contact.db";
    public static String         DB_NAME       = "contact.db";
    public static String         DB_NAME_TXT   = "j.txt";
    public static String         DB_NAME_TXT_2 = "Test.txt";
    public static String         STR           = "Test.txt";

    public static String         FONT1_NAME    = "font/IRAN-Sans-Light.otf";


    @Override
    public void onCreate() {

        super.onCreate();

        context = getApplicationContext();

        font1 = Typeface.createFromAsset(context.getAssets(), FONT1_NAME);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        DataAccess dataAccess = new DataAccess();
        dataAccess.copyDatabase();

        AssetManager assetManager = context.getAssets();
        InputStream inputStream;
        try {
            inputStream = assetManager.open("db/Test.txt");
            HelperIO.copyFile(inputStream, DB_PATH + "Test.txt");

        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}