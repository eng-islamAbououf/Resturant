package com.example.calculation.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.calculation.Adapters.MyFragAdapter;
import com.example.calculation.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class Home extends AppCompatActivity {
    ViewPager viewPager;
    MyFragAdapter myFragmentAdabter;
    TabLayout tabLayout;
    FirebaseAuth auth ;
    Button logout_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intiView();
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                startActivity(new Intent(Home.this,MainActivity.class));
                finish();
            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //Toast.makeText(HomeActivity.this,""+tab.getText(),Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });

    }



    private void intiView(){
        auth = FirebaseAuth.getInstance();
        logout_btn = findViewById(R.id.logout);
        viewPager = findViewById(R.id.container);
        tabLayout = findViewById(R.id.tabLayout);
        myFragmentAdabter=new MyFragAdapter(getSupportFragmentManager(),1,tabLayout.getTabCount());
        viewPager.setAdapter(myFragmentAdabter);
    }
}