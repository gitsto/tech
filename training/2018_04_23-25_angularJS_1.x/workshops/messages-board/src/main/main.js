(function () {
    'use strict';

    angular.module('main', [
        'common',
        'app-header',
        'app-message-form',
        'app-message-list',
        'services'])
        .controller('MainController', MainController)
        .config(ConfigCompiler);

    // Pour la mise en production
    ConfigCompiler.$inject = ['$compileProvider']
    function ConfigCompiler($compileProvider) {
        $compileProvider.debugInfoEnabled(false);
    }

    MainController.$inject = ['$scope', '$q'];
    function MainController($scope, $q) {
        this.value = 123456

        /* console.warn($scope)

        $scope.$watch(function () {
            console.log('Change')
        }) */


    }
})()