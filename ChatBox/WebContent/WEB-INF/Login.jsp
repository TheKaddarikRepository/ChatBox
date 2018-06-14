<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div id="login_info">
	<h1>Login</h1>
	<p>Please enter your login informations.</p>
	<form action="Connection" method="post">
		<fieldset>
			<label for="login_name">Login</label><br> <input type="text"
				name="my_login" id="login_name">
			<c:if test="${login_error!=null }">
				<c:out value="${login_error.getMessage()}" />
			</c:if>
			<br> <label for="password_text">Password</label><br> <input
				type="password" name="my_password" id="password_text">
			<c:if test="${password_error!=null }">
				<c:out value="${password_error.getMessage()}" />
			</c:if>
			<br>
			<button type="submit" value="loging-in" name="direction">LOGIN</button>
			<c:if test="${account_error!=null }">
				<c:out value="${account_error.getMessage()}" />
			</c:if>
			<br>
		</fieldset>
	</form>
	<p>
		<a href="http://">Forgotten password</a>
	</p>
</div>
<br>
<div id="reg_but">
	<h1>For new customer</h1>
	<p>please register a new account</p>
	<form action="/Register" method="get">
		<input type="submit" value="register" name="direction">
		</button>
	</form>
</div>




