package com.example.yemensolutiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button signUp,EditUser,newDesign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUp=findViewById(R.id.signup_main_btn);
        EditUser=findViewById(R.id.edit_user_main_btn);
        newDesign=findViewById(R.id.new_design_btn);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignUpSample1Activity.class);
                startActivity(intent);
            }
        });

        EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,EditUserData.class);
                startActivity(intent);
            }
        });

        newDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,NewDesignActivity.class);
                startActivity(intent);
            }
        });

    }
}