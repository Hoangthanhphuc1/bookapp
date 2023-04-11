package com.example.hoangthanhphuc0861.model;

import java.io.Serializable;

public class SACH implements Serializable {
    String Masach, Tensach, MaNsach, Dongia,Soluonghang,Filename,Picture;


    public SACH(String masach, String tensach, String maNsach, String dongia, String soluonghang, String filename, String picture) {
        Masach = masach;
        Tensach = tensach;
        MaNsach = maNsach;
        Dongia = dongia;
        Soluonghang = soluonghang;
        Filename = filename;
        Picture = picture;
    }

    public String getMasach() {
        return Masach;
    }

    public void setMasach(String masach) {
        Masach = masach;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public String getMaNsach() {
        return MaNsach;
    }

    public void setMaNsach(String maNsach) {
        MaNsach = maNsach;
    }

    public String getDongia() {
        return Dongia;
    }

    public void setDongia(String dongia) {
        Dongia = dongia;
    }

    public String getSoluonghang() {
        return Soluonghang;
    }

    public void setSoluonghang(String soluonghang) {
        Soluonghang = soluonghang;
    }

    public String getFilename() {
        return Filename;
    }

    public void setFilename(String filename) {
        Filename = filename;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }
}



