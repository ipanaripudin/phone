package com.cowsoran.ekiapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.cowsoran.ekiapi.MainActivity;
import com.cowsoran.ekiapi.R;
import com.cowsoran.ekiapi.api.ApiClient;
import com.cowsoran.ekiapi.api.PhoneService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteActivity extends AppCompatActivity {

    private EditText edtId;
    private Button btnDelete;
    private PhoneService phoneService;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        ivBack = findViewById(R.id.ivBackDelete);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DeleteActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        edtId = findViewById(R.id.edtDeleteId);

        phoneService = ApiClient.getClient().create(PhoneService.class);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePhone();
            }
        });

    }


    private void deletePhone() {
        int phoneId = Integer.parseInt(edtId.getText().toString().trim());

        Call<Void> call = phoneService.deletePhone(phoneId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DeleteActivity.this, "Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DeleteActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}