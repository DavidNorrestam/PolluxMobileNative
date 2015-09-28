package pollux.trialbee.com.pollux;

import android.os.Build;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by dauvid on 2015-02-20.
 */
public class WebViewDataSender {
    private WebView webView;
    private Bridge bridge;
    private static final String TAG="WebViewDataSender";

    public WebViewDataSender(Bridge b, WebView wV) {
        this.bridge = b;
        webView = wV;
        webView.setWebViewClient(new WebViewClient());

        // Enable javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Enable remote debugging if OS version is high enough
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        // Add javascript interface
        webView.addJavascriptInterface(new JsInterface(bridge), "Android");

        // Load pollux server page on "http://pollux-server.heroku.com"
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("http://pollux-server.heroku.com");
            }
        });
    }

    public void sendData(final String javascriptFunction, final String arg) {
        Log.d(TAG, "sendData");
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + javascriptFunction + "('" + arg + "')");
            }
        });
    }
}
