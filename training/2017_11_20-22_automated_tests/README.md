# Notes

50% des tentatives de Tests Auto sont des échecs :

- il n'y a pas de budget allouer au test Autos.
- c'est un métier de dev que de développer des mots clés et cela n'est pas compris de tous.
  objectif de la couche métier : de lier la partie app à tester et mot clés à mettre à disposition. Que l'on doit test aussi!
  Pour valider que la nouvelle version de l'app livrée ne casse pas les liens utilisé pour fabriquer les mots clés
- les standards sont en cours de convergence (exemple compllexité de gestion de versions des plugins/drivers pour chaque navigateur => abandon de selenium)

Le TDD n'est pas la solution approprié, parfois favoriser le DDT (Design Driven Testing)

On peut utiliser (ou mixer) d'autres outils pour arriver à tester notre app ou un sous ensemble (ex: JavaScript)
bcp de résultats sont convertibles au format "junit" que comprends Jenkins (sinon voir meme les plugins).

reflexe astuce d'automaticien:

- utiliser les bonnes librairies pour tester de manière consistante
  api/lib pour synchroniser l'attente de la dispo des élements UI. <http://selenide.org/> (korée)
- tester le résultat par rapport à la visibiliter des images/icones. <http://sikulix.com/>

selenium remote driver (java) permet de selectionner des OS, navigateurs,
et de paralleliser/dispatcher des tests sur des VM/Dockers différents en pilotant
un hub qui communique avec des nodes

robot web pour selenium <http://sideex.org/>

angular js web driver java basé sur protractor (lui meme utilisant du node.js)
<https://github.com/paul-hammant/ngWebDriver>
cela facilite la recherche d'elements web (car engular génère des id dynamiquement, ce qui complexifie la stabilité car les ids ne sont pas uniques)

<dossier_formation>\tds\eclipse\workspace\webdriver\src\main\java\webdriver\Exercice.java est un exemeple de code qui utilise le ngWebDriver qui fait des appels javascript (jquery) pour récuperer des objets java.

Pratiques dans d'autres sociétés :

- poste/équipe de dev (transverse) avec un budget pour les tests auto qui est puisé dans les projets. Ou bien qui a un budget à part.

UI recorder <=> selenium IDE

formateur osrsys : olivier.charles.calvados@orange.fr

comparatif :

- <http://www.growing-object-oriented-software.com/>
- <https://www.amazon.fr/Design-Driven-Testing-Smarter-Harder/dp/1430229438>
