package com.example.hoangthanhphuc0861.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.hoangthanhphuc0861.Activity.MainActivity_Allnhomsach;
import com.example.hoangthanhphuc0861.Activity.MainActivity_Allsach;
import com.example.hoangthanhphuc0861.Activity.MainActivity_GioHang;
import com.example.hoangthanhphuc0861.Activity.Search_MainActivity;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.adapter.Adapter_NhomSach_TrangChu;
import com.example.hoangthanhphuc0861.adapter.Adapter_Sach;
import com.example.hoangthanhphuc0861.model.NHOMSACH;
import com.example.hoangthanhphuc0861.model.SACH;
import com.example.hoangthanhphuc0861.model.SERVER;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Toolbar androidxtoolbar;
    DrawerLayout drawer_layout;
    RecyclerView rvChude, rvSach,rvSachtheochude;
    ViewFlipper viewflipper;
    FrameLayout trangchugiohang;
    NotificationBadge menu_sl;
    ImageView imgsearch;
    TextView allnhomsach,allsach;

    ArrayList<NHOMSACH> datanhomsach = new ArrayList<>();
    ArrayList<SACH> datasach = new ArrayList<>();


    Adapter_NhomSach_TrangChu adapter_nhomSachTrangChu;
    Adapter_Sach adapter_sach;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.homefragment,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        anhxa();

        loadchude();
        loadsach();
        LoadViewPlipper();


    }

    void LoadViewPlipper(){
        ///khai bao mang quan cao
        ArrayList<String> mangquancao = new ArrayList<>();
        //nap du lkieu cho mang quan cao lay tu server
        mangquancao.add("https://newshop.vn/public/uploads/options/sach_nuoi_day_con.jpg");
//        mangquancao.add(SERVER.slide+ "slide5.png");


        for(String slide : mangquancao)
        {
            ImageView hinh  = new ImageView(getContext());
            Picasso.get().load(slide).into(hinh);
            hinh.setScaleType(ImageView.ScaleType.FIT_XY);//slide gian vua du
            viewflipper.addView(hinh);
        }
        //tu dong chay
        viewflipper.setAutoStart(true);
        //thoi gian thay doi slide
        viewflipper.setFlipInterval(1000);
    }

    void loadchude()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

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

        adapter_nhomSachTrangChu = new Adapter_NhomSach_TrangChu(getContext(), datanhomsach);
        rvChude.setAdapter(adapter_nhomSachTrangChu);
        rvChude.setLayoutManager(new GridLayoutManager(getContext(),1, GridLayoutManager.HORIZONTAL,false));



    }

    void loadsach()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        Response.Listener<JSONArray> thanhcong = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                for(int i = 0 ; i<jsonArray.length();i++ )
                {


                    try {
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

                    } catch (JSONException e) {
                        Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
                adapter_sach.notifyDataSetChanged(); // cập nhật lại adapter
            }
        };
        Response.ErrorListener thatbai = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        };
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(SERVER.sach,thanhcong ,thatbai );
        requestQueue.add(jsonArrayRequest);
//        datachude.add(new CHUDE(R.drawable.icon_bookstore,"aaaa"));

        adapter_sach= new Adapter_Sach(getContext(), datasach);
        rvSach.setAdapter(adapter_sach);
        rvSach.setLayoutManager(new GridLayoutManager(getContext(),2));


    }
    void anhxa() {
        allnhomsach = view.findViewById(R.id.allnhomsach);
        allsach = view.findViewById(R.id.allsach);
        androidxtoolbar = view.findViewById(R.id.androidxtoolbar);

        rvChude = view.findViewById(R.id.rvChude);
        rvSach = view.findViewById(R.id.rvSach);

        viewflipper = view.findViewById(R.id.viewflipper);
        menu_sl = view.findViewById(R.id.menu_sl);

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


        //cick gio hàng//
        trangchugiohang = view.findViewById(R.id.trangchugiohang);
        trangchugiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getActivity(), MainActivity_GioHang.class);

                startActivity(giohang);
            }
        });

        //click tim kiem
        imgsearch = view.findViewById(R.id.imgsearch);
        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent timkiem = new Intent(getActivity(), Search_MainActivity.class);

                startActivity(timkiem);
            }
        });

        //clich xem tat ca nhom sach
        allnhomsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allnhomsach = new Intent(getActivity(), MainActivity_Allnhomsach.class);
                startActivity(allnhomsach);
            }
        });
        //clich xem tat ca sach
        allsach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent allsach = new Intent(getActivity(), MainActivity_Allsach.class);
                startActivity(allsach);
            }
        });


    }//ánh xạ

}
