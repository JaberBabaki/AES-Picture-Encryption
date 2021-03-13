package android.simplify.contact.customcontrol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import android.simplify.contact.G;
import android.widget.Toast;


public class DES {

    File file1;


    public void enCode(String path, String key, String pasvand) {
        try {
            File file = new File(path);
            FileInputStream inFile = new FileInputStream(file);
            String pathh = file.getParent();
            String str = pathh + "/enddd" + pasvand;
            file1 = new File(str);
            FileOutputStream outStream = new FileOutputStream(file1);
            byte[] k = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(k, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            CipherOutputStream cos = new CipherOutputStream(outStream, cipher);
            byte[] buf = new byte[1024];
            int read;
            while ((read = inFile.read(buf)) != -1) {
                cos.write(buf, 0, read);
            }
            inFile.close();
            cos.flush();
            cos.close();
            Toast.makeText(G.currentActivity, "The file encrypted Successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {

        }

    }


    public void deCode(String path, String key, String pasvand) {
        try {
            File file = new File(path.toString());
            FileInputStream inFile = new FileInputStream(file1);
            String pathh = file.getParent();
            String str = pathh + "/dec" + pasvand;
            File file1 = new File(str);
            FileOutputStream outStream = new FileOutputStream(file1);
            byte[] k = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(k, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            CipherOutputStream cos = new CipherOutputStream(outStream, cipher);
            byte[] buf = new byte[1024];
            int read;
            while ((read = inFile.read(buf)) != -1) {
                cos.write(buf, 0, read);
            }
            inFile.close();
            cos.flush();
            cos.close();
            Toast.makeText(G.currentActivity, "The decrypted successfully", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
