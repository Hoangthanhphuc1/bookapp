package com.example.hoangthanhphuc0861.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangthanhphuc0861.Activity.Search_MainActivity;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.model.SACH;
import com.example.hoangthanhphuc0861.model.SERVER;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_Search extends RecyclerView.Adapter<Adapter_Search.ViewHolder> {
    private Context mContext;
    private ArrayList<SACH> mSachList;

    public Adapter_Search(Context context,ArrayList<SACH> sachList) {
        mContext = context;
        mSachList = sachList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sach,null);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SACH currentSach = mSachList.get(position);

        Picasso.get().load(SERVER.imgsach + currentSach.getPicture()).into(holder.imghinhsach);
        holder.tvtensach.setText(currentSach.getTensach());

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvgiasach.setText(decimalFormat.format(Double.parseDouble(currentSach.getDongia()))+"đ");

    }

    @Override
    public int getItemCount() {
        return mSachList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imghinhsach;
        TextView tvtensach, tvgiasach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhsach = itemView.findViewById(R.id.imghinhsach);
            tvtensach = itemView.findViewById(R.id.tvtensach);
            tvgiasach = itemView.findViewById(R.id.tvgiasach);
        }
    }
}

