<?php 

    require("connection.php");
    include("functions.php");

    if (!empty($_POST['id'])) {
        $id     = $_POST['id'];

        $query  = "DELETE FROM mahasiswa WHERE id='$id'";
        $delete     = mysqli_query($connect,$query);

        if ($delete) {
            set_response_notif(true,"Data telah terhapus");
        }else{
            set_response_notif(false,"Gagal menghapus Data");
        }
    }else {
        set_response_notif(false,"id tidak ditemukan");
    }
?>