<?php include('index_header.php'); ?>
<body>
<?php
require "dbcon.php";

if (isset($_POST['register'])) {
    if (strlen($_POST['username']) >= 1 && strlen($_POST['password']) >= 1 && strlen($_POST['firstname']) >= 1 && strlen($_POST['lastname']) >= 1  && strlen($_POST['gender']) >= 1) {



$username = trim($_POST['username']);
$password = trim($_POST['password']);
$firstname = trim($_POST['firstname']);
$lastname = trim($_POST['lastname']);
$gender = trim($_POST['gender']);

$encriptacionSHA1 = sha1($password);

		
 $consulta = "insert into members (username,password,firstname,lastname,gender,image) values ('$username','$encriptacionSHA1','$firstname','$lastname','$gender','images/avatar.jpg')";

	$resultado = mysqli_query($conn,$consulta); 
	
	if ($resultado) {
	    	?> 
	    	<h3 class="ok">¡Te has inscrito correctamente!</h3>
           <?php
	    } else {
	    	?> 
	    	<h3 class="bad">¡Ups ha ocurrido un error!</h3>
           <?php
	    }
     
		
	 }  else {
	    	?> 
	    	<h3 class="bad">¡Por favor complete los campos!</h3>
           <?php
    }

}

?>

<script>
	alert('Registro satisfactorio, ingresa con tus credenciales');
	window.location = 'index.php';
</script>

</body>
</html>