<?php
require('connect.php');

$masach = $_GET['Masach'];

class SACH{
    function __construct($Masach,$Tensach,$MaNsach,$Dongia, $Soluonghang, $Filename, $Picture){
        $this->Masach = $Masach;
        $this->Tensach = $Tensach;
        $this->MaNsach = $MaNsach;
        $this->Dongia = $Dongia;
        $this->Soluonghang = $Soluonghang;
        $this->Filename = $Filename;
        $this->Picture = $Picture;
    }
}

if (empty($masach)) {
    $query = "SELECT * FROM SACH";
}
else {
     $query = " SELECT * FROM SACH WHERE Masach='$masach' ";
 }


$data = mysqli_query($connect, $query);
$arraylist = array();

while ($row = mysqli_fetch_assoc($data)) {
    array_push($arraylist, new SACH($row["Masach"],$row["Tensach"], $row["MaNsach"],$row["Dongia"],$row["Soluonghang"],$row["Filename"],$row["Picture"]));
}

header("Content-type: application/json");
echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

mysqli_close($connect);
?>
