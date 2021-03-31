package com.example.calculation.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.calculation.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class SplashScreen extends AppCompatActivity {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        splash();
    }

    protected void splash(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkUser();
            }
        },3000);
    }

    protected void checkUser(){

        if (auth.getCurrentUser()!=null){

            checkIsAdmin(auth.getUid());
        }
        else {
            startActivity(new Intent(SplashScreen.this ,MainActivity.class));
            finish();
        }
    }

    private void checkIsAdmin(String uid) {
        final DocumentReference documentReference = firestore.collection("Users").document(uid);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.e("TAG", "hhhhhhhhhh   " + documentSnapshot.getData());
                if (documentSnapshot.getString("isAdmin") != null) {
                    startActivity(new Intent(SplashScreen.this, AdminActivity.class));
                    finish();
                } else if (documentSnapshot.getString("isUser") != null) {
                    startActivity(new Intent(SplashScreen.this, Home.class));
                    finish();
                }
            }
        });
    }
}