<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<nav id="menu">
    <span id="men_liens">
        <div id="acceuil_men">
            <a href="Index.html" target="_self">Acceuil</a>
        </div>
        <div id="produits_men">
            <a href="Index.html" target="_self">Produits</a>
        </div>
        <div id="apropos_men">
            <a href="Index.html" target="_self">A Propos</a>
        </div>
    </span>
    <span id="men_action">
        <div id="recherche">
            <form action="commerce.php" method="post" style="vertical-align:middle;">
                <span>
                    <input type="text" placeholder="Rechercher" name="search_word" id="enter_search"/>
                    <button type="submit"><img src="images_site/loupe.png" alt="loupe" name="loupe" id="loupe"/></button>
                </span>
            </form>
        </div>
        <div id="panier">
            <form action="commerce.php" method="post" style="vertical-align:middle;">
                <span>
                    <button type="submit" value="panier" name="direction"><img src="images_site/panier.png" alt="panier" id="panier_but"/></button>
                </span>
            </form>
        </div>
    </span>
</nav>