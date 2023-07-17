package com.cowsoran.ekiapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.style.UpdateAppearance;
import android.view.View;
import android.widget.ImageView;

import com.cowsoran.ekiapi.activity.CreateActivity;
import com.cowsoran.ekiapi.activity.DeleteActivity;
import com.cowsoran.ekiapi.activity.ReadActivity;
import com.cowsoran.ekiapi.activity.UpdateActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView ivRead;
    private ImageView ivCreate;
    private ImageView ivUpdate;
    private ImageView ivDelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivRead = findViewById(R.id.ivReadMain);
        ivRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ReadActivity.class);
                startActivity(i);
                finish();
            }
        });

        ivCreate = findViewById(R.id.ivCreateMain);
        ivCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(i);
                finish();
            }
        });

        ivUpdate = findViewById(R.id.ivUpdateMain);
        ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, UpdateActivity.class);
                startActivity(i);
                finish();
            }
        });

        ivDelete = findViewById(R.id.ivDeleteMain);
        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DeleteActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}