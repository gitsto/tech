
# AngularJS

## Outils (pratique et logique)

* [node +npm](https://nodejs.org/en/)
* [VS Code](https://code.visualstudio.com/docs/?dv=win)
* Plugin et snippets
* Chrome
* [MDN](https://developer.mozilla.org/fr/docs/Web/JavaScript)
* [Angular](https://docs.angularjs.org)
* Framework CSS
* [ES Compatibility Table](http://kangax.github.io/compat-table/es5/)

## Framework Angular

> 🦄

### Directives

> Les `directives` sont des mots clés apportés par angular pour exprimer la manipulation du DOM souhaitée;

il y a une equivalence CamelCase vers dash-case

> `ngApp` est utilisée dans le code HTML en tant que `ng-app` 

* `ngApp` - Zone surveillé par angular (`$rootScope`) 
* `ngModel` - 2Way Binding -> Logique Contole de la saisie
    * `$touched/$untouched` ->  CSS `.ng-...`
    * `$dirty/$pristine` ->  CSS `.ng-...`
    * `$valid/$valid` ->  CSS `.ng-...`
* `ngInit`
* `ngClick,....`
* `ngShow/ngHide`
* `ngIf` 
* `ngRepeat` -> Création d'un $scope par repetition et variable clé `$index,$first,$last...` 
* `ngBind == {{}}` 
* `ngPattern` 
* `ngMinlength` 
* `ngMaxlength` 
* `ngForm` 
* `ngClass` 
* `ngSwitch` 
* `ngChecked`  
* `ngRequired` 
* `input` 
* `a` 