(function () {
    'use strict';

    angular.module('common', [])
        .directive('commonButton', CommonButtonDirective)
        .directive('commonZoom', CommonZoomDirective)
        .directive('commonEmailValidator', CommonEmailValidatorDirective)

    //commonButton
    //commonZoom
    //commonEmailValidator

    CommonButtonDirective.$inject = [];
    function CommonButtonDirective() {
        return {
            restrict: 'E',
            transclude: true,
            templateUrl: 'src/common/common-button.html',
            scope: {
                level: '@', // @color = &
                icon: '@'
            }

        }
    }

    CommonZoomDirective.$inject = [];
    function CommonZoomDirective() {
        return {
            restrict: 'A',
            link: function (scope, elm, attrs) {
                /*                 
                console.warn('zoom',scope, elm, attrs)
                console.warn(attrs.commonZoom)
                console.warn(elm[0]) 
                */
                elm.wrap('<div style="border:5px solid lightblue;"></div>');

                elm[0].onmouseover = function () {   
                    elm.addClass('zoom');
                }

                elm[0].onmouseout = function () {
                    elm.removeClass('zoom');
                }
            }
        }
    }

    CommonEmailValidatorDirective.$inject = [];
    function CommonEmailValidatorDirective() {
        console.log('CommonEmailValidator')
        return {
            restrict: 'A',
            require: 'ngModel',
            link:function(scope, elm, attrs, ngModel ){
                console.log(ngModel);

                ngModel.$validators.atOrsys = function(modelValue,viewValue){
                    return /^\w+@orsys\.fr/.test(viewValue)
                }

            }
        }

        //DDO
        /*{
            restrict:'EA', // 'E' //'A'
            template:'',
            templateUrl:'',
            require:'directiveName', // pour obtenir directiveNameController
            link:function(scope,elm,attrs,requires){},
            scope:false, // true, {}
            transclude:true,
            controller:'ControllerName'
            //compile
            controllerAs:'name'
            bindToController:true
        }*/
    }


})()