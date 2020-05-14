(function () {
    'use strict';
    // Code protégé par l'IIFE

    angular.module('main', [])
        .filter('uppercase', upperCaseFilter);

    upperCaseFilter.$inject = []; //??
    function upperCaseFilter() {

        return function(input){
            return angular.uppercase(input)
        }

    }

    //fin du code
})();