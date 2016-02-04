package ssu.insadomapp;

/**
 * Created by Marc on 12/01/2016.
 */
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class httpURLconnet {


    HttpURLConnection urlConnection;

    public httpURLconnet() throws IOException {
        URL url = new URL("http://www.android.com/");

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//            readStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally{
            urlConnection.disconnect();
        }
    }
}
