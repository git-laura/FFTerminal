package ff.findyourfriend.util;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

/**
 * Created by lmartinr on 25/04/16.
 */
public final class CacheController {

    private CacheController() {}

    public static String getUsername(Activity activity){
        SharedPreferences prefs = activity.getBaseContext().getSharedPreferences("UserData", 0);
        return prefs.getString("username","");
    }

    public static void setUsername(String username, Activity activity){
        SharedPreferences prefs = activity.getBaseContext().getSharedPreferences("UserData", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("username", username);
        editor.commit();
    }

    public static String getPassword(Activity activity){
        SharedPreferences prefs = activity.getBaseContext().getSharedPreferences("UserData", 0);
        return prefs.getString("password","");
    }

    public static void setPassword(String password, Activity activity){
        SharedPreferences prefs = activity.getBaseContext().getSharedPreferences("UserData", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("password", password);
        editor.commit();
    }
}
