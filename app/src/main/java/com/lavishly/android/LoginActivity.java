package com.lavishly.android;

import android.app.ProgressDialog;
import android.content.Intent;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class LoginActivity extends AppCompatActivity {


    private CallbackManager mCallbackManager;
    LoginButton FacebookLoginButton;


    private EditText Email;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();


        if (user != null) {
            finish();
            Intent openMainPageActivityFromLogin = new Intent(LoginActivity.this, MainPageActivity.class);
            startActivity(openMainPageActivityFromLogin);
        } else {
            setContentView(R.layout.activity_login);
            setupUIViews();
            loadLoginFunctionality();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupUIViews() {
        Email = (EditText) findViewById(R.id.etEmail);
        Password = (EditText) findViewById(R.id.etPassword);
        Login = (Button) findViewById(R.id.btnLogin);
        FacebookLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        forgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        Info = (TextView) findViewById(R.id.tvInfo);
        Info.setText("No of Attempts Remaining: 5");
        userRegistration = (TextView) findViewById(R.id.tvRegister);
        progressDialog = new ProgressDialog(this);
    }

    //Facebook Login
    private void facebookLogin() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mCallbackManager = CallbackManager.Factory.create();
        FacebookLoginButton.setReadPermissions("email", "public_profile");

    }

    private void setFacebookLoginButton() {
        facebookLogin();
        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String userID = loginResult.getAccessToken().getUserId();
                handleFacebookToken(loginResult.getAccessToken());

            }

            @Override
            public void onCancel() {
                Snackbar setFacebookLoginButtononCancelSnackBar = Snackbar.make(findViewById(R.id.activity_login), "Facebook Login Cancelled", Snackbar.LENGTH_LONG);
                setFacebookLoginButtononCancelSnackBar.show();

            }

            @Override
            public void onError(FacebookException error) {
                Snackbar setFacebookLoginButtononErrorSnackBar = Snackbar.make(findViewById(R.id.activity_login), error.getMessage(), Snackbar.LENGTH_LONG);
                setFacebookLoginButtononErrorSnackBar.show();

            }
        });
    }

    private void handleFacebookToken(AccessToken accessToken) {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        final AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());

        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.signInWithCredential(credential);
                            progressDialog.dismiss();

                        } else {
                            progressDialog.dismiss();
                            Snackbar handleFacebookTokenSnackBar = Snackbar.make(findViewById(R.id.activity_login), "Authentication Failed", Snackbar.LENGTH_LONG);
                            handleFacebookTokenSnackBar.show();

                        }
                        progressDialog.dismiss();
                    }
                });
    }


    //Facebook Login End

    private void loadLoginFunctionality() {
        Login.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                validate(Email.getText().toString(), Password.getText().toString());
            }
        });

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {

                openRegistrationActivityFromLogin();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openForgotPasswordActivityFromLogin = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(openForgotPasswordActivityFromLogin);
            }
        });
        FacebookLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFacebookLoginButton();
            }
        });
    }

    public void openRegistrationActivityFromLogin() {
        Intent openRegistrationActivityFromLogin = new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(openRegistrationActivityFromLogin);
    }

    private void validate(String userEmail, String userPassword) {
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();

                    checkEmailVerification();


                } else {
                    progressDialog.dismiss();
                    Snackbar loginValidateSnackBar = Snackbar.make(findViewById(R.id.activity_login), "Login Failed", Snackbar.LENGTH_LONG);
                    loginValidateSnackBar.show();
                    counter--;
                    Info.setText("No of Attempts Remaining: " + counter);
                    if (counter == 0) {
                        Login.setEnabled(false);
                    }
                }
            }
        });


    }

    private void checkEmailVerification() {
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        Boolean emailflag = user.isEmailVerified();

        if (emailflag) {
            finish();
            Intent openMainPageActivityFromLogin = new Intent(LoginActivity.this, MainPageActivity.class);
            startActivity(openMainPageActivityFromLogin);
            Snackbar loginCheckEmailVerificationSuccessSnackBar = Snackbar.make(findViewById(R.id.activity_login), "Login Successful", Snackbar.LENGTH_LONG);
            loginCheckEmailVerificationSuccessSnackBar.show();
        } else {
            Snackbar loginCheckEmailVerificationFailureSnackBar = Snackbar.make(findViewById(R.id.activity_login), "Verify Your Email Address", Snackbar.LENGTH_LONG);
            loginCheckEmailVerificationFailureSnackBar.show();
            mAuth.signOut();
        }
    }


}


