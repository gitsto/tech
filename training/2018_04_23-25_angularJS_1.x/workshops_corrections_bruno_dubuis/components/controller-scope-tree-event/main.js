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

        $rootScope.$on('GROOT',function(evt,payload){
            console.log(evt,payload)
            $rootScope.rootValue = payload;
        })

    }

    AppController.$inject = ['$scope']; // injection
    function AppController($scope) {

        $scope.value = 100;

        $scope.sendEvent = function(){
            $scope.$emit('GROOT','I am Groot!');
        }
    }

    MainController.$inject = ['$scope']; // injection
    function MainController($scope) {

        $scope.stringValue = 'Hello';
        $scope.objectValue = { text: 'Hello' };
    }

    //fin du code
})();