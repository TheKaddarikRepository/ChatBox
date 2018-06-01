<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div class="register">
    <form action="commerce.php" method="post" enctype="multipart/form-data"> 
        <div class="register-client">
            <h3>INFORMATION PERSONNELLE</h3>
            <div>
                <span>Prenom<label>*</label></span>
                <input type="text" name="prenom" placeholder="votre prénom" required>
                <?php if (isset($errors['noFirstName'])) echo '<strong>' . $errors['noFirstName'] . '</strong>';?> 
            </div>
            <br>
            <div>
                <span>Nom<label>*</label></span>
                <input type="text" name="nom" placeholder="votre nom de famille" required>
                <?php if (isset($errors['noName'])) echo '<strong>' . $errors['noName'] . '</strong>';?> 
            </div>
            <br>
            <div>
                <span>Email<label>*</label></span>
                <input type="email" name="e_mail" placeholder="xxxxx.xxxx@xxxx.xx" required>
                <?php if (isset($errors['noEmail'])) echo '<strong>' . $errors['noEmail'] . '</strong>';?> 
            </div>
            <br>
            
        </div>
        <div class="register-login">
            <h3>LOGIN INFORMATION</h3>
            <div>
                <span>Login pseudo<label>*</label></span>
                <input type="text" name="pseudo" placeholder="votre pseudo de session" required>
                <?php if (isset($errors['noPseudo'])) echo '<strong>' . $errors['noPseudo'] . '</strong>';?> 
            </div>
            <br>
            <div>
                <span>Password<label>*</label></span>
                <input type="password" name="pswd1" id="pswd1" placeholder="> 8 charactères (1 lettre, 1 chifre, 1 (!@#$%))" pattern="[0-9A-Za-z!@#$%]{8,}" required>
                <?php if (isset($errors['noPswd'])) echo '<strong>' . $errors['noPswd'] . '</strong>';?>
            </div>
            <br>
            <div>
                <span>Confirm Password<label>*</label></span>
                <input type="password" name="pswd2" id="pswd2" onfocusout="comparePswds(document.getElementById('pswd1').value);" required>
            </div>
            <br>
            <div>
                <span>image avatar<label>*</label></span>
                <input type="file" name="monfichier" /><?php if (isset($errors['noFile'])) echo '<strong>' . $errors['noFile'] . '</strong>';?><br /> 
            </div>
            <br>
        </div>
        <div class="register-button">
            <input type="submit" name ="direction" value="register-in">
            <p><?php if (isset($errors['noAccount'])) echo '<strong>' . $errors['noAccount'] . '</strong>';?></p>
        </div>
    </form>
</div>