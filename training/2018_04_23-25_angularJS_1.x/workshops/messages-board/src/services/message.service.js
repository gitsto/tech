(function () {
    'use strict';

    angular.module('message.service', [])
        .service('MessageService', MessageService);

    MessageService.$inject = ['$http', 'UserService'];
    function MessageService($http, UserService) {

        var messages = this.messages = [];

        this.createMessage = function (text, title) {
            var user = UserService.getCurrentUser();

            
            if (!user) {
                return false;
            }

            var data = { text: text, title: title, user: user }

            $http.post('http://192.168.19.100:5050/messages', data)
                .then(processResult.bind(this))

            function processResult(response){   
                this.messages.push(response.data)
            }
        }


        this.getMessages = function () {
            //Promise
            $http.get('http://192.168.19.100:5050/messages')
                .then(function (response) {
                    angular.merge(messages, response.data);
                })
        }

        this.getMessages();
    }

})()


var config = {
    headers :{
        'Content-Type': 'application/json'
    }
}