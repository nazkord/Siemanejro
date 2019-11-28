package utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.siemanejro.siemanejroproject.R;

import model.User;

public class SharedPrefUtil {

    public static User getLoggerUser(Activity activity) {
        Context context = activity.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.login_preferences_key), Context.MODE_PRIVATE);

        Long id = sharedPref.getLong(context.getString(R.string.shPref_id_key), 0);
        String userName = sharedPref.getString(context.getString(R.string.shPref_login_key), null);
        String userPassword = sharedPref.getString(context.getString(R.string.shPref_password_key), null);
        return new User(id,userName,userPassword);
    }

    public static void setLoggerUser(Activity activity, User user) {
        Context context = activity.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.login_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putString(context.getString(R.string.shPref_login_key), user.getName());
        prefEditor.putString(context.getString(R.string.shPref_password_key), user.getPassword());
        prefEditor.putLong(context.getString(R.string.shPref_id_key), user.getId());
        prefEditor.apply();
    }

    public static void deleteLoggedUser(Activity activity) {
        Context context = activity.getApplicationContext();
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.login_preferences_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.remove(context.getString(R.string.shPref_login_key));
        prefEditor.remove(context.getString(R.string.shPref_password_key));
        prefEditor.remove(context.getString(R.string.shPref_id_key));
        prefEditor.apply();
    }
}
