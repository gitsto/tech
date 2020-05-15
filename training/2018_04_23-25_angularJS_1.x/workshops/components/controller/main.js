(function () {
    'use strict';
    // Code protégé par l'IIFE

    angular.module('main', [])
        .controller('AppController', AppController);

    AppController.$inject = ['$scope']; // injection
    function AppController($scope) {
        console.log( Math.random() );

        $scope.value = 100
    }

    //fin du code
})();