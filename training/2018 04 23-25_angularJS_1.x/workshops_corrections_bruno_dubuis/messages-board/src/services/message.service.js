(function () {
    'use strict';

    angular.module('message.service', [])
        .service('MessageService', MessageService);

    MessageService.$inject = ['$http', 'UserService','$interval'];
    function MessageService($http, UserService,$interval) {

        var messages = this.messages = [];

        this.createMessage = function (text, title) {
            var user = UserService.getCurrentUser();

            
            if (!user) {
                return false;
            }

            var data = { text: text, title: title, user: user }

            return $http.post('http://192.168.19.100:5050/messages', data)
                .then(processResult)
                .then(function(){return true});

            function processResult(response){   
                messages.push(response.data)
            }
        }


        this.getMessages = function () {
            //Promise
            $http.get('http://192.168.19.100:5050/messages')
                .then(function (response) {
                    angular.merge(messages, response.data);
                })
        }

        // polling
        $interval(this.getMessages.bind(this),1500)
        
    }

})()


var config = {
    headers :{
        'Content-Type': 'application/json'
    }
}