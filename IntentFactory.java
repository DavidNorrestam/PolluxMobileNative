package pollux.trialbee.com.pollux;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by philip on 2015-02-26.
 */
public class IntentFactory {
    public static Intent createCameraIntent(Context context) {
        // Create the intent for capturing an image
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = AndroidHardware.requestImageFile();
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    Uri.fromFile(photoFile));
        }
        return takePictureIntent;
    }
    public static Intent createImageIntent(Context context) {
        // Create the intent for picking an image
        Intent getImageIntent = new Intent(Intent.ACTION_PICK);
        getImageIntent.setType("image/*");
        // Ensure that there's a camera activity to handle the intent

        return getImageIntent;
    }

    public static Intent createGeolocationIntent (Context context) {
        // Not implemented yet
        return null;
    }
}
