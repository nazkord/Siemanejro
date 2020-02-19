package com.siemanejro.siemanejroproject.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.siemanejro.siemanejroproject.R;
import com.siemanejro.siemanejroproject.utils.SharedPrefUtil;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GoogleSingInActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 0;
    private  GoogleSignInClient googleSignInClient;
    private SignInButton googleSingInButton;

    @Override
    protected void onStart() {
        super.onStart();

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_sing_in);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.serverClientID))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSingInButton = findViewById(R.id.google_sign_in_button_2);
        googleSingInButton.setOnClickListener(v -> signIn());

    }

    private void updateUI(GoogleSignInAccount account) {
        //TODO: testing
        if(account != null) { //there is no logged google account
            startActivity(new Intent(GoogleSingInActivity.this, MainActivity.class));
        } else if(new SharedPrefUtil(this).getLoggerUser() == null) {
            
        }

            Toast.makeText(GoogleSingInActivity.this,"Please try again", Toast.LENGTH_SHORT).show();
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task completedTask) {
        try {
            GoogleSignInAccount account = (GoogleSignInAccount) completedTask.getResult(ApiException.class);
            String idToken = Objects.requireNonNull(account).getIdToken();

            Log.i("Googin token", idToken);

            updateUI(account);

        } catch (Throwable throwable) {
            Log.e("Google Sign In Error", "signInResult:failed code= " + throwable.getMessage());
            throwable.printStackTrace();
            updateUI(null);
        }
    }

    private void singOut() {
        googleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {

                });
    }
}