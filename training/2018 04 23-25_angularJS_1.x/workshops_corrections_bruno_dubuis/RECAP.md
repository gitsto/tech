
# AngularJS


## Historique / Vocabulaire / Acccronymes
* Vrai nom de JavaScript = Ecma Script
* ES5 -> base utilisée pour AngularJS
* ES6 -> nous ne verrons pas durant la formation
* Accronyme NG (angularJS, car on prononce N-gular)

Notions associées
* WebPlatform : W3C
* HTML: Structure
* CSS : Presentation
* JS : interaction logique
* REST - SPA (Single Page Application)
* Ajax - appels XHR

## Versions majeures
* Angular 1.x -> AngularJS
* 2+ (4,5,6, 10000) -> Angular

## Outils (pratique et logique)

* [node + npm](https://nodejs.org/en/)
* [VS Code](https://code.visualstudio.com/docs/?dv=win)
* Plugin et snippets
* Chrome : navigateur avec les meilleurs outils de tests, recommander de coder en 1er sur chrome puis tester sur les autres navigateurs...
    * Extension : ng-inspector
    * Extension : angularjs-inspector
    * Extension : angularjs-graph 
* [MDN](https://developer.mozilla.org/fr/docs/Web/JavaScript)
* [Angular](https://docs.angularjs.org)
* Framework CSS
* [ES Compatibility Table](http://kangax.github.io/compat-table/es5/)
* [All docs in one place] (devdocs.io)

* [Test visuel du code Asynchrone](http://latentflip.com/loupe) exemples callback, promise, event etc
### Nodejs + npm

VSCode: editeur de code
NodeJS : moteur d'execution de script / interpreteur (equivalent JVM)

> npm est un **gestionnaire de dépendances**

Initialiser le bordereau de dépendances :

`npm init`

Installation locale (dossier projet)

`npm i PACKAGE_NAME`

Installation global (machine)

`npm i -g PACKAGE_NAME`

Forcer un version :

`npm i [-g] PACKAGE_NAME@version.number`

* [lite-server (avec son url localhost après installation)](http://localhost:3000)
* [json-server (avec son url localhost après installation)](http://localhost:5050/messages/2)


## Framework Angular

> Gain de productivité :  Abstraction de la manipulation DOM, AngularJS utilise jQuery

* Elevation du HTML a travers les directives.
* Le HTML Devient un langage de TEMPLATE
    * Structure HTML
    * Presentation
    * Données

> Angular apporte la séparation VUE / MODEL

Plan d'apprentissage :
* ✓ Object Angular
* ✓ Directives
* ✓ `$scope` Angular

Strucuration Applicative :

* S - Single Responsibility Pattern
* O - Open / Close 
* L - Substitution
* I - Interface Segregation
* D - Inversion de Dependances

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

### Filtres

> Règle de transformation de données pour la présentation.

```js
angular.module('myModuleName',[]/*dependencies*/)
    .filter('myFilterName', MyFilterFactory );

    MyFilterFactory.$inject = [];
    function MyFilterFactory(){

        return function(input){
            return input + 'anyTransformation';
        }
    }

```

```html
<div>
    {{ myBindedValue | filterName }}
</div>
```

### Notion de composants

> Peut être exprimé par `.directive` ou `.component` a partir de la version 1.5;

C'est une unité fonctionnelle de la UI: 
* Un `template`
* Une `logique` relationnelle le `$scope`
* Une présentation ou comportment particulier.

### Services

> C'est la business logic de l'application.

Traite les données pour les livrer au `controller`

* Acces aux données.
* Filtrer.
* Regle de création/modification.

> Les services sont **injectés** dans les `controller`

> Ce sont des `singleton`.

## JavaScript

* Code Hoisting
* Scope (ES5)
* ByVal ByRef
* Pattern de protection : `IIFE`
* Protoptype
* Asynchronicité : Callback, Event, Promise,...

définition rapide de promise : 
 donner qqchose qui sera executé plus tard, et pour se brancher dessus on utilise then.


## Pratique / installation de l'environnement de dev pour les exo de la formation

workshops/resources/
- nodejs et vscode

puis pour lancer vscode depuis sont dossier dans la barre d'adresse de windosw explorer, taper "cmd"
et ça ouvre une ligne de commande dans le bon dossier
taper la commande 

code .

marketplace
VSCode extention 
- (CSS) botstrap 3 snippets (sans cliquer sur recharger)
- angular 1 javascript and typescript snippets (John Papa, référence en collab avec Google)
- markdown pdf
cliquer sur "recharger" 

Crtl+Maj+P pour ouvrir la p
"term in"

vérifier que les outils sont installés dans le terminal
node -v
npm -v

## Astuces de codage
* dans VSCode comment créer une page html ? page vide puis 
!<Enter> (grace à emmet qui est un plugin de productivité)

* taper dans la console (f12) le mot clé angular donne toutes l'api disponible

* dans la console chrome $0 est un raccouri pour manipuler l'objet sélectionné, exemple $0.title $0.onClick etc

## Livres / Ouvrages
* [ng-book](https://www.amazon.fr/dp/099134460X/ref=olp_product_details?_encoding=UTF8&me=)

## Tests unitaires
* Jasmin pour écrire des TUs
* Karma permet de lancer TU et code metier puis de faire un rapport
comment installer ? npm install jasmin, npm install karma...


## Bests Practices et ce qu'il faut éviter, mes notes en vrac ici

* Pour composer ses pages, un fichier js par module.
* service = zone unique située dans un emplacement à part de la logique de présentation
best practice, des fichiers html légers, des services plus gros à part.
* les requestes sont dans les services, à éviter les requetes dans les controllers
* npm run <clé_dans_un_json>, va lancer la commande saisie en valeur dans le fichier json. Exemple package.json
* pour debugger, utiliser directement dans le code le mot debugger et cela sera directement détécté par la console, 
attention à ne pas les laisser en prod, comme les console.log()...
* scope préférer utiliser un object qui est passé par référence au scope fils, 
pour éviter de créer plusieurs variables de meme noms dans des scopes différents qui sont passés par valeur 
et qui écrasent la valeur
* 'privatiser' une variable c'est utiliser 'var' plutôt que 'this' dans une fonction de service
* 'scoper' une css, utiliser un id et y faire référence avec un '#' exemple input#loginUser
* transclude faire passer du contenu à un composant, à condition que l'option soit activée dans ce dernier.
* AngularJS fait du digest à chaque evenement produit (ou bien lors de changement de model, ou bien lors de l'execution de setTimeout, setInterval, ) 
si cela est connecté au data, on utilise un ng-<event> 
sinon on utilise un autre moyen (voir le code pour le zoom de l'image Robot dans message-board) pour ne pas surcharger en travail a faire.
* manipulation du DOM sont à faire dans les directive, dans des "link function"
* utilisation des level: '@' évite de taper level: '@level' comme raccourci

## RAPPELS 
* Requetes HTML, différence entre GET est une requete cachable et POST clé valeur sont transmis dans le body
