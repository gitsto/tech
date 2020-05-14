(function(){
    'use strict';

   angular.module('app-message-form',[])
    .directive('appMessageForm',  appMessageFormDirective )
    .controller('MessageFormController', MessageFormController);

    MessageFormController.$inject = ['$scope','MessageService','UserService'];
    function MessageFormController($scope,MessageService,UserService) {

        $scope.msg = {};

        $scope.send = function(){
            MessageService.createMessage($scope.msg.text,$scope.msg.title);
            $scope.msg = {};
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