(function(){
    'use strict';
    // Code protégé par l'IIFE

    angular.module('main', ['other']) // Dependance de MAIN sur Other
        .run(runMain)
        .config(configMain); // Provider

    function runMain(){console.log('RUN','MAIN')}
    function configMain(){console.log('CONFIG','MAIN')}

    angular.module('other', [])
        .run(runOther)
        .config(configOther);

    function runOther(){console.log('RUN','OTHER')}
    function configOther(){console.log('CONFIG','OTHER')}
    
    //fin du code
})();