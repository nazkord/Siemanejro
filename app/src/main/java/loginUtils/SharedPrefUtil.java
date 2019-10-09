package loginUtils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.siemanejro.siemanejroproject.R;

import model.User;

public enum SharedPrefUtil {
    LOGIN_SHARED_PREF_UTIL();

    public User getLoggerUser(Activity activity) {
        Context context = activity.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.login_preferences_key), Context.MODE_PRIVATE);

        String userName = sharedPref.getString(context.getString(R.string.shPref_login_key), null);
        String userPassword = sharedPref.getString(context.getString(R.string.shPref_password_key), null);
        return new User(userName,userPassword);
    }

    public void setLoggerUser(Activity activity, User user) {
        Context context = activity.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.login_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString(context.getString(R.string.shPref_login_key), user.getName());
        prefEditor.putString(context.getString(R.string.shPref_password_key), user.getPassword());
        prefEditor.apply();
    }
}
