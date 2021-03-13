package android.simplify.contact;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.simplify.contact.customcontrol.DES;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DesActivity extends Activity {

    TextView txtPath;


    //Uri      uri;

    @Override
    protected void onResume() {
        super.onResume();
        G.currentActivity = this;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.en);

        Button btnSelect = (Button) findViewById(R.id.btn_read);
        Button btnDec = (Button) findViewById(R.id.btn_de);
        Button btnEn = (Button) findViewById(R.id.btn_en);

        final EditText edt = (EditText) findViewById(R.id.edt1);

        txtPath = (TextView) findViewById(R.id.txtPath);

        final DES des = new DES();

        btnSelect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                showFileChooser();
            }
        });

        btnEn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if ((edt.getText().toString().length() >= 5) && (edt.getText().toString().length() <= 8)) {
                    des.enCode(txtPath.getText().toString(), edt.getText().toString(), (String) txtPath.getTag());
                } else {
                    Toast.makeText(G.currentActivity, "password length between 5 to 8", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDec.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if ((edt.getText().toString().length() >= 5) && (edt.getText().toString().length() <= 8)) {
                    des.deCode(txtPath.getText().toString(), edt.getText().toString(), (String) txtPath.getTag());
                } else {
                    Toast.makeText(G.currentActivity, "password length between 5 to 8", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void showFileChooser() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.addCategory(Intent.CATEGORY_OPENABLE);
        chooseFile.setType("text/plain");
        startActivityForResult(Intent.createChooser(chooseFile, "Choose a file"), 1);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bitmap photo = (Bitmap) data.getData().getPath();
            if (data.getData().getScheme().toString().compareTo("content") == 0) {
                String str = getRealPathFromURI(data.getData());
                int pasNum = str.indexOf(".");
                String pasvand = str.substring(pasNum);

                txtPath.setText("" + str);
                txtPath.setTag("" + pasvand);
            } else {
                String selectedImageUri = data.getData().toString();
                int addNum = selectedImageUri.indexOf("/m");
                String addressFile = selectedImageUri.substring(addNum);
                int pasNum = selectedImageUri.indexOf(".");
                String pasvand = selectedImageUri.substring(pasNum);

                txtPath.setText("" + addressFile);
                txtPath.setTag("" + pasvand);

            }

        }
    }


    public String getRealPathFromURI(Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = G.context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

}
