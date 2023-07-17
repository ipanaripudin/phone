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
import com.cowsoran.ekiapi.model.Phone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateActivity extends AppCompatActivity {

    private EditText edtId;
    private EditText edtPhoneName;
    private EditText edtPrice;
    private Button btnUpdate;

    private ImageView ivBack;
    private PhoneService phoneService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        ivBack = findViewById(R.id.ivBackUpdate);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        edtId = findViewById(R.id.edtUpdateId);
        edtPhoneName = findViewById(R.id.edtUpdateName);
        edtPrice = findViewById(R.id.edtUpdatePrice);
        phoneService = ApiClient.getClient().create(PhoneService.class);

        btnUpdate  = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePhone();
            }
        });

    }

    private void updatePhone() {
        int phoneId = Integer.parseInt(edtId.getText().toString().trim());
        String phoneName = edtPhoneName.getText().toString().trim();
        int price = Integer.parseInt(edtPrice.getText().toString().trim());

        Phone phone = new Phone();
        phone.setPhoneName(phoneName);
        phone.setPrice(price);

        Call<Phone> call = phoneService.updatePhone(phoneId,phone);
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(UpdateActivity.this, "Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}