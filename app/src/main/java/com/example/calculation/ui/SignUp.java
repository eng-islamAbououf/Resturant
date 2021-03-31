package com.example.calculation.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.calculation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    String mail , pass ;
    ConstraintLayout constraintLayout ;
    ProgressBar progressBar ;
    EditText email , password , name , phone ;
    Button signUp_btn ;
    FirebaseAuth auth ;
    FirebaseFirestore firebaseFirestore ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        intiview();
        signUp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickSignUp();
            }
        });
    }

//**************************************************************************************************
    // for initialize el view
    private void intiview(){
        constraintLayout = findViewById(R.id.parent2);
        progressBar = findViewById(R.id.prog1);
        signUp_btn = findViewById(R.id.signUp_btn);
        email = findViewById(R.id.email1);
        password = findViewById(R.id.password1);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    //***********************************************************************************************
    // check fadi wla la2
    private void OnClickSignUp(){

        if (email.getText().toString().isEmpty()||password.getText().toString().isEmpty()||
                phone.getText().toString().isEmpty()||name.getText().toString().isEmpty()) {
            Toast.makeText(SignUp.this, "Check ur data", Toast.LENGTH_SHORT).show();
        }
        else {
            progressBar.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.GONE);
            mail=email.getText().toString().trim();
            pass = password.getText()
                    .toString().trim();
            startSignUp(mail,pass);
        }
    }
    //************************************************************************************************
    // create account in firebase
    protected void startSignUp(String mail , final String  password){
        auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUp.this, "Successfully....", Toast.LENGTH_SHORT).show();
                    createUser();
                    startActivity(new Intent(SignUp.this, Home.class));
                    finish();
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    constraintLayout.setVisibility(View.VISIBLE);
                    email.setText("");
                    Toast.makeText(SignUp.this, R.string.taken, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
//******************************************************************************************************
    // create users
    protected void createUser(){
        FirebaseUser firebaseUser = auth.getCurrentUser();
        DocumentReference documentReference = firebaseFirestore.collection("Users").document(firebaseUser.getUid());
        Map<String , Object> userInfo = new HashMap<>();
        userInfo.put("FullName",name.getText().toString().trim());
        userInfo.put("UserEmail",email.getText().toString().trim());
        userInfo.put("phone",phone.getText().toString().trim());
        userInfo.put("UserPassword",password.getText().toString().trim());
        userInfo.put("isUser","1");
        documentReference.set(userInfo);
    }
}