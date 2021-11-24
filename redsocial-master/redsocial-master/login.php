<?php
require "dbcon.php";

session_start();
if($_POST){
$username = $_POST['username'];
$password = $_POST['password'];

$sql = "select * from members where username = '$username'";
$resultado = $conn->query($sql);
$num = $resultado->num_rows;

if($num>0){
			$row = $resultado->fetch_assoc();
			$password_bd = $row['password'];
			
			$pass_c = sha1($password);
			
			if($password_bd == $pass_c){
				
$_SESSION['id'] = $row['member_id'];
$_SESSION['firstname'] = $row['firstname'];
$_SESSION['lastname'] = $row['lastname'];
$_SESSION['image'] = $row['image'];

 header('location:home.php'); 
} else {
			
			echo "La contraseña no coincide";
			
			}
			
			
			} else {
			echo "NO existe usuario";
		}
		
		
		
	}
?>

				<form  method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>"> 
                                <h3>Ingresar</h3> 
								<hr>
                                <p> 
                                    <label for="username" class="uname" data-icon="u" > Tu usuario </label>
                                    <input id="username" name="username" required="required" type="text"/>
                                </p>
                                <p> 
                                    <label for="password" class="youpasswd" data-icon="p"> Tu contraseña </label>
                                    <input id="password" name="password" required="required" type="password"/> 
                                </p>
                                <button type="submit" class="btn btn-primary">Login</button></div>
                                <p class="change_link">
									Aun no eres miembro ?
									<a href="#toregister" class="to_register">Regístrate</a>
								</p>
                            </form>