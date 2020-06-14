
function afficherSurfaceCercle() {
	var r = parseFloat(prompt("Saisir votre rayon : "));
	return r*r*3.14
}

afficherSurfaceCercleAvecRayon = function (rayon) {
	var r = parseFloat(rayon);
	surface = r*r*3.14;
	//alert("Pour un rayon de " + rayon + " la surface est " + surface);
	console.log("dans la console : " + surface);
	return surface;
}

afficherInformation = function() {
	var ua = navigator.userAgent;
	document.write("<h1>" + ua + "</h1>");
}

afficherInformations = function(p) {
	for (var key in p) {
		var prop = key + " -> " + p[key];
		console.log(prop);
		document.write(prop + "<br>");
	}
}


prefixTab = function(tab) {
	
	//console.log("prefixTab debut");
	
	for (var i=0; i<tab.length; i++) {
		var indiceLettre_A = 65;
		var prefixLettre = String.fromCharCode(indiceLettre_A + i);
		console.log("prefixLettre: " + prefixLettre);
		var newValeur = prefixLettre + " " + tab[i];
		// replacer la valeur courante par la nouvelle
		tab[i] = newValeur;
	}
	
	console.log(tab);
	document.write(tab);
	//console.log("prefixTab fin");
	return tab;
}	
