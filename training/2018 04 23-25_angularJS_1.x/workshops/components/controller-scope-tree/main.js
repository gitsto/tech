(function () {
    'use strict';
    // Code protégé par l'IIFE

    angular.module('main', [])
        .controller('AppController', AppController)
        .controller('MainController', MainController)
        .run(runModule)

    runModule.$inject = ['$rootScope']; // injection
    function runModule($rootScope) {
        $rootScope.rootValue = 'Je s\'appelle root';
    }

    AppController.$inject = ['$scope']; // injection
    function AppController($scope) {

        $scope.value = 100;
    }

    MainController.$inject = ['$scope']; // injection
    function MainController($scope) {

        $scope.stringValue = 'Hello';
        $scope.objectValue = { text: 'Hello' };
    }

    //fin du code
})();