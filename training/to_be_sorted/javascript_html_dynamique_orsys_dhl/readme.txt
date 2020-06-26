pour les tests avec ajax, un serveur wamp était dispo; 
il faut aller placer les fichiers dans les dossiers de wamp avec la meme arborescence que les urls (voir code)
afin que les sources soient placées dans un même "domain" d'exection


attention aux erreurs d'import et d'utilisation des méthodes dans <script>
ne pas faire 
<script src="../fichiers.js" >
		// le code ajouté ici ne fonctionnera pas, il faut refaire plus loin un appel avec <script>...code...</script>
</script>