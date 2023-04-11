package com.example.hoangthanhphuc0861.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangthanhphuc0861.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class fragmenta extends Fragment {
    RecyclerView recyclerView;
    Context context;



    ViewFlipper viewflipper;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmenta_layout,container,false);
       return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewflipper = view.findViewById(R.id.viewflipper);

        LoadViewPlipper();


    }

    void LoadViewPlipper(){
        ///khai bao mang quan cao
        ArrayList<String> mangquancao = new ArrayList<>();
        //nap du lkieu cho mang quan cao lay tu server
        mangquancao.add("https://newshop.vn/public/uploads/options/sach_nuoi_day_con.jpg");



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


}


