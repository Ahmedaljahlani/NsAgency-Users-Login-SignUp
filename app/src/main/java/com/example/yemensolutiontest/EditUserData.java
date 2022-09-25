package com.example.yemensolutiontest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class EditUserData extends AppCompatActivity {

    Button editUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_profile);

        editUserData=findViewById(R.id.edit_btn);

        editUserData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EditUserData.this,EditUserDetails.class);
                startActivity(intent);
            }
        });
    }
}