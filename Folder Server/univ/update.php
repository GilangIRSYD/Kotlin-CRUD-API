<?php 

    require("connection.php");
    include("functions.php");

    if (!empty($_POST['name']) && !empty($_POST['noHp']) && !empty($_POST['address']) && !empty($_POST['id'])) {
        
        $id         = $_POST['id'];
        $name       = $_POST['name'];
        $noHp       = $_POST['noHp'];
        $address    = $_POST['address'];

        $query  = "UPDATE mahasiswa SET 
                name='$name',
                noHp='$noHp',
                address='$address' WHERE id='$id'";

        $update = mysqli_query($connect,$query);

        if ($update) {
            set_response_notif(true,"Data berhasil diubah");
        }else {
            set_response_notif(false,"gagal mengubah Data");
        }
    }else {
        set_response_notif(false,"Lengkapi data yang ingin diubah");
    }
?>