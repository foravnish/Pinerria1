package sweet.home.homesweethome.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import java.io.File;

import sweet.home.homesweethome.R;
import sweet.home.homesweethome.Utils.Api;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenualPDF extends Fragment {


    public MenualPDF() {
        // Required empty public constructor
    }
    public static final String SAMPLE_FILE = Api.SchoolManuals; //your file path


    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view=inflater.inflate(R.layout.fragment_menual_pd, container, false);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);

//        Log.d("dsfsfsdfgsdgs",getIntent().getStringExtra("link"));

        WebView webView =  view.findViewById(R.id.webview);
        //progressBar = (ProgressBar) findViewById(R.id.progressBar);

        webView.setWebViewClient(new MyBrowser());

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+SAMPLE_FILE);


        return view;
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE);

        }

    }


}
