package ff.findyourfriend.util;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lmartinr on 25/04/16.
 */
public final class NetworkController {
    public static String LOGIN_URL = "http://login";
    public static String COORDENATES_URL = "http://cordenates";

    private NetworkController() {

    }

    public static boolean atenticate(String username, String password) {
        boolean isValid = false;
        try {
            URL url = new URL(LOGIN_URL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            // readStream(in);
        } catch (Exception e) {
            Log.d(NetworkController.class.getName(), " Error al realizar la conexión HttpURL al autenticar: " + e);
        }
        return isValid;
    }
}
