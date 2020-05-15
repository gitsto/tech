# Quelques Notes (Formateur : jldeleage@free.fr)

Pour l'objectif lambda en Java 8,
on se base sur des interfaces fonctionnelles :

- qui contient les méthodes de Object
- avec une seule méthode (abstract) à implementer
- qui permet de définir des méthodes statiques
    > cela permet de ne plus avoir du découplage, exemple Collection et Collections qui ne contient que des méthodes statiques utilitaires pour manipuler des obj Collection.
- qui permet de définir des méthodes *default*
    > c'est raccourci par rapport à l'implementation dans une class abstract où l'on implementerait le comportement par défaut)
    > cela permet de l'héritage multiple si on implemente 2 interfaces on bénéficie de leur méthodes default.

Nashorn pour executer du JS dans Java.
Sécurité TLS 2.1 activée par défaut.

## Lambda expression

- définir une méthode annonyme (à la place d'une classe anonyme)
- gagner en visibilité.

toute fonction est un objet. On peurt la stocker dans une variable.

Les TPS sont ceux du dossier
>install_jva\autres TP\

à copier coller dans un nouveau projet dans eclipse pour tester

notations de référence à une méthode au lieu de lambda:
(x,y,z) -> C.f(x,y,z), peut être notée C::f
(x,y,z) -> x.f(x,y,z), peut être notée x::f
(x,y,z) -> x.f(x,y,z), peut être notée C::f

>montrer dans le wiki du board comment faire avec Code Style (Clean up) pour convertir des portions de code en lambda

## Streams: (manip de données)

concept de "filter-map-reduce" ou juste map-reduce  
On travaille sur une collection d'objet au départ.
principe :

- la couche "reduce" demande à la couche "map" qui demande à la couche "filter" d'aller travailler sur une référence à la fois.
on peut paralleliser le traitement (en passant par un pool de threads...)

Le stream peut être comparé à un iterator,
il sert à "parcourir" une collection, il ne la contient pas.
et à la fin du parcours (exemple, si on affiche les données dans un System.out.println), on ne peut plus le réutiliser.

exemple de bon tuto sur les stream : http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
plus généralement : <http://winterbe.com>

<http://www.mkyong.com/tutorials/java-8-tutorials/>

## Date et Time

2 catégories :

- temps "machine" instant.now();, au lieu de currentMilliseconds()... etc
EPOCH origine de la norme "1 Jan 1970..."
- temps "humain" LocalDate, LocalTime, LocalDateTime.now(); attention aux manipulations qui peuvent générer des exceptions selon si l'info existe ou pas.
exemple : on veut rajouter une heure sur un objet LocalDate => KO, car une LocalDate ne contient pas d'heure.

on ne converti/modifie pas => mais on fabrique un nouvel objet à l'appel des méthodes.

## Java 8 Nashorn (moteur JavaScript, anciennement Rhino)

En JavaScript :
on peut passer par le prototype d'un constructeur d'objets pour dynamiquement modifier un objet (pas de notion de class par rapport à) java)

```javascript
var a = [1,2,3]
Array.prototype.filtre = function(f) {  // prototype est le mot clé
	var resultat ) [];
	for (var i=0; i<this.lenght; i++)
		if (f(this[i])) resultat.push(this[i]))
	return resultat;
}
```

En JavaScript

- le nombre de parametres d'une fonction n'est pas important.
Elle se débrouille et positionne la valeur undefined aux parametres non utilisés
- on peut aller via le prototype d'un autre objet, appeler une methode sur son instance d'objet courante même si les objets n'ont rien à voir.

Dans Nashorn, côté JavaScript, on peut utiliser l'appel
var timerJava = Java.type("java.util.Timer") // importation
