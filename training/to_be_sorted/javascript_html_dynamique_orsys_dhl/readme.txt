pour les tests avec ajax, un serveur wamp �tait dispo; 
il faut aller placer les fichiers dans les dossiers de wamp avec la meme arborescence que les urls (voir code)
afin que les sources soient plac�es dans un m�me "domain" d'exection


attention aux erreurs d'import et d'utilisation des m�thodes dans <script>
ne pas faire 
<script src="../fichiers.js" >
		// le code ajout� ici ne fonctionnera pas, il faut refaire plus loin un appel avec <script>...code...</script>
</script>