package com.example.hoangthanhphuc0861.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.hoangthanhphuc0861.Activity.MainActivity_DangKy;
import com.example.hoangthanhphuc0861.Activity.MainActivity_DangNhap;
import com.example.hoangthanhphuc0861.R;

public class AccountFragment extends Fragment {

    private EditText edittext_tendangnhap;
    private EditText edittext_matkhau;
    private Button btnDangNhap, dangky;
    private LinearLayout btn_account;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_account,container,false);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       anhxa();


    }

    private void anhxa() {
        btn_account = view.findViewById(R.id.btn_account);

        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MainActivity_DangNhap.class);
                startActivity(intent);
            }
        });
    }
}