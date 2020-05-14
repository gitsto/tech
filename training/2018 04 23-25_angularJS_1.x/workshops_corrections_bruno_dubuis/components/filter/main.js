(function () {
    'use strict';
    // Code protégé par l'IIFE

    angular.module('main', [])
        .filter('checked', checkedFilter);

    checkedFilter.$inject = []; //??
    function checkedFilter() {

        return function(input){
            return input + 'CHECKED'
        };

    }

    //fin du code
})();