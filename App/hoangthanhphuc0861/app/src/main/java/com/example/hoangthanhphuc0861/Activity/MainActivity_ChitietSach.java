package com.example.hoangthanhphuc0861.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hoangthanhphuc0861.R;

import com.example.hoangthanhphuc0861.model.GioHang;
import com.example.hoangthanhphuc0861.model.SACH;
import com.example.hoangthanhphuc0861.model.SERVER;
import com.nex3z.notificationbadge.NotificationBadge;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class MainActivity_ChitietSach extends AppCompatActivity {

    TextView tenchitiet,giachitiet;
    ImageView imgchitiet;
    RecyclerView rvchitiet;
    Spinner spinner;
    Button btnthem;
    NotificationBadge badge;
    SACH sach;
    FrameLayout framelayoutgiohang;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chitiet_sach);

//        tenchitiet = findViewById(R.id.tenchitiet);
//        giachitiet = findViewById(R.id.giachitiet);
        imgchitiet = findViewById(R.id.imgchitiet);
        spinner = findViewById(R.id.spinner);
        btnthem = findViewById(R.id.btnthem);
        badge = findViewById(R.id.menu_sl);

        if(SERVER.manggiohang != null)
        {
            int totalItem = 0;
            // vòng lặp for được sử dụng để xem tổng số lượng mặt hàng trong giỏ hàng bằng cách
            // cộng dồn số lượng từng mặt hàng. Biến totalItem sẽ được cập nhật với giá trị này.
            for(int i = 0 ; i < SERVER.manggiohang.size(); i++)
            {
                totalItem = totalItem + SERVER.manggiohang.get(i).getSoluong();
            }
            //phương thức setText() được gọi trên đối tượng badge để cập nhật số lượng mặt hàng
            // trên biểu tượng "giỏ hàng" của ứng dụng. String.valueOf(totalItem) được sử dụng
            // để chuyển đổi giá trị của totalItem thành chuỗi để có thể đặt làm nội dung của badge.
            badge.setText(String.valueOf(totalItem));
        }

        //cick gio hàng//
        framelayoutgiohang = findViewById(R.id.trangchugiohang);
        framelayoutgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(MainActivity_ChitietSach.this, MainActivity_GioHang.class);

                startActivity(giohang);
            }
        });


      initData();
      initControl();

    }

    private void initControl() {

        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themGioHang();
            }
        });

    }

 private void themGioHang() {
    if(SERVER.manggiohang.size() > 0)
    {

        boolean flag = false;
        // phương thức xử lý sự kiện khi người dùng chọn một lựa chọn từ Spinner.
        //số lương sách được chọn
        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());

        for (int i = 0 ; i<SERVER.manggiohang.size(); i++)
        {
            //vòng lặp for duyệt qua danh sách các mặt hàng trong giỏ hàng (SERVER.manggiohang)
            //nếu mã sách trong giỏ hàn trùng với mã sách trong SACH model
            if(SERVER.manggiohang.get(i).getIdsach() == Integer.parseInt(sach.getMasach()))
            {
                //số lượng mặt hàng được cập nhật bằng cách thêm giá trị soluong vào số lượng hiện tại,
                // và giá tiền của mặt hàng được tính toán lại dựa trên giá tiền của sách và số lượng mới.

                SERVER.manggiohang.get(i).setSoluong(soluong + SERVER.manggiohang.get(i).getSoluong());
                //getDongia() được gọi để lấy giá tiền của sách đó dưới dạng chuỗi.
                // Giá trị này được chuyển đổi sang kiểu số nguyên dạng long bằng phương thức Long.parseLong().
                //Sau đó, getDongia() được nhân với số lượng của mặt hàng trong giỏ hàng được lấy từ
                // danh sách SERVER.manggiohang bằng cách gọi phương thức getSoluong().
                long gia = Long.parseLong(sach.getDongia()) * SERVER.manggiohang.get(i).getSoluong();
                //Kết quả của phép tính này được lưu trữ trong biến "gia", và sau đó được gán vào

                SERVER.manggiohang.get(i).setGiasach(gia);
                // thuộc tính giasach của mặt hàng tương ứng trong danh sách SERVER.manggiohang
                // bằng cách gọi phương thức setGiasach(). Do đó, giá trị giá tiền của mặt hàng này được
                // cập nhật và sẽ được hiển thị trong giỏ hàng.


                //biến flag là một biến boolean được sử dụng để xác định xem một mặt hàng đã được cập nhật
                // trong giỏ hàng hay chưa.
                //Nếu mã sách của mặt hàng trong giỏ hàng trùng với mã sách của mặt hàng được chọn từ
                // danh sách sách, thì flag được đặt lại thành true
                //Việc này cho phép chương trình biết được xem mặt hàng đã được cập nhật hay chưa
                // và có thể thực hiện các thao tác cần thiết,
                flag = true;

            }
        }

        //Nếu flag vẫn là false, điều đó có nghĩa là mã sách của mặt hàng được chọn không trùng với
        // bất kỳ mã sách nào trong giỏ hàng. Trong trường hợp này, một mặt hàng mới được tạo ra với
        // thông tin tương ứng và được thêm vào danh sách giỏ hàng bằng cách sử dụng
        // phương thức add() của SERVER.manggiohang.
        if(flag == false)
        {
            GioHang gioHang = new GioHang();
            long gia = Long.parseLong(sach.getDongia()) * gioHang.getSoluong();
            gioHang.setGiasach(gia);
            gioHang.setSoluong(soluong);
            gioHang.setIdsach(Integer.parseInt(sach.getMasach()));
            gioHang.setTensach(sach.getTensach());
            gioHang.setHinhsach(sach.getPicture());
            SERVER.manggiohang.add(gioHang);
        }
    }

    //Nếu flag đã được đặt lại thành true, điều đó có nghĩa là mặt hàng đã tồn tại trong giỏ hàng và
    // chỉ cần cập nhật thông tin của mặt hàng này. Điều này được thực hiện bằng cách lấy số lượng mới từ
    // spinner, tính toán lại giá tiền và cập nhật thông tin của mặt hàng trong danh sách SERVER.manggiohang.
    else
    {
        int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
        long gia = Long.parseLong(sach.getDongia()) * soluong;
        GioHang gioHang = new GioHang();
        gioHang.setGiasach(gia);
        gioHang.setSoluong(soluong);
        gioHang.setIdsach(Integer.parseInt(sach.getMasach()));
        gioHang.setTensach(sach.getTensach());
        gioHang.setHinhsach(sach.getPicture());
        SERVER.manggiohang.add(gioHang);
    }

        int totalItem = 0;
        for(int i = 0 ; i < SERVER.manggiohang.size(); i++)
        {
            totalItem = totalItem + SERVER.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));



    }


    private void initData() {
        //hiển thị chi tiết sách
        sach  = (SACH) getIntent().getSerializableExtra("chitiet");
      //
      Picasso.get().load(SERVER.imgsach + sach.getPicture()).into(imgchitiet);
      //
       tenchitiet.setText(sach.getTensach());
       //
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
       giachitiet.setText(decimalFormat.format(Double.parseDouble(sach.getDongia()))+"đ");

       // chọn số lượng
       Integer [] so = new Integer[] {1,2,3,4,5,6,7,8,9,10};
       //sử dụng để tạo một đối tượng ArrayAdapter và gắn nó với một Spinner để hiển thị
        // danh sách các phần tử cho người dùng lựa chọn.
      ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
      spinner.setAdapter(adapterspin);



}

}