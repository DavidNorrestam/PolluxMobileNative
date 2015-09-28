package pollux.trialbee.com.pollux;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by dauvid on 2015-03-23.
 */
public class ImagePickCallback implements Callback {
    private Bridge bridge;
    private static final String TAG = "ImagePickCallback";
    private String callbackName;
    private Context context;
    public ImagePickCallback(Bridge bridge,Context context, String callbackName){
        this.bridge = bridge;
        this.callbackName = callbackName;
        this.context = context;
    }

    @Override
    public void done(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "hej");
        bridge.processCallback(callbackName, getImageBase64(data.getData()));
    }

    private String getImageBase64(Uri uri) {
        Log.d(TAG, "getImageBase64");
        // Get photo file as bitMap
        InputStream imageStream = null;
        try {
            imageStream = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(imageStream);

        // Convert bitmap to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }
}
