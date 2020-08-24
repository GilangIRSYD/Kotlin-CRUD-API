<?php 

    $host       = "localhost";
    $username   = "root";
    $password   = "";
    $database   = "univ";

    $connect = mysqli_connect($host,$username,$password,$database);

    if (mysqli_connect_errno()) {
        echo "Failed to Connect database" ;
    }
    


?>