<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="register">
	<form action="commerce.php" method="post" enctype="multipart/form-data">
		<div class="register-client">
			<h3>INFORMATION PERSONNELLE</h3>
			<div>
				<span>Prenom<label>*</label></span> <input type="text" name="prenom"
					placeholder="votre prénom" required>
				<c:if test="${firstname_error!=null }">
					<c:out value="${firstname_error.getMessage()}" />
				</c:if>
				<br>
			</div>
			<br>
			<div>
				<span>Nom<label>*</label></span> <input type="text" name="nom"
					placeholder="votre nom de famille" required>
				<c:if test="${name_error!=null }">
					<c:out value="${name_error.getMessage()}" />
				</c:if>
				<br>
			</div>
			<br>
			<div>
				<span>Email<label>*</label></span> <input type="email" name="e_mail"
					placeholder="xxxxx.xxxx@xxxx.xx" required>
				<c:if test="${email_error!=null }">
					<c:out value="${email_error.getMessage()}" />
				</c:if>
				<br>
			</div>
			<br>

		</div>
		<div class="register-login">
			<h3>LOGIN INFORMATION</h3>
			<div>
				<span>Login pseudo<label>*</label></span> <input type="text"
					name="pseudo" placeholder="votre pseudo de session" required>
				<c:if test="${login_error!=null }">
					<c:out value="${login_error.getMessage()}" />
				</c:if>
				<br>
			</div>
			<br>
			<div>
				<span>Password<label>*</label></span> <input type="password"
					name="pswd1" id="pswd1"
					placeholder="> 8 charactères (1 lettre, 1 chifre, 1 (!@#$%))"
					pattern="[0-9A-Za-z!@#$%]{8,}" required>
				<c:if test="${password_error!=null }">
					<c:out value="${password_error.getMessage()}" />
				</c:if>
				<br>
			</div>
			<br>
			<div>
				<span>Confirm Password<label>*</label></span> <input type="password"
					name="pswd2" id="pswd2" pattern="[0-9A-Za-z!@#$%]{8,}"
					onfocusout="comparePswds(document.getElementById('pswd1').value);"
					required>
			</div>
			<br>
			<div>
				<span>image avatar<label>*</label></span> <input type="file"
					name="monfichier" />
				<c:if test="${avatar_error!=null }">
					<c:out value="${avatar_error.getMessage()}" />
				</c:if>
				<br>
			</div>
			<br>
		</div>
		<div class="register-button">
			<input type="submit" name="direction" value="register-in">
			<p>
				<c:if test="${account_error!=null }">
					<c:out value="${account_error.getMessage()}" />
				</c:if>
			</p>
		</div>
	</form>
</div>