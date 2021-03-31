package com.example.calculation.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.calculation.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.api.LogDescriptor;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignIn extends AppCompatActivity {


    String mail , pass ;
    FirebaseAuth auth ;
    FirebaseFirestore firestore;
    ConstraintLayout constraintLayout ;
    ProgressBar progressBar ;
    EditText email , password ;
    Button signin_btn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        intiview();
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnClickSignIn();
            }
        });
    }


    private void intiview(){
        constraintLayout = findViewById(R.id.parent1);
        progressBar = findViewById(R.id.prog);
        signin_btn = findViewById(R.id.signIn_btn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        auth=FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    private void OnClickSignIn(){
        mail = email.getText().toString().trim();
        pass = password.getText().toString().trim();
        if (email.getText().toString().isEmpty())
            email.setError("Empty");
        else if (password.getText().toString().isEmpty())
            password.setError("Empty");
        else {
            progressBar.setVisibility(View.VISIBLE);
            constraintLayout.setVisibility(View.GONE);
            StartLogin(mail,pass);
        }
    }
    private void StartLogin(final String email , final String password){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                checkIsAdmin(authResult.getUser().getUid());
                Toast.makeText(SignIn.this, "Login Successfully..", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressBar.setVisibility(View.GONE);
                constraintLayout.setVisibility(View.VISIBLE);
                Toast.makeText(SignIn.this, R.string.failed, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void checkIsAdmin(String uid) {
        final DocumentReference documentReference = firestore.collection("Users").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.e("TAG","hhhhhhhhhh   "+documentSnapshot.getData());
                if (documentSnapshot.getString("isAdmin") != null ){
                    startActivity(new Intent(SignIn.this,AdminActivity.class));
                    finish();
                }else if (documentSnapshot.getString("isUser") != null ) {
                    startActivity(new Intent(SignIn.this,Home.class));
                    finish();
                }
            }
        });

    }

}