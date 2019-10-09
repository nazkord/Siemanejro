package loginUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.siemanejro.siemanejroproject.R;

import model.User;

public class sharedPrefUtils {
    public static User getLoggerUser(Activity activity) {
        Context context = activity.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.login_preferences_key), Context.MODE_PRIVATE);
        return null;

    }
}
