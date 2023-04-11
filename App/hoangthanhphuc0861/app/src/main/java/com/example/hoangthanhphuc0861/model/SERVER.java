package com.example.hoangthanhphuc0861.model;

import java.util.List;

public class SERVER {

    public static String serverip ="http://192.168.201.238/";
    public static String nhomsach =serverip+"sqlsachonline/nhomsach.php";

    public static String sach =serverip+"sqlsachonline/sach.php";
    public static String sachtheoloai =serverip+"sqlsachonline/sachtheoloai.php";
    public static String chitietsach =serverip+"sqlsachonline/chitietsach.php";

    public static String imgsach =serverip+"sqlsachonline/imgsach/";
    public static String slide =serverip+"sqlsachonline/slide/";

    public static String timkiem =serverip+"sqlsachonline/timkiem.php";

    public static String dangky =serverip+"sqlsachonline/dangky.php";
    public static String dangnhap =serverip+"sqlsachonline/login.php";

    public static List<GioHang> manggiohang;
}
