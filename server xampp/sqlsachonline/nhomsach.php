<?php
require('connect.php');

class NHOMSACH{
    function __construct($MaNsach, $tensach, $loai,$piture){
        $this->MaNsach = $MaNsach;
        $this->tensach = $tensach;
        $this->loai = $loai;
        $this->piture = $piture;
    }
}
$query = "SELECT * FROM NHOMSACH";

$data = mysqli_query($connect, $query);
$arraylist = array();

while ($row=mysqli_fetch_assoc($data))
{
    array_push($arraylist, new NHOMSACH($row["MaNsach"], $row["Tensach"], $row["Loai"], $row["Piture"]));
}
header("Content-type: application/json");
echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);

mysqli_close($connect);

?>

