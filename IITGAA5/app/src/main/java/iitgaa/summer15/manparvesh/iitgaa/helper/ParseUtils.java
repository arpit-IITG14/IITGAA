package iitgaa.summer15.manparvesh.iitgaa.helper;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;

import iitgaa.summer15.manparvesh.iitgaa.app.AppConfig;

/**
 * Created by Man Parvesh on 7/9/2015.
 */
public class ParseUtils {
    private static String TAG = ParseUtils.class.getSimpleName();

    public static void verifyParseConfiguration(Context context) {
        if (TextUtils.isEmpty(AppConfig.PARSE_APPLICATION_ID) || TextUtils.isEmpty(AppConfig.PARSE_CLIENT_KEY)) {
            Toast.makeText(context, "Please configure your Parse Application ID and Client Key in AppConfig.java", Toast.LENGTH_LONG).show();
            ((Activity) context).finish();
        }
    }

    public static void registerParse(Context context) {
        // initializing parse library
        Parse.initialize(context, AppConfig.PARSE_APPLICATION_ID, AppConfig.PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParsePush.subscribeInBackground(AppConfig.PARSE_CHANNEL, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.e(TAG, "Successfully subscribed to Parse!");
            }
        });
    }

    public static void subscribeWithEmail(String email, String name, String phone, String country) {
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();

        installation.put("email", email);
        installation.put("name", name);
        installation.put("phone", phone);
        installation.put("country", country);

        installation.saveInBackground();

        Log.e(TAG, "Subscribed with email: " + email);
        Log.e(TAG, "Subscribed with name: " + name);
        Log.e(TAG, "Subscribed with phone: " + phone);
        Log.e(TAG, "Subscribed with country: " + country);

    }
}
