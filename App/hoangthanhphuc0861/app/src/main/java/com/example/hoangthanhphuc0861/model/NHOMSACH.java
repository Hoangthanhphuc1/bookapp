package com.example.hoangthanhphuc0861.model;

public class NHOMSACH {

    String MaNsach,tensach, loai,piture;

    public NHOMSACH(String maNsach, String tensach, String loai, String piture) {
        MaNsach = maNsach;
        this.tensach = tensach;
        this.loai = loai;
        this.piture = piture;
    }

    public String getMaNsach() {
        return MaNsach;
    }

    public void setMaNsach(String maNsach) {
        MaNsach = maNsach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getPiture() {
        return piture;
    }

    public void setPiture(String piture) {
        this.piture = piture;
    }
}


