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

public class CreateActivity extends AppCompatActivity {

    private EditText edtPhoneName;
    private EditText edtPrice;
    private Button btnPost;
    private PhoneService phoneService;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        ivBack = findViewById(R.id.ivBackCreate);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        edtPhoneName = findViewById(R.id.edtPostName);
        edtPrice = findViewById(R.id.edtPostPrice);
        phoneService = ApiClient.getClient().create(PhoneService.class);

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPhone();
            }
        });

    }

    private void createPhone() {
        String phoneName = edtPhoneName.getText().toString().trim();
        int  phonePrice = Integer.parseInt(edtPrice.getText().toString().trim());

        Phone phone = new Phone();
        phone.setPhoneName(phoneName);
        phone.setPrice(phonePrice);

        Call<Phone> call = phoneService.createPhone(phone);
        call.enqueue(new Callback<Phone>() {
            @Override
            public void onResponse(Call<Phone> call, Response<Phone> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(CreateActivity.this, "Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CreateActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Phone> call, Throwable t) {
                Toast.makeText(CreateActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}