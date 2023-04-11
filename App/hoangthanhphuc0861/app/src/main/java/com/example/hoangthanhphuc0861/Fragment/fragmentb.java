package com.example.hoangthanhphuc0861.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangthanhphuc0861.R;

import java.util.ArrayList;

public class fragmentb extends Fragment {
    RecyclerView recyclerViewb;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentb_layout,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();
        recyclerViewb = view.findViewById(R.id.recycleviewb);

//        data.add(new ROOM(R.drawable.r1,"Phong suite family", "day la phong vip"));
//
//        data.add(new ROOM(R.drawable.diningroom,"Phong dining", "day la phong vip"));
//
//        data.add(new ROOM(R.drawable.livingroom,"Phong living family", "day la phong vip"));
//
//        data.add(new ROOM(R.drawable.accessories,"Phong accessories family", "day la phong vip"));
//
//        data.add(new ROOM(R.drawable.r2,"Phong suite family", "day la phong vip"));
//
//        data.add(new ROOM(R.drawable.r3,"Phong dining", "day la phong vip"));
//
//        data.add(new ROOM(R.drawable.r4,"Phong living family", "day la phong vip"));
//
//        data.add(new ROOM(R.drawable.bedroom,"Phong accessories family", "day la phong vip"));
//
//        roomAdapter = new ROOM_ADAPTER(context,data);
//        recyclerViewb.setAdapter(roomAdapter);
//
//        //định dang recycleView
//
//        // chiều ngang
////        recyclerViewb.setLayoutManager(new GridLayoutManager(context,RecyclerView.HORIZONTAL,false));
//        //recyclerViewb.setLayoutManager(new GridLayoutManager(context, DividerItemDecoration.HORIZONTAL,false));
//
//        // chiều dọc
////        recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL, false));
//
//        recyclerViewb.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL, false));
//
//        recyclerViewb.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }
    }


