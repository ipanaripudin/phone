package com.cowsoran.ekiapi.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cowsoran.ekiapi.MainActivity;
import com.cowsoran.ekiapi.R;
import com.cowsoran.ekiapi.api.ApiClient;
import com.cowsoran.ekiapi.api.PhoneService;
import com.cowsoran.ekiapi.model.Phone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadActivity extends AppCompatActivity {

    private EditText edtId;
    private Button btnGet;
    private TextView tvResult;
    private PhoneService phoneService;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        ivBack = findViewById(R.id.ivBackGet);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ReadActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        edtId = findViewById(R.id.edtGetId);
        tvResult = findViewById(R.id.tvGetResult);
        phoneService = ApiClient.getClient().create(PhoneService.class);

        btnGet = findViewById(R.id.btnGet);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int phoneId = Integer.parseInt(edtId.getText().toString().trim());
                getPhone(phoneId);
            }
        });

    }

    private void getPhone(int phoneId) {
        Call<Phone> call =  phoneService.getPhoneById(phoneId);
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if (response.isSuccessful()) {
                    Phone phone = response.body();
                    if (phone != null) {
                        StringBuilder result = new StringBuilder();
                        result.append("ID: ").append(phone.getId())
                                .append("\nPhone Name: ").append(phone.getPhoneName())
                                .append("\nPrice: ").append(phone.getPrice());
                        tvResult.setText(result.toString());
                    } else {
                        tvResult.setText("Phone not found");
                    }
                } else {
                    tvResult.setText("Failed to get phone. Error code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                tvResult.setText("Error : " + t.getMessage());
            }
        });
    }

}