<?php
require('connect.php');


$query = "SELECT * FROM SACH";
$data = mysqli_query($connect, $query);
$result = array();
while ($row = mysqli_fetch_assoc($data))
{
    $result[] = ($row);
    //
}
if(!empty($result))
{
    $arr = [
        'success' =>true,
        'message' => "thanh cong",
        'arraylist' => $result
    ];
}
else
{
    $arr = [
        'success' =>false,
        'message' => "khon thanh cong",
        'arraylist' => $result
    ];
}
print_r((json_encode($arr)))
?>