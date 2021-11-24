<?php
session_start();
if (!isset($_SESSION['id'])){
header('location:index.php');
}
$session_id = $_SESSION['id'];
$firstname = $_SESSION['firstname'];
$lastname = $_SESSION['lastname'];
$image = $_SESSION['image'];
?>	