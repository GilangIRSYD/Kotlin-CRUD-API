<?php 

    require("connection.php");
    include("functions.php");

    if (!empty($_GET['id'])) {
        $id     = $_GET['id'];
        $query  = "SELECT * FROM mahasiswa WHERE id='$id'";
    }else{

        $query  = "SELECT * FROM mahasiswa";
    }

    $reslut    =  mysqli_query($connect,$query);
    $data      =  array();

    if (mysqli_num_rows($reslut) > 0) {
        
        while ($row = mysqli_fetch_assoc($reslut)) {
            $data[] = $row;
        }

        set_response(true,"Data is found", $data);

        
    }else {

        set_response(false,"Data not found",$data);
    }
?>