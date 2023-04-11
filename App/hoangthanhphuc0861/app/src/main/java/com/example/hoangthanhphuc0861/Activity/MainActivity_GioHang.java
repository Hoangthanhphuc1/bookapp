package com.example.hoangthanhphuc0861.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hoangthanhphuc0861.R;
import com.example.hoangthanhphuc0861.adapter.Adapter_GioHoang;
import com.example.hoangthanhphuc0861.model.EventBus.TinhTongEvent;
import com.example.hoangthanhphuc0861.model.SERVER;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

public class MainActivity_GioHang extends AppCompatActivity {
    RecyclerView rvGiohang;
    TextView tienthanhtoan,giohangtrong;
    Button muahang;
    Adapter_GioHoang adapter_gioHoang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_giohang);



        anhxa();
        initControl();
        tinhtongtien();
    }

    private void tinhtongtien() {
        long tongtien = 0;
        //vòng lặp for được sử dụng để duyệt qua tất cả các mặt hàng trong giỏ hàng.
        // Mỗi mặt hàng được lấy ra từ danh sách SERVER.manggiohang,
        // và tiền của mỗi mặt hàng được tính bằng cách lấy giá của sách nhân với số lượng của sách đó.
        // Tiền của mỗi mặt hàng sẽ được thêm vào biến tongtien.
        for(int i = 0 ; i < SERVER.manggiohang.size(); i++)
        {
            tongtien = tongtien + (SERVER.manggiohang.get(i).getGiasach() * SERVER.manggiohang.get(i).getSoluong());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tienthanhtoan.setText(decimalFormat.format(tongtien)+ "đ");
    }

    private void initControl() {


        rvGiohang.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvGiohang.setLayoutManager(layoutManager);
        if(SERVER.manggiohang.size() == 0 )
        {
        //    giohangtrong.setVisibility(View.VISIBLE);
            Toast.makeText(this, "giỏ hàng trống", Toast.LENGTH_SHORT).show();
        }
        else
        {
            adapter_gioHoang = new Adapter_GioHoang(getApplicationContext(), SERVER.manggiohang);
            rvGiohang.setAdapter(adapter_gioHoang);
        }
    }





    private void anhxa() {
        rvGiohang = findViewById(R.id.rvGiohang);
        tienthanhtoan = findViewById(R.id.tienthanhtoan);
        muahang = findViewById(R.id.muahang);
      //  giohangtrong = findViewById(R.id.giohangtrong);

    }

    @Override
   // Phương thức onStart() được ghi đè để đăng ký lắng nghe các sự kiện từ EventBus.
    // Trong trường hợp này, đối tượng EventBus được lấy thông qua phương thức getDefault()
    // và được đăng ký để lắng nghe các sự kiện.
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }
//Phương thức onStop() được ghi đè để hủy đăng ký các sự kiện từ EventBus khi Activity bị dừng lại.
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    //nếu sự kiện TinhTongEvent được gửi, phương thức tinhtongtien() sẽ được gọi
    // để tính tổng tiền và cập nhật giao diện người dùng.
    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event)
    {
        if(event != null )
        {
            tinhtongtien();
        }
    }
}