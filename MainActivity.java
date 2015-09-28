package pollux.trialbee.com.pollux;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {
    private HashMap<Integer, Callback> callbacks;
    private UniqueInteger   uniqueInteger;
    private static final String TAG = "MainActivity";
    private Bridge bridge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bridge = new Bridge(this);
        uniqueInteger = new UniqueInteger();
        callbacks = new HashMap<>();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "OnActivityResult");
        Callback cb = callbacks.get(requestCode);
        cb.done(requestCode, resultCode, data);
    }

    public void processIntent(Intent intent, Callback callback) {
        Log.d(TAG, "processIntent");
        int requestCode = uniqueInteger.getUniqueInteger();
        callbacks.put(requestCode, callback);
        startActivityForResult(intent, requestCode);
    }
}