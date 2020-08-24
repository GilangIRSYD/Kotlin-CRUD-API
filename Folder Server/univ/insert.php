<?php 

    require("connection.php");
    include("functions.php");

    if (!empty($_POST['name']) &&  !empty($_POST['noHp']) &&  !empty($_POST['address'])) {
        $name   = $_POST['name'];
        $noHp   = $_POST['noHp'];
        $address= $_POST['address'];
        $query  = "INSERT INTO mahasiswa(name,noHp,address) VALUES('$name','$noHp','$address')";

        $result = mysqli_query($connect,$query);
        $error = mysqli_error($connect);

        if ($result) {
            set_response_notif(true,"Data Berhasil Ditambahkan");
        }else{
            set_response_notif(false,"Gagal menambahkan data : $error");
        }
    }else {
        set_response_notif(false,"Failed to insert data");
    }
?>