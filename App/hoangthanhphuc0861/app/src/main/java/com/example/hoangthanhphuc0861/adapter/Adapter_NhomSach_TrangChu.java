package com.example.hoangthanhphuc0861.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangthanhphuc0861.Activity.Sach_Activity;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.model.NHOMSACH;
import com.example.hoangthanhphuc0861.model.SERVER;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_NhomSach_TrangChu extends RecyclerView.Adapter<KHUNGNHIN2> {

    Context context;
    ArrayList<NHOMSACH> data;

    public Adapter_NhomSach_TrangChu(Context context, ArrayList<NHOMSACH> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public KHUNGNHIN2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sachtrangchu,null);
        return new KHUNGNHIN2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN2 holder, int position) {
    NHOMSACH cd = data.get(position);
    //chua gan du lieu cho hinh

        Picasso.get().load(SERVER.imgsach + cd.getPiture()).into(holder.imgnhomsach);
        holder.tvnhomsach.setText(cd.getLoai());


    //click item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context, Sach_Activity.class);
                intent.putExtra("MaNsach", cd.getMaNsach());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


}
//layout_chude
class KHUNGNHIN2 extends RecyclerView.ViewHolder {
    ImageView imgnhomsach;
    TextView tvnhomsach;

    public KHUNGNHIN2(@NonNull View itemView) {
        super(itemView);
        imgnhomsach = itemView.findViewById(R.id.imgnhomsach);
        tvnhomsach = itemView.findViewById(R.id.tvnhomsach);

    }
}
