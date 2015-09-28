package pollux.trialbee.com.pollux;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by philip on 2015-02-26.
 */
public class ImageCallback implements Callback {
    private Uri photoFileUri;
    private Bridge bridge;
    private static final String TAG = "ImageCallback";
    private String callbackName;

    public ImageCallback(Bridge bridge, Uri photoFileUri, String callbackName){
        this.bridge = bridge;
        this.photoFileUri = photoFileUri;
        this.callbackName = callbackName;
    }

    @Override
    public void done(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "done");
        bridge.processCallback(callbackName, getImageBase64(photoFileUri));
    }

    private String getImageBase64(Uri uri) {
        Log.d(TAG, "getImageBase64");
        // Get photo file as bitMap
        File photoFile = new File(uri.getPath());
        String filePath = photoFile.getAbsolutePath();
        Log.d(TAG, "filePath to image is: " + filePath);
        Bitmap bm = BitmapFactory.decodeFile(filePath);

        // Convert bitmap to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}
