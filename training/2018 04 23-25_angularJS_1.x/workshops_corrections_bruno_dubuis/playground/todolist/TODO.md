
1. Initialiser une liste de tache 2 objets et dupliquer les li
    [{text:'A',complete:false},{text:'B',complete:true}]
    * ng-init
    * ng-repeat
    * {{}}
    * ng-checked

```html
<body ng-app 
 ng-init="tasks = [{text:'A',complete:false},{text:'B',complete:true}]">
 <!-- ... -->
```

```html
<li ng-repeat="t in tasks track by $index">
            <span>{{t.text}}</span>
            <input type="checkbox" ng-checked="t.complete" ng-model="t.complete">
            <button>X</button>
        </li>
```

> visualiser un Objet
```html
<pre>
    <code>
     {{tasks | json}}
    </code>
</pre>
```

2. Connecter la checkbox sur le *model* de tache

```html
<li ng-repeat="t in tasks track by $index">
            <span>{{t.text}}</span>
            <input type="checkbox" ng-checked="t.complete" ng-model="t.complete">
            <button>X</button>
        </li>
```

3. Programmer la suppression de la tache

```html
<li ng-repeat="t in tasks track by $index">
            <span>{{t.text}}</span>
            <input type="checkbox" ng-checked="t.complete" ng-model="t.complete">
            <button ng-click=" tasks.splice($index,1) ">X</button>
        </li>
```

4. Integrer le formulaire d'ajout

```html
<form>
        <input type="text" placeholder="Add Something..." ng-model="userInput">
        <button 
            ng-click="tasks.push({text:userInput,complete:false})"
            ng-disabled=" !userInput "
            >Add</button>
    </form>
```

5. Ajout de validation