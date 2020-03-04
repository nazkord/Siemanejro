package com.siemanejro.siemanejroproject.activities;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.AccountPicker;
import com.siemanejro.siemanejroproject.R;
import com.siemanejro.siemanejroproject.SiemanejroApp;
import com.siemanejro.siemanejroproject.utils.SharedPrefUtil;

import static android.accounts.AccountManager.newChooseAccountIntent;

public class AuthActivity extends AppCompatActivity {

    public static final int AUTHORIZATION_CODE = 1993;
    public static final int ACCOUNT_CODE = 1603;

    private AccountManager accountManager;
    private SharedPrefUtil sharedPrefUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


        accountManager = AccountManager.get(this);

        sharedPrefUtil = new SharedPrefUtil(this);
        if (sharedPrefUtil.getLoggerUser() != null
                && sharedPrefUtil.getToken() != null) {
            doAuth();
        } else {
            chooseAccount();
        }

    }

    private void doAuth() {
        String token = sharedPrefUtil.getToken();
        Log.i("AuthApp", token);
        //TODO: make request to server
    }

    private void chooseAccount() {
        Intent intent = AccountPicker.newChooseAccountIntent(null, null, new String[]{"com.google"},
                false, null, null, null, null);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            requestToken();
        }
    }

    private void requestToken() {
        accountManager = AccountManager.get(SiemanejroApp.getContext());
        Bundle options = new Bundle();

        accountManager.getAuthToken(
                getAccount(),
                "Manage your tasks",            // Auth scope
                options,                        // Authenticator-specific options
                this,
                new OnTokenAcquired(),          // Callback called when a token is successfully acquired
                null); //show be: new Handler(new Error())
    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {
        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            try {
                // Get the result of the operation from the AccountManagerFuture.
                Bundle bundle = result.getResult();
                Intent launch = (Intent) bundle.get(AccountManager.KEY_INTENT);

                if (launch != null) {

                    startActivityForResult(launch, 0);

                } else {
                    // The token is a named value in the bundle. The name of the value
                    // is stored in the constant AccountManager.KEY_AUTHTOKEN.
                    String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);

                    sharedPrefUtil.setToken(token);

                    doAuth();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }
    }

    private Account getAccount() {

        //proper version
//        User user = sharedPrefUtil.getLoggerUser();
//        for (Account account : accountManager.getAccountsByType("com.google")) {
//            if (account.name.equals(user.getName())) {
//                return account;
//            }
//        }

        Account[] accounts = accountManager.getAccountsByType("com.google");
        if (accounts.length < 1) { //there is no account of such provider (google)
            //TODO: login to your google account
        }

        return accounts[0];
    }
}
