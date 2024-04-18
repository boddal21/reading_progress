package com.example.reading_progress;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileManager {
    public static final String APP_FOLDER_NAME = "ReadingProgressFolder";

    public static File getAppDirectory() {
        File externalStorageDir = Environment.getExternalStorageDirectory();
        return new File(externalStorageDir, APP_FOLDER_NAME);
    }

    public static void copyDrawableToAppFolder(Context context, String drawableName, File appFolder) {
        File targetFile = new File(appFolder, drawableName);
        if (targetFile.exists()) {
            return;
        }

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = context.getResources().openRawResource(
                    context.getResources().getIdentifier(drawableName, "drawable", context.getPackageName()));
            outputStream = new FileOutputStream(targetFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
