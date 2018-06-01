<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<header>
    <div id="logo">
        <p style="vertical-align:center;">ChatBox</p>
    </div>
    <div id="login-in">
        <form action="commerce.php" method="post">
            <input type="image" src="
            <?php if (isset($_SESSION['avatar'])){echo $_SESSION['avatar'];}else{echo "images_site/compte.png";}            
            ?>
            " alt="avatar" name="direction" value="login" id="compte"/>
            <br>
            <label for="compte">
            <?php if (isset($_SESSION['pseudo'])){echo $_SESSION['pseudo'];}else{echo "login";}            
            ?>
            </label>
        </form>
        <form action="commerce.php" method="post">
            <button type="submit" name="direction" value="disconnect">deconnexion</button>
        </form>
    </div>
</header>