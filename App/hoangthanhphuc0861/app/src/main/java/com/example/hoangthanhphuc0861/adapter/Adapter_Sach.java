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

import com.example.hoangthanhphuc0861.Activity.MainActivity_ChitietSach;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.model.SACH;
import com.example.hoangthanhphuc0861.model.SERVER;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Sach extends RecyclerView.Adapter<KHUNGNHIN>{

    Context context1;
    ArrayList<SACH> data1;

    public Adapter_Sach(Context context1, ArrayList<SACH> data1) {
        this.context1 = context1;
        this.data1 = data1;
    }

    @NonNull
    @Override
    public KHUNGNHIN onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context1).inflate(R.layout.layout_sach,null);
        return new KHUNGNHIN(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN holder, int position) {
    SACH sach = data1.get(position);

        Picasso.get().load(SERVER.imgsach + sach.getPicture()).into(holder.imghinhsach);
      //  holder.imghinhsach.setImageResource(sach.getHinhsach());
        holder.tvtensach.setText(sach.getTensach());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        holder.tvgiasach.setText(sach.getDongia());
        holder.tvgiasach.setText(decimalFormat.format(Double.parseDouble(sach.getDongia()))+"đ");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(context1, MainActivity_ChitietSach.class);
                intent.putExtra("chitiet", sach);

                context1.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data1.size();
    }
}
class KHUNGNHIN extends RecyclerView.ViewHolder {
    ImageView imghinhsach;
    TextView tvtensach, tvgiasach;

    public KHUNGNHIN(@NonNull View itemView) {
        super(itemView);
        imghinhsach = itemView.findViewById(R.id.imghinhsach);
        tvtensach = itemView.findViewById(R.id.tvtensach);
        tvgiasach = itemView.findViewById(R.id.tvgiasach);

    }
}
