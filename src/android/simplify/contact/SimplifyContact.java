package android.simplify.contact;

import java.io.InputStream;
import java.util.ArrayList;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.simplify.contact.customcontrol.AdapterDetilsTopic;
import android.simplify.contact.customcontrol.ContactModel;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.raykaco.andriod.database.Check;


public class SimplifyContact extends Activity {

    /** Called when the activity is first created. */
    @Override
    protected void onResume() {
        super.onResume();
        G.currentActivity = this;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_root);

        LinearLayout laytabContact = (LinearLayout) findViewById(R.id.tab_contact);
        LinearLayout laytabTime = (LinearLayout) findViewById(R.id.tab_time);
        LinearLayout laytabFavorate = (LinearLayout) findViewById(R.id.tab_fav);

        final ImageView imgSec1 = (ImageView) findViewById(R.id.tab_sec1);
        final ImageView imgSec2 = (ImageView) findViewById(R.id.tab_sec2);
        final ImageView imgSec3 = (ImageView) findViewById(R.id.tab_sec3);

        imgSec1.setBackgroundColor(Color.parseColor("#29DDD7"));

        laytabContact.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                imgSec1.setBackgroundColor(Color.parseColor("#29DDD7"));
                imgSec2.setBackgroundColor(Color.parseColor("#2989DD"));
                imgSec3.setBackgroundColor(Color.parseColor("#2989DD"));

            }
        });
        laytabTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                imgSec1.setBackgroundColor(Color.parseColor("#2989DD"));
                imgSec2.setBackgroundColor(Color.parseColor("#2989DD"));
                imgSec3.setBackgroundColor(Color.parseColor("#29DDD7"));

            }
        });
        laytabFavorate.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                imgSec1.setBackgroundColor(Color.parseColor("#2989DD"));
                imgSec2.setBackgroundColor(Color.parseColor("#29DDD7"));
                imgSec3.setBackgroundColor(Color.parseColor("#2989DD"));
            }
        });

        // por kardan list
        ArrayAdapter adapterListNew = new AdapterDetilsTopic(getContacts(G.context));
        ListView lstProduct = (ListView) findViewById(R.id.lst_product);
        lstProduct.setAdapter(adapterListNew);
        adapterListNew.notifyDataSetChanged();
        lstProduct.setTextFilterEnabled(true);
        ///

        //sliding menu
        ImageView imgMenue = (ImageView) findViewById(R.id.men);
        final TextView txtNews = (TextView) findViewById(R.id.txt_news_sl);
        final DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        try {
            imgMenue.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Check check = new Check();
                    if (check.selectId() == 1) {
                        txtNews.setText("ON");
                    } else if (check.selectId() == 0) {
                        // Toast.makeText(G.context, "==>  " + "G.on", Toast.LENGTH_LONG).show();
                        txtNews.setText("OFF");
                    }

                    if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                        drawerLayout.closeDrawers();
                    } else {

                        drawerLayout.openDrawer(Gravity.RIGHT);
                    }
                }
            });
        }
        catch (Exception e) {
            Log.i("MEN", e.toString());
        }

        //on ya off karadn
        LinearLayout layLogin = (LinearLayout) findViewById(R.id.lay_login);
        LinearLayout layNecessary = (LinearLayout) findViewById(R.id.lay_necessary);
        layLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Check check = new Check();
                if (check.selectId() == 1) {
                    txtNews.setText("OFF");
                    check.setOff();
                } else if (check.selectId() == 0) {
                    // Toast.makeText(G.context, "==>  " + "G.on", Toast.LENGTH_LONG).show();
                    txtNews.setText("ON");
                    check.setOn();
                }

                //Toast.makeText(G.context, "==>  " + G.on, Toast.LENGTH_LONG).show();
            }
        });

        layNecessary.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intentm = new Intent(G.currentActivity, DesActivity.class);
                G.currentActivity.startActivity(intentm);
            }
        });

    }


    public ArrayList<ContactModel> getContacts(Context ctx) {
        ArrayList<ContactModel> list = new ArrayList<ContactModel>();
        ContentResolver contentResolver = ctx.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        Check ch = new Check();
        Log.i("TTT", ch.selectText());
        G.STR = ch.selectText();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor cursorInfo = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{ id }, null);
                    InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(ctx.getContentResolver(),
                            ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id)));

                    Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(id));
                    Uri pURI = Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

                    Bitmap photo = null;
                    if (inputStream != null) {
                        photo = BitmapFactory.decodeStream(inputStream);
                    }
                    while (cursorInfo.moveToNext()) {
                        ContactModel info = new ContactModel();
                        info.id = id;
                        info.name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                        info.mobileNumber = cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        info.photo = photo;
                        info.photoURI = pURI;
                        /*Toast.makeText(SimplifyContact.this, "Name: " + info.name
                                + ", Phone No: " + info.mobileNumber, Toast.LENGTH_SHORT).show();*/
                        list.add(info);
                    }

                    cursorInfo.close();
                }
            }
        }

        return list;
    }

}