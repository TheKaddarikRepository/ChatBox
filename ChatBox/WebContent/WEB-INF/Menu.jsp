<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<nav id="menu">
	<span id="menu_collectifs">
		<ul>
			<li><span id="sub_public">LOUNGES</span>
				<ul>
					<li>lounge-1</li>
					<li>lounge-2</li>
				</ul></li>
			<li><span id="sub_private">GROUPS</span>
				<ul>
					<li>
						<div>
							<form action="search_groups" method="post"
								style="vertical-align: middle;">
								<span> <label style="display: none;">text to
										search in groups names</label> <input type="text" name="in_grp_name" />
									<label style="display: none">execute search on groups</label>
									<button type="submit">
										<img alt="search" src="/WEB-INF/images/loupe.png">
									</button>
								</span>
							</form>
						</div>
					</li>
					<li><a href="">group-1</a></li>
					<li><a href="">group-2</a></li>
				</ul>
		</ul>
	</span> <span id="men_search">
		<form action="search_all" method="post"
			style="vertical-align: middle;">
			<span> <input type="text" placeholder="Rechercher"
				name="search_word" id="enter_search" />
				<button type="submit">
					<img src="images_site/loupe.png" alt="loupe" name="loupe"
						id="loupe" />
				</button>
			</span>
		</form>
	</span> <span id="men_friends">
		<div id="friends">
			<form action="search_friend" method="post"
				style="vertical-align: middle;">
				<span> <label>text to search in users names</label> <input
					type="text" name="in_frd_name" />
					<button type="submit" value="panier" name="direction">
						<img src="/WEB-INF/images/panier.png" alt="panier" id="panier_but" />
					</button>
				</span>
			</form>
		</div>
	</span>
</nav>