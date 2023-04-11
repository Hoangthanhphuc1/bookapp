package com.example.hoangthanhphuc0861.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hoangthanhphuc0861.Interface.IImageClickListener;
import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.model.EventBus.TinhTongEvent;
import com.example.hoangthanhphuc0861.model.GioHang;
import com.example.hoangthanhphuc0861.model.NHOMSACH;
import com.example.hoangthanhphuc0861.model.SERVER;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Adapter_GioHoang extends RecyclerView.Adapter<KHUNGNHIN3>{
    Context context2;
    List<GioHang> data2;

    public Adapter_GioHoang(Context context2, List<GioHang> data2) {
        this.context2 = context2;
        this.data2 = data2;
    }

    @NonNull
    @Override
    public KHUNGNHIN3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context2).inflate(R.layout.layout_item_giohang,null);
        return new KHUNGNHIN3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KHUNGNHIN3 holder, int position) {
        GioHang gh = data2.get(position);
//////////--hinh sach--/////////////////////////////////////
        Picasso.get().load(SERVER.imgsach + gh.getHinhsach()).into(holder.hinhsachgiohang);

//////////--ten sach--/////////////////////////////////////
        holder.tensachgiohang.setText(gh.getTensach());

//////////--gia sach--/////////////////////////////////////
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//       holder.giasachgiohang.setText(decimalFormat.format((gh.getGiasach()))+"đ");
        long gia = gh.getSoluong() * gh.getGiasach();
        holder.giasachgiohang.setText(decimalFormat.format(gia)+ "đ");

///////////--so luong sach--////////////////////////////////////
        holder.soluong_giohang.setText(gh.getSoluong() + " ");

        holder.setListener(new IImageClickListener() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                //////////////////////////////////////////
                if(giatri == 1)
                {
                    if(data2.get(pos).getSoluong() > 1)
                    {
                        int soluongmoi = data2.get(pos).getSoluong() - 1;
                        data2.get(pos).setSoluong(soluongmoi);

                    holder.soluong_giohang.setText(data2.get(pos).getSoluong() + " ");

                    long gia = data2.get(pos).getSoluong() * data2.get(pos).getGiasach();
                    holder.giasachgiohang.setText(decimalFormat.format(gia)+ "đ");

                    EventBus.getDefault().postSticky(new TinhTongEvent());
                    }
                    else if(data2.get(pos).getSoluong() == 1 )
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có muốn xóa sản phẩm này khỏi giỏi hàng?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                SERVER.manggiohang.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvent());
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();

                    }
                }
                ////////////////////////////////////////////
                else if(giatri == 2)
                {
                    if(data2.get(pos).getSoluong() <10)
                    {
                        int soluongmoi = data2.get(pos).getSoluong() + 1;
                        data2.get(pos).setSoluong(soluongmoi);
                    }
                    holder.soluong_giohang.setText(data2.get(pos).getSoluong() + " ");

                    long gia = data2.get(pos).getSoluong() * data2.get(pos).getGiasach();
                    holder.giasachgiohang.setText(decimalFormat.format(gia)+ "đ");

                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return data2.size();

    }

}
class KHUNGNHIN3 extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView hinhsachgiohang,soluong_tru,soluong_cong,xoagiohang;
    TextView tensachgiohang,giasachgiohang,soluong_giohang;
    IImageClickListener listener;

    public KHUNGNHIN3(@NonNull View itemView) {
        super(itemView);
        hinhsachgiohang = itemView.findViewById(R.id.hinhsachgiohang);

        soluong_tru = itemView.findViewById(R.id.soluong_tru);
        soluong_cong = itemView.findViewById(R.id.soluong_cong);


        tensachgiohang = itemView.findViewById(R.id.tensachgiohang);
        giasachgiohang = itemView.findViewById(R.id.giasachgiohang);
        soluong_giohang = itemView.findViewById(R.id.soluong_giohang);

        //event click
        soluong_tru.setOnClickListener(this);
        soluong_cong.setOnClickListener(this);
    }

    public void setListener(IImageClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        /////
        if(view == soluong_tru)
        {
            //1 tru
            listener.onImageClick(view , getAdapterPosition(), 1);

        }
        else if(view == soluong_cong)
        {
            //2 cong
            listener.onImageClick(view , getAdapterPosition(), 2);
        }

    }
}