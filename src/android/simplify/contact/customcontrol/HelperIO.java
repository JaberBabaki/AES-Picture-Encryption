package android.simplify.contact.customcontrol;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;


public class HelperIO {

    public static void closeStream(InputStream stream) {
        try {
            stream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void closeStream(OutputStream stream) {
        try {
            stream.flush();
            stream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void closeChannel(FileChannel channel) {
        try {
            channel.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void copyFile(String inputFilename, String outputFilename) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new FileInputStream(inputFilename);
            outputStream = new FileOutputStream(outputFilename);

            copyFile(inputStream, outputStream);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }
    }


    public static void copyFile(String inputFilename, OutputStream outputStream) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(inputFilename);

            copyFile(inputStream, outputStream);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }
    }


    public static void copyFile(InputStream inputStream, String outputFilename) {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(outputFilename);

            copyFile(inputStream, outputStream);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }
    }


    public static void copyFile(InputStream inputStream, OutputStream outputStream) {
        byte[] buffer = new byte[8 * 1024];
        int lenRead = 0;
        try {
            while ((lenRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, lenRead);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeStream(inputStream);
            closeStream(outputStream);
        }
    }
}
