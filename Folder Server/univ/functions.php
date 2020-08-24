<?php 

    function set_response($isSuccess, $message, $data){
        $result     = array (
            'isSuccess' => $isSuccess,
            'message' => $message,
            'data' => $data
        );

        echo json_encode($result);
    }

    function set_response_notif($isSuccess,$message){
        $result      = array(
            'isSuccess' => $isSuccess,
            'message' => $message
        );           

        echo json_encode($result);
    }
?>