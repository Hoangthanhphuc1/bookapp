<?php
require('connect.php');

if(isset($_POST['search'])) {
    $search = $_POST['search'];

    $query = "SELECT * FROM SACH WHERE Tensach LIKE '%".$search."%'";
    $data = mysqli_query($connect, $query);

    if($data) {
        $result = array();
        while ($row = mysqli_fetch_assoc($data))
        {
            $result[] = $row;
        }
        
        if(!empty($result))
        {
            $arr = [
                'success' => true,
                'message' => "Thanh cong",
                'arraylist' => $result
            ];
        }
        else
        {
            $arr = [
                'success' => false,
                'message' => "khong tim thay ban ghi nao phu hop voi tim kiem",
                'arraylist' => $result
            ];
        }
    } else {
        $arr = [
            'success' => false,
            'message' => "loi truy van",
            'arraylist' => []
        ];
    }
} else {
    $arr = [
        'success' => false,
        'message' => "tham so search khong duoc dinh nghia",
        'arraylist' => []
    ];
}

echo json_encode($arr);
?>
