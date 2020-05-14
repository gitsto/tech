// scaffod.js

/**
 * 1. Créer un tableau nommé [folders] contenant les string
 * 
 * concept-js
 * concept-css
 * components
 * playground
 * 
 * 
 * 2. Créer une fonction nommée  [createDir]
 * Recevant un paramètre et affichant sa valeur dans la console
 * 
 * 3. Appliquer la fonction par itération usr chaque élémént du tableau
 * 
 */

 var folders = [
     'concept-js',
     'concept-css',
     'components',
     'playground'
 ];

 function createDir( name ) {
     console.log( 'creating', name );
     require('fs').mkdirSync(name);
 }



 folders.forEach(createDir);