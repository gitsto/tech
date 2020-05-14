# Notes de formation Java 8, 9, 10, 11

## Java 8

Java simule de la programation fonctionnelle, en utilisant les lambdas qui sont possible grace à des interfaces à une seule methode.

`@FunctionalInterface` est conseillée comme indicateur pour le compilateur qu'il vérifie à la compilation que l'interface ne contient qu'une seul méthode. L'annotation permet aussi d'optimiser le bytecode.

`Optional` a été intégré à Java pour améliorer la gestion des NullPointer, mais sans trop de succès, cela a un coup mémoire => non recommandé.

les `streams` sont consommés qu'une seule fois en Java (contrairement à d'autres langages)

## Java 9

améliore les stream en reactive stream, ceux qui utilise Spring 5 ont plus interêt à utiliser l'implementation [Spring 5 API Reactor](https://www.baeldung.com/spring-webflux) pour gérer des flux de données
[WebFlux](https://medium.com/@zineb.errahmouni/cr%C3%A9er-une-simple-application-rest-r%C3%A9active-avec-spring-5-web-flux-et-reactor-3c733ad858e0)

Si on souhaite faire de la programmation avec gestion parallèle des flux (parallelStream) il vaut mieux utiliser la programmation réactive (de spring).

### Methodes privées

les methode privée autorisée dans les interfaces.

try-with-resources peuvent utiliser le autoclosable => plus besoin d'utiliser close() mais attention il faut le faire haut niveau si on ne veut justement pas fermer avec des try internes dans un gros try

### Method "of"

Avant utilisé dans les date/time 
plus utile et lisible pour construire des liste.

```java
List<String> l = List.of("un", "deux", "trois");
Set<Integer> l = Set.of(1, 2, 3);
```

### API Flow

Pour traiter les données de manière asynchrone et non bloquante.

* Publisher
* Subscriber

Pipeline: Produceur ====> Processing stage 1 =====> Processing stage 2 ======> Consumer

Cas ou le consumer est saturé: back pressure
l'algo de gestion de back pressure n'est pas implémenté c'est dev de le faire en J9.
Dans spring, cela est fait.

### JShell

read-eval-print-loop (REPL) comme dans le principe utilisé par Python

### Jigsaw

C'est un système modulaire qui apporte la notion de module qui est un conteneur de packages. Le livrable est un jar qui contient un manifest module-info.java (un peu comme package-info.java)

## Java 10
