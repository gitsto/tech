(function () {
    'use strict';
    // Code protégé par l'IIFE

    angular.module('main', [])
        .directive('customInput', InputDrective);

    InputDrective.$inject = [] //??
    function InputDrective() {
        return { // recette de construction
            template:'<input placeholder="Custom">',
            //Mode d'usage : Element, Attribut [Commentaire HTML, ClassCSS] 
            resctrict:'E'
        }
    }
    //fin du code
})();