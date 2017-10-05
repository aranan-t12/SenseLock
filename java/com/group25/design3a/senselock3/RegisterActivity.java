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
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    //  databaseHelper registerDB;

    //User details and register button
    private Button registerButton;
    private EditText userEmail;
    private EditText userPassword;
    private EditText userFirstName;
    private EditText userLastName;

    //Progress bar dialog
    private ProgressDialog progressDialog;

    //Firebase authentication
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // registerDB = new databaseHelper(this);

        //Initialize firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();

        //Initialize progress dialog
        progressDialog = new ProgressDialog(this);

        //Initialize reset button
        registerButton = (Button) findViewById(R.id.registerButton);

        //Initialize user input details
        userEmail = (EditText) findViewById(R.id.userEmail);
        userPassword = (EditText) findViewById(R.id.password);
        userFirstName = (EditText) findViewById(R.id.userFirstName);
        userLastName = (EditText) findViewById(R.id.userLastName);

        //Initialize register button
        registerButton.setOnClickListener(this);

    }

    private void userRegister (){
        //Details to be gathered from register page
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String firstName = userFirstName.getText().toString().trim();
        String lastName = userLastName.getText().toString().trim();


        //Check if fields are empty
        if(TextUtils.isEmpty(email)){
            //If email field is empty
            Toast.makeText(this, "One or more fields are empty!",Toast.LENGTH_SHORT).show();
            return; //return; will stop the function from executing commands

        }
        if(TextUtils.isEmpty(password)){
            //If password field is empty
            Toast.makeText(this, "One or more fields are empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(firstName)){
            //If first name field is empty
            Toast.makeText(this, "One or more fields are empty!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(lastName)){
            //If last name field is empty
            Toast.makeText(this, "One or more fields are empty!",Toast.LENGTH_SHORT).show();
        }


        //IF REGISTRATION IS OKAY AND ALL FIELDS ARE FILLED OUT...!
        //Progress dialog will indicate progress

        progressDialog.setMessage("Registering account, please wait");
        progressDialog.show();

        //Registers account based on email and password
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            //If the account has been created and logged in
                            Toast.makeText(RegisterActivity.this, "Account registration successful!", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(RegisterActivity.this, "Failed to register account!", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v == registerButton){
            //To stop progress dialog
            userRegister();
            startActivity(new Intent(this, Register_LogIn.class));

        }
    }
}