package fr.company.agterra.tompouss;

import android.app.Activity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Agterra on 17/01/2018.
 */

public class CustomWebViewClientEDT extends WebViewClient {

    public String currentUrl = null;

    public String baseEDTUrl = "http://edt.jordan-martin.fr/index";

    private Activity currentActivity;

    private static String privateFileName = "privateItemsFile";


    public CustomWebViewClientEDT(Activity baseActivity) {

        this.currentActivity = baseActivity;

    }

    @Override
    public void onPageFinished(WebView view, String url) {

        if(url != null && !url.contains("index"))
        {

            this.currentUrl = url;

            this.saveRessource(url);

        }

        super.onPageFinished(view, url);

    }

    public String loadSavedRessource()
    {

        String ressourceName = null;

        try
        {

            File itemsFile = new File(this.currentActivity.getFilesDir(), privateFileName);

            FileInputStream fileInputStream = new FileInputStream(itemsFile);

            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            ressourceName = (String)objectInputStream.readObject();

            this.currentUrl = ressourceName;

            objectInputStream.close();

            fileInputStream.close();

            System.out.println(ressourceName);

        }
        catch (Exception e)
        {

            System.out.println("Error: " +e.getMessage());

            //Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return ressourceName;

    }

    public void saveRessource(String ressource)
    {

        try
        {

            File itemsFile = new File(this.currentActivity.getFilesDir(), privateFileName);

            FileOutputStream fileOutputStream = new FileOutputStream(itemsFile);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(ressource);

            objectOutputStream.close();

            fileOutputStream.close();

        }
        catch (Exception e)
        {

            System.out.println("Error: " +e.getMessage());

            // Toast.makeText(getApplicationContext(), "Error : " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }

}
