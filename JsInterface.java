package pollux.trialbee.com.pollux;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * Created by dauvid on 2015-02-03.
 */
public class JsInterface {
    private Bridge bridge;
    private static final String TAG = "JsInterface";

    // Instantiate the interface and set the context
    JsInterface(Bridge bridge) {
        this.bridge = bridge;
    }

    //Request an image from the device camera
    @JavascriptInterface
    public void requestCamera(String callback) {
        Log.d(TAG, "requestImage");
        bridge.requestCamera(callback);
    }

    // Request an image from the device storage
    @JavascriptInterface
    public void requestImage(String callback) {
        Log.d(TAG, "requestImage");
        bridge.requestImage(callback);
    }

    // Request the devices gps-location
    @JavascriptInterface
    public void getGeolocation(String callback) {
        Log.d(TAG, "getGeolocation");
        bridge.getGeolocation(callback);
    }

    @JavascriptInterface
    public void functionCall(String type, String callback) {
        switch (type) {
            case "geolocation":
                getGeolocation(callback);
                break;
            case "camera":
                requestCamera(callback);
                break;
            case "image":
                requestImage(callback);
                break;
            default:
                break;
        }
    }
}