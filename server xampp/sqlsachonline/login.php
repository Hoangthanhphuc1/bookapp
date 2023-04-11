<?php
// Kết nối đến cơ sở dữ liệu
$connect = mysqli_connect("localhost", "root", "", "app_qlbansach");

// Kiểm tra kết nối
if (!$connect) {
    die("Kết nối không thành công: " . mysqli_connect_error());
}

// Lấy thông tin từ request
$email = $_POST["Email"];
$password = $_POST["Password"];

// Kiểm tra thông tin đăng nhập
$sql = "SELECT * FROM khachhang WHERE Email = '$email' AND Password = '$password'";
$result = mysqli_query($connect, $sql);

if (mysqli_num_rows($result) > 0) {
    echo "Đăng nhập thành công!";
} else {
    echo "Đăng nhập không thành công! Vui lòng kiểm tra lại email và mật khẩu.";
}

// Đóng kết nối
mysqli_close($connect);
?>
