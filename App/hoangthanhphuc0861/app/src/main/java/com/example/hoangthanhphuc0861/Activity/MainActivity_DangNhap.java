package com.example.hoangthanhphuc0861.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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


public class MainActivity_DangNhap extends AppCompatActivity {

    private EditText edittext_tendangnhap;
    private EditText edittext_matkhau;
    private Button btnDangNhap, dangky;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dang_nhap);



        edittext_tendangnhap = findViewById(R.id.edittext_tendangnhap);
        edittext_matkhau = findViewById(R.id.edittext_matkhau);
        dangky = findViewById(R.id.dangky);

        btnDangNhap = findViewById(R.id.button_dangnhap);
        dangky = findViewById(R.id.dangky);
        dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_DangNhap.this, MainActivity_DangKy.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity_DangNhap.this, TrangChu_Activity.class);
//                startActivity(intent);
                dangnhap();
            }

private void dangnhap() {
    String email = edittext_tendangnhap.getText().toString();
    String password = edittext_matkhau.getText().toString();

    // Gửi yêu cầu đăng nhập đến server bằng thư viện volley
    RequestQueue queue = Volley.newRequestQueue(MainActivity_DangNhap.this);

    StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.dangnhap,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("dangnhap", response);
                    if (response.trim().equals("Đăng nhập thành công!")) {
                        // Nếu đăng nhập thành công, chuyển đến activity tiếp theo
                        Toast.makeText(MainActivity_DangNhap.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity_DangNhap.this, TrangChu_Activity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                     Toast.makeText(MainActivity_DangNhap.this, "Đăng nhập không thành công!" +
                             " Vui lòng kiểm tra lại email và mật khẩu", Toast.LENGTH_SHORT).show();
                     }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity_DangNhap.this, "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
    ) {
        @Override
        protected Map<String, String> getParams() {
            // Truyền tham số Email và Password vào yêu cầu đăng nhập
            Map<String, String> params = new HashMap<>();
            params.put("Email", email);
            params.put("Password", password);
            return params;
        }
    };
    // Thêm yêu cầu vào hàng đợi Volley để thực thi
    Volley.newRequestQueue(MainActivity_DangNhap.this).add(stringRequest);
}

        });
    }
}