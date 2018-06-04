<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
	<div id="logo">
		<p style="vertical-align: center;">ChatBox</p>
	</div>
	<div id="login-in">
		<form action="/ChatBox/Connection" method="post">
			<input type="image" src="/WEB-INF/images/user_id.png" alt="avatar"
				id="user_avatar" /> <br> <label for="user_avatar">
				<p>User_Login</p>
				<p>User_name User_firstname</p>
			</label>
		</form>
		<form action="/ChatBox/Connection" method="post">
			<button type="submit" name="direction" value="disconnect">deconnexion</button>
		</form>
	</div>
</header>