// scafold.js
// scafolder outil qui va générer

/*
*
* 1. céer un tablea nommé [folders] contenant des strings
*
* concept-js
* concept-css
* components
* playground

* 2. créer une fonction nommée [createDir]
* recevant un parametre et affichant sa valeur en console
* 3. appliquer la fonction par iteration sur chaque element du tableau
* 
*/

// 1.
var folders = ["concept-js", "concept-css", "components", "playground"];
// 2. 
function createDir(p1) {
    console.log("creating: ", p1);
    // appel d'une api nodejs pour creer les dossier, ici
    require('fs').mkdirSync(p1);
}
// 3. ici la boucle est fonctionnelle (comme les lambda en java)
// tester dans la console avec node sc (et tabulation) 
// pour executer le fichier dans la console plutôt que dans un navigateur via une page html de test

folders.forEach(createDir);
