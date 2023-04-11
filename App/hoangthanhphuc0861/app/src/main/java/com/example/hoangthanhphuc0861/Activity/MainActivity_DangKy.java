package com.example.hoangthanhphuc0861.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.model.SERVER;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity_DangKy extends AppCompatActivity {
    private EditText editTextTenKH;
    private EditText editTextPassword;
    private EditText editTextPhone;
    private EditText editTextEmail;
    private Button buttonDangKy;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_ky);
        editTextTenKH = findViewById(R.id.editTextTenKH);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextEmail = findViewById(R.id.editTextEmail);

        buttonDangKy = findViewById(R.id.buttonDangKy);
        buttonDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKy();
            }

private void dangKy() {

    StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.dangky,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("dangky", response);
                    if (response.trim().equals("Đăng ký thành công!")) {
                        // Nếu đăng nhập thành công, chuyển đến activity tiếp theo
                        Toast.makeText(MainActivity_DangKy.this, "Dang ky thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity_DangKy.this, MainActivity_DangNhap.class);
                    } else {
                        Toast.makeText(MainActivity_DangKy.this, "ko thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Lỗi kết nối!", Toast.LENGTH_SHORT).show();
                }
            }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("TenKH", editTextTenKH.getText().toString());
            params.put("Password", editTextPassword.getText().toString());
            params.put("Phone", editTextPhone.getText().toString());
            params.put("Email", editTextEmail.getText().toString());
            return params;
        }
    };
    Volley.newRequestQueue(MainActivity_DangKy.this).add(stringRequest);
}


        });



    }
}