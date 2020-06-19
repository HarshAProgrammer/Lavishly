package com.lavishly.android;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegistrationActivity extends AppCompatActivity {


    private EditText userName;
    private EditText userPhoneNo;
    private EditText userEmail;
    private EditText userPassword;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    String Email,PhoneNo,Password,Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);



        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        userName = (EditText) findViewById(R.id.etUserName);
        userPhoneNo = (EditText) findViewById((R.id.etUserPhoneNo));
        userEmail = (EditText) findViewById(R.id.etUserEmail);
        userPassword = (EditText) findViewById(R.id.etUserPassword);
        regButton = (Button) findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvUserLogin);













        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Upload data to the database
                    Name = userName.getText().toString().trim();
                    Email = userEmail.getText().toString().trim();
                    Password = userPassword.getText().toString().trim();
                    PhoneNo = userPhoneNo.getText().toString().trim();
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    mAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override


                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {


                                progressDialog.dismiss();
                                sendEmailVerification();


                            } else {
                                progressDialog.dismiss();
                                Snackbar registerIfFailureSnackBar = Snackbar.make(findViewById(R.id.activity_registration),"Registration Failed",Snackbar.LENGTH_LONG);
                                registerIfFailureSnackBar.show();
                            }


                        }
                    });
                }


                userLogin.setOnClickListener(new View.OnClickListener() {
                    @Override


                    public void onClick(View v) {
                        Intent openLoginActivityFromRegistration = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(openLoginActivityFromRegistration);
                    }
                });



            }




        });









    }


    private boolean validate() {
        Boolean result = false;

        Name = userName.getText().toString();
        Password = userPassword.getText().toString();
        Email = userEmail.getText().toString();
        PhoneNo = userPhoneNo.getText().toString();


        if (Name.isEmpty() || Password.isEmpty() || Email.isEmpty()){
            Snackbar registerValidateSuccessSnackBar = Snackbar.make(findViewById(R.id.activity_registration),"Please Enter All The Details",Snackbar.LENGTH_LONG);
            registerValidateSuccessSnackBar.show();

        }else {
            result = true;
        }


        return result;

    }



    private void sendEmailVerification(){
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Snackbar registerSendEmailVerificationSuccessSnackBarOne = Snackbar.make(findViewById(R.id.activity_registration),"Successfully Registered",Snackbar.LENGTH_SHORT);
                        registerSendEmailVerificationSuccessSnackBarOne.show();
                        Snackbar registerSendEmailVerificationSuccessSnackBarTwo = Snackbar.make(findViewById(R.id.activity_registration),"Verify Your Email Address",Snackbar.LENGTH_LONG);
                        registerSendEmailVerificationSuccessSnackBarTwo.show();
                        mAuth.signOut();
                        finish();
                        Intent openLoginActivityFromRegistration = new Intent(RegistrationActivity.this, LoginActivity.class);
                        startActivity(openLoginActivityFromRegistration);
                    }else{
                        Snackbar registerSendEmailVerificationFailureSnackBar = Snackbar.make(findViewById(R.id.activity_registration),"Registration Failed",Snackbar.LENGTH_SHORT);
                        registerSendEmailVerificationFailureSnackBar.show();

                    }
                }
            });
        }
    }

    private void sendUserData(){

        Name = userName.getText().toString();
        Email = userEmail.getText().toString();
        PhoneNo = userPhoneNo.getText().toString();



        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference mUIDReference = firebaseDatabase.getReference(mAuth.getUid());
        UserProfile userProfile = new UserProfile(Name, Email, PhoneNo);
        mUIDReference.setValue(userProfile);

    }

}


