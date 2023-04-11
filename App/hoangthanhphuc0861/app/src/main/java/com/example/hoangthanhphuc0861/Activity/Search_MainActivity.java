package com.example.hoangthanhphuc0861.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.adapter.Adapter_Sach;
import com.example.hoangthanhphuc0861.adapter.Adapter_Search;
import com.example.hoangthanhphuc0861.model.SACH;
import com.example.hoangthanhphuc0861.model.SERVER;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search_MainActivity extends AppCompatActivity {
    RecyclerView rvSearch;
    EditText edtsearch;
    Button btnsearch;

    Adapter_Search adapter_search;
    ArrayList<SACH> datasach = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        try {
            rvSearch.setLayoutManager(new LinearLayoutManager(this));
            adapter_search = new Adapter_Search(this, datasach);
            rvSearch.setAdapter(adapter_search);
        } catch (Exception e) {
            Log.e("Error", "Error setting up RecyclerView", e);
        }



        anhxa();
    }

    private void anhxa() {
        rvSearch = findViewById(R.id.recycleview_search);
        edtsearch = findViewById(R.id.edtsearch);
        btnsearch = findViewById(R.id.btnsearch);
        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aakeyword = edtsearch.getText().toString().trim();
                if (!TextUtils.isEmpty(aakeyword)) {
                    search(aakeyword);
                } else {
                    Toast.makeText(Search_MainActivity.this, "Vui lòng nhập thông tin tìm kiếm.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void search(final String keyword) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER.timkiem, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RESPONSE", response); // in giá trị response ra Logcat để kiểm tra
                Toast.makeText(Search_MainActivity.this, "Tìm kiếm thành công", Toast.LENGTH_SHORT).show();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    //datasach.clear(); Sau khi gọi phương thức này, danh sách datasach sẽ trở thành rỗng.
                    // Việc xóa các phần tử này là để chuẩn bị cho việc thêm các phần tử mới
                    // lấy được từ server sau khi tìm kiếm.
                    datasach.clear();
//Vòng lặp for được sử dụng để duyệt qua các phần tử trong một mảng JSONArray.
// Biến đếm i được khởi tạo với giá trị 0 và tăng dần cho đến khi nó đạt đến chiều dài của mảng JSONArray
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        datasach.add(new SACH(
                                jsonObject.getString("Masach"),
                                jsonObject.getString("Tensach"),
                                jsonObject.getString("MaNsach"),
                                jsonObject.getString("Dongia"),
                                jsonObject.getString("Soluonghang"),
                                jsonObject.getString("Filename"),
                                jsonObject.getString("Picture")
                        ));
                    }
                    //adapter_sach.notifyDataSetChanged() được sử dụng để thông báo cho
                    // Adapter biết rằng dữ liệu đã thay đổi và cần cập nhật lại giao diện người dùng
                    adapter_search.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Search_MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            //Đoạn mã này được viết trong một lớp đang kế thừa từ lớp Volley's Request.
            // Nó tạo ra một Map có kiểu String, String để chứa các tham số của một yêu cầu HTTP.
            // Để làm điều đó, nó sử dụng lớp HashMap và sau đó đặt giá trị của search là keyword vào map đó.
            // Biến keyword được giả định là một biến đã được khởi tạo và có giá trị của từ khóa tìm kiếm.
            // Cuối cùng, nó trả về map đó để được sử dụng bởi Volley để thực hiện yêu cầu HTTP.
            // được sử dụng để tạo ra một bộ tham số trong các yêu cầu HTTP, như GET hoặc POST,
            // để gửi dữ liệu từ thiết bị của bạn đến một máy chủ web hoặc API và nhận lại kết quả.
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("search", keyword);
                return params;
            }
        };


        requestQueue.add(stringRequest);

    }
}

