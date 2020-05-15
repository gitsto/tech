(function () {
    'use strict';
    // Code protégé par l'IIFE

    var ButtonComponent = {
        template:'<button>Custom</button>'
    }

    angular.module('main', [])
        .component('customButton', ButtonComponent);


    //fin du code
})();