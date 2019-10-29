package com.medicine.app

import android.app.ProgressDialog
import android.os.Bundle
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_web_view)
        val pd = ProgressDialog(this);
        pd.setMessage("Please wait Loading...");
        pd.show();

        webview!!.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (!pd.isShowing()) {
                    pd.show()
                }

                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }
        }
        webview.settings.setJavaScriptEnabled(true);
        webview.loadUrl(intent.getStringExtra("url"))

    }

    override fun onBackPressed() {
        if (webview.canGoBack())
            webview.goBack()
        else
            super.onBackPressed()
    }
}
