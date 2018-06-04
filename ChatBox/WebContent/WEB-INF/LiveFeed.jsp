<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="discussion_frame">
	<ul>
		<li style="width: 100%">
			<div class="msj macro">
				<div class="avatar">
					<img class="img-circle" style="width: 100%;" src="'+ me.avatar +'" />
				</div>
				<div class="text">
					<p>'+ text +'</p>
					<p>
						<small>'+date+'</small>
					</p>
				</div>
			</div>
		</li>
		<li style="width: 100%;">
			<div class="msj-rta macro">
				<div class="text">
					<p>'+text+'</p>
					<p>
						<small>'+date+'</small>
					</p>
				</div>
				<div class="avatar" style="padding: 0px 0px 0px 10px;">
					<img class="img-circle" style="width: 100%;" src="'+you.avatar+'" />
				</div>
			</div>
		</li>
	</ul>
	<div>
		<div class="msj-rta macro">
			<div class="text" style="background: whitesmoke;">
				<input class="mytext" placeholder="Type a message" />
			</div>

		</div>
		<div style="padding: 10px;">
			<span class="glyphicon"></span>
		</div>
	</div>
</div>