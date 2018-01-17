package fr.company.agterra.tompouss;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class EDTFragment extends Fragment {

    private CustomWebViewClientEDT webViewClient;

    private WebView edtWebView;

    public EDTFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.edt_fragment_layout, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        this.webViewClient = new CustomWebViewClientEDT(getActivity());

        this.webViewClient.loadSavedRessource();

        this.edtWebView = getView().findViewById(R.id.edt_webview);

        edtWebView.setWebViewClient(this.webViewClient);

        if(edtWebView != null)
        {

            if(this.webViewClient.currentUrl == null) {

                edtWebView.loadUrl(this.webViewClient.baseEDTUrl);

            }
            else
            {

                edtWebView.loadUrl(this.webViewClient.currentUrl);

            }

        }

        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.edt_action_menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            case R.id.home_action:

                this.edtWebView.loadUrl(this.webViewClient.baseEDTUrl);

                this.webViewClient.currentUrl = null;

                this.webViewClient.saveRessource(null);

                return true;

            case R.id.refresh_action:

                this.edtWebView.reload();

                return true;

        }

        return false;

    }

}
