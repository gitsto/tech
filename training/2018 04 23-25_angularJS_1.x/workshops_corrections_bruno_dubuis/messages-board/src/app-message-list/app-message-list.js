(function(){
    'use strict';

    angular.module('app-message-list',[])
    .directive('appMessageList',  appMessageListDirective )
    .controller('MessageListController', MessageListController);

    MessageListController.$inject = ['$scope','MessageService'];
    function MessageListController($scope, MessageService) {

        console.log(MessageService)

        $scope.messages = MessageService.messages ;
        $scope.value = 789 ;


    }

    appMessageListDirective.$inject = [];
    function appMessageListDirective(){
        return {
            restrict:'E',
            controller:'MessageListController',
            scope:true,
            templateUrl:'src/app-message-list/app-message-list.html'
        }
    }

})()