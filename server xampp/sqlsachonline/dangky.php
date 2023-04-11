<?php
// Kết nối đến cơ sở dữ liệu
$connect = mysqli_connect("localhost", "root", "", "app_qlbansach");

// Kiểm tra kết nối
if (!$connect) {
    die("Kết nối không thành công: " . mysqli_connect_error());
}

// Lấy thông tin từ request
$tenKH = $_POST["TenKH"];
$password = $_POST["Password"];
$phone = $_POST["Phone"];
$email = $_POST["Email"];

// Kiểm tra email đã được sử dụng chưa
$sql = "SELECT * FROM khachhang WHERE Email = '$email'";
$result = mysqli_query($connect, $sql);
if (mysqli_num_rows($result) > 0) {
    echo "Email đã được sử dụng!";
} else {
    // Thêm khách hàng vào cơ sở dữ liệu
    $sql = "INSERT INTO khachhang (TenKH, Password, Phone, Email) VALUES ('$tenKH', '$password', '$phone', '$email')";
    if (mysqli_query($connect, $sql)) {
        echo "Đăng ký thành công!";
    } else {
        echo "Đăng ký không thành công: " . mysqli_error($connect);
    }
}

// Đóng kết nối
mysqli_close($connect);
?>
