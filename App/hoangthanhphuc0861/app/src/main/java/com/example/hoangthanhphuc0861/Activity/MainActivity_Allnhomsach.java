package com.example.hoangthanhphuc0861.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.adapter.Adapter_NhomSach_TrangChu;
import com.example.hoangthanhphuc0861.adapter.Adapter_Sach;
import com.example.hoangthanhphuc0861.model.NHOMSACH;
import com.example.hoangthanhphuc0861.model.SACH;
import com.example.hoangthanhphuc0861.model.SERVER;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity_Allnhomsach extends AppCompatActivity {
    RecyclerView rvallnhomsach;
    FrameLayout trangchugiohang;

    ArrayList<NHOMSACH> datanhomsach = new ArrayList<>();
    ArrayList<SACH> datasach = new ArrayList<>();
    NotificationBadge menu_sl;

    Adapter_NhomSach_TrangChu adapter_nhomSachTrangChu;
    Adapter_Sach adapter_sach;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_allnhomsach);

        menu_sl = findViewById(R.id.menu_sl);
        if( SERVER.manggiohang == null)
        {
            SERVER.manggiohang = new ArrayList<>();
        }
        else
        {
            int totalItem = 0;
            for(int i = 0 ; i < SERVER.manggiohang.size(); i++)
            {
                totalItem = totalItem + SERVER.manggiohang.get(i).getSoluong();
            }
            menu_sl.setText(String.valueOf(totalItem));
        }

        rvallnhomsach = findViewById(R.id.rvallnhomsach);
        trangchugiohang = findViewById(R.id.trangchugiohang);
        trangchugiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(MainActivity_Allnhomsach.this, MainActivity_GioHang.class);

                startActivity(giohang);
            }
        });
        loadchude();
    }

    void loadchude()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                for(int i = 0 ; i<jsonArray.length();i++ )
                {


                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        datanhomsach.add(new NHOMSACH(

                                jsonObject.getString("MaNsach"),
                                jsonObject.getString("tensach"),
                                jsonObject.getString("loai"),
                                jsonObject.getString("piture")

                        ));

                    } catch (JSONException e) {

                    }
                }
                adapter_nhomSachTrangChu.notifyDataSetChanged(); // cập nhật lại adapter
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {



            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.nhomsach,thanhcong ,thatbai );
        requestQueue.add(jsonArrayRequest);
//        datachude.add(new CHUDE(R.drawable.icon_bookstore,"aaaa"));

        adapter_nhomSachTrangChu = new Adapter_NhomSach_TrangChu(this, datanhomsach);
        rvallnhomsach.setAdapter(adapter_nhomSachTrangChu);
        rvallnhomsach.setLayoutManager(new GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false));



    }
}