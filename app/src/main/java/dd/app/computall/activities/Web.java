package dd.app.computall.activities;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import dd.app.computall.R;

/**
 * Created by User on 12/04/2016.
 */
public class Web extends AppCompatActivity {

        private WebView webView;

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.web);

            webView = (WebView) findViewById(R.id.webV);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl("http://computall.netne.net/");


        }

    }

