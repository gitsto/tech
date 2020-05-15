(function(){
    'use strict';

   angular.module('app-message-form',[])
    .directive('appMessageForm',  appMessageFormDirective )
    .controller('MessageFormController', MessageFormController);

    MessageFormController.$inject = ['$scope','MessageService','UserService','$timeout'];
    function MessageFormController($scope,MessageService,UserService,$timeout) {

        $scope.msg = {};

        $scope.send = function(){
            MessageService.createMessage($scope.msg.text,$scope.msg.title).then(
                
                function( result ){
                    $scope.saved = true;
                    $scope.msg = {};
                    $timeout(function(){
                        $scope.saved = false;
                    },1000)
                }
            )
     
        }

        $scope.showForm = function(){
            return UserService.isActiveUser();
        }

    }

    appMessageFormDirective.$inject = [];
    function appMessageFormDirective(){
        return {
            restrict:'E',
            controller:'MessageFormController',
            templateUrl:'src/app-message-form/app-message-form.html',
            scope:true
        }
    }
})()