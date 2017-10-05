package com.group25.design3a.senselock3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register_LogIn extends AppCompatActivity implements View.OnClickListener {

    private Button loginButton;
    private EditText userEmail;
    private EditText userPassword;
    private TextView signUp;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__log_in);

        //Declare variables and assign by ID
        //Button
        loginButton = (Button) findViewById(R.id.loginButton);
        //EditText
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.userPassword);
        //TextView
        signUp = (TextView) findViewById(R.id.loginError);

        //Initialize progress dialog
        progressDialog = new ProgressDialog(this);

        //Initialize firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        //OnClockListener Setup

        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);

        //Check if user is already signed in

        //if (firebaseAuth.getCurrentUser() !=null){
          // finish();
        //Move to fingerprint authentication
           //startActivity(new Intent(getApplicationContext(), FingerPrintLogin.class));
        //}

    }

    private void userLogin(){
        //Get email and password details

        //Email
        String email = userEmail.getText().toString().trim();
        //Password
        String password = userPassword.getText().toString().trim();

        //Check if fields are empty
        if(TextUtils.isEmpty(email)){
            //If email field is empty
            Toast.makeText(this, "One ore more fields are empty!",Toast.LENGTH_SHORT).show();
            return; //return; will stop the function from executing commands

        }
        if(TextUtils.isEmpty(password)){
            //If password field is empty
            Toast.makeText(this, "One more more fields are empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        //IF REGISTRATION IS OKAY AND ALL FIELDS ARE FILLED OUT...!
        //Progress dialog will indicate progress

        progressDialog.setMessage("Logging in, please wait");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            finish();
                            //Go to Main Activity
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == loginButton){
            userLogin();
        }

        if(v == signUp){
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

}
