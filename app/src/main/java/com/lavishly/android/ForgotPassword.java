package com.lavishly.android;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText passwordEmail;
    private Button resetPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        passwordEmail = (EditText) findViewById(R.id.etPasswordEmail);
        resetPassword = (Button) findViewById(R.id.btnPasswordReset);
        mAuth = FirebaseAuth.getInstance();


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = passwordEmail.getText().toString().trim();


                if (useremail.equals("")) {
                    Snackbar forgotPasswordResetEmptySuccessSnackBar = Snackbar.make(findViewById(R.id.activity_forgot_password), "Enter Valid Email Address", Snackbar.LENGTH_LONG);
                    forgotPasswordResetEmptySuccessSnackBar.show();
                } else {
                    mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Snackbar forgotPasswordResetSuccessSnackBar = Snackbar.make(findViewById(R.id.activity_forgot_password), "Password Reset Email Sent To Registered Email Address", Snackbar.LENGTH_LONG);
                                forgotPasswordResetSuccessSnackBar.show();
                                finish();
                                Intent openLoginActivityFromForgotPassword = new Intent(ForgotPassword.this, LoginActivity.class);
                                startActivity(openLoginActivityFromForgotPassword);


                            } else {
                                Snackbar forgotPasswordResetFailureSnackBarOne = Snackbar.make(findViewById(R.id.activity_forgot_password), "Register Your Email Address First", Snackbar.LENGTH_LONG);
                                forgotPasswordResetFailureSnackBarOne.show();
                                Snackbar forgotPasswordResetSuccessSnackBarTwo = Snackbar.make(findViewById(R.id.activity_forgot_password), "Check Your Internet Connectivity", Snackbar.LENGTH_LONG);
                                forgotPasswordResetSuccessSnackBarTwo.show();

                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
