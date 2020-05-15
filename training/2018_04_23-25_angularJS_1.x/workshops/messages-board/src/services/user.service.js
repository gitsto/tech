(function () {
    'use strict';

    angular.module('user.service', [])
        .service('UserService', UserService);

    var email = '';

    UserService.$inject = [];
    function UserService() {

        console.log('UserService')

        this.isActiveUser = function () {
            return email.length > 0 ;
        }

        this.getCurrentUser = function () {
            return email;
        }

        this.login = function (emailInput) {
            
            if( emailInput.includes('@orsys.fr')) {
                email = emailInput;
                return true;
            }else{
                return false;
            }
        }

        this.logout = function () {
            email = '';
            return true;
        }

    }

})()