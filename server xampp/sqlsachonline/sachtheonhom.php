<?php
require('connect.php');

 $machude = $_GET['macd'];

class SACH{
    function __construct($masach,$tensachh,$hinhsach,$macd, $dongia, $mota, $ngaycapnhat){
        $this->masach = $masach;
        $this->tensachh = $tensachh;
        $this->hinhsach = $hinhsach;
        $this->macd = $macd;
        $this->dongia = $dongia;
        $this->mota = $mota;
        $this->ngaycapnhat = $ngaycapnhat;
    }
}

if (empty($machude)) {
    $query = "SELECT * FROM SACH";
}
else {
     $query = " SELECT * FROM SACH WHERE Machude='$machude' ";
 }

$data = mysqli_query($connect, $query);
$arraylist = array();

while ($row = mysqli_fetch_assoc($data)) {
    array_push($arraylist, new SACH($row["Masach"],$row["Tensach"], $row["Hinhminhhoa"],$row["Machude"],$row["Dongia"],$row["Mota"],$row["Ngaycapnhat"]));
}

header("Content-type: application/json");
echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

mysqli_close($connect);
?>
