<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>



<div id="login_info">
    <h1>Login</h1>
    <p>Please enter your login informations.</p>
    <form action="commerce.php" method="post">
        <fieldset>
            <label for="login_name">Login</label><br> 
            <input type="text" name="my_login" id="login_name">
            <?php if (isset($errors['noLogin'])) echo '<strong>' . $errors['noLogin'] . '</strong>';?><br>
            <label for="password_text">Password</label><br>
            <input type="password" name="my_password" id="password_text">
            <?php if (isset($errors['noPswd'])) echo '<strong>' . $errors['noPswd'] . '</strong>';?><br>
            <button type="submit" value="loging-in" name="direction">LOGIN</button>
            <?php if (isset($errors['noAccount'])) echo '<strong>' . $errors['noAccount'] . '</strong>';?>
        </fieldset>
    </form>
    <p><a href="http://">Forgotten password</a></p>
</div>
<br>
<div id="reg_but">
    <h1>For new customer</h1>
    <p>please register a new account</p>
    <form action="/Register" method="get">
        <input type="submit" value="register" name="direction"></button>
    </form>
</div>




