(function(){
    'use strict';

    angular.module('app-header',[])
    .directive('appHeader',  appHeaderDirective )
    .controller('HeaderController', HeaderController);

    HeaderController.$inject = ['$scope','UserService'];
    function HeaderController($scope,UserService) {

        $scope.user = {};

        $scope.login = function(){
            UserService.login($scope.user.email);
        }

        $scope.logout = function(){
            UserService.logout();
        }

        $scope.showForm = function(){
            return !UserService.isActiveUser();
        }
    }

    appHeaderDirective.$inject = [];
    function appHeaderDirective(){
        return {
            restrict:'E',
            templateUrl:'src/app-header/app-header.html'
        }
    }

})()