
* ✓ Peut on mettre des regex dans les filter
oui

* ✓ Peut on definir des validateurs custom
oui et c'est un des but majeurs d'angular. Exemple créer son custom validateur de manière unique, qui sera réutilisé dans plusieurs pages ou endroit différents

* Les directives ont elles un niveau de priorité
oui, mais nous n'avons pas eu a voir cela pendant la formation, il faudra regarder la docs

* ✓ Pourquoi le factory ne sont pas préférables
alors dans mes souvenir, une factory créer plusieurs instances d'objets à chaque fois qu'on y fait appel, cela peut rapidement 'gonfler' le nombre de traitement.
durant la formation nous avons vu qu'il est préférable d'utiliser des passage d'objet par référence pour éviter cela parfois même d'encapsuler un primitif dans un objet, car les primitifs sont passé par valeur (copie).