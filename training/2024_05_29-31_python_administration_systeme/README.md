# Notes de formation

[Orsys : Python, administration système
](https://www.orsys.fr/formation-python-administration-systeme.html)

## Formateur

* mail: [Hugo Mougard](hugo@mougard.fr)

## Références

* [Python Tutor](https://pythontutor.com) : executer et analyser du code en ligne
* [Docs Python3 - Data Model](https://docs.python.org/3/reference/datamodel.html#special-method-names)
* [Py format](https://pyformat.info/)
* [Docs Python3 - Collections](https://docs.python.org/3/library/collections.abc.html#module-collections.abc)
* [Docs Python3 - Fonctions natives](https://docs.python.org/fr/3/library/functions.html)
* [Docs Python3 - Exceptions](https://docs.python.org/3/library/exceptions.html#exception-hierarchy)
* [Mypy (is an optional) static type checker for Python](https://www.mypy-lang.org/)

## Exos

>Sur [Google Colab](https://colab.research.google.com/), les TPs/exercices à faire:

* [Affichage](https://colab.research.google.com/drive/13L1KChRhH-MbWM2Wg2QDC7FoFSp2j9cv#forceEdit=true&sandboxMode=true&scrollTo=RQFAZCXlRFH9)

* [Collections](https://colab.research.google.com/drive/1pWD3qd0tlEqbpYxGoBi-3VxUrk5uLSxD#forceEdit=true&sandboxMode=true)

* [Boucles et comparaisons](https://colab.research.google.com/drive/1W0twH5aq2UFgoLIJNS9sx8LsIQBW-1pP#forceEdit=true&sandboxMode=true&scrollTo=wCicBNyFCnxd)

* [Tests](https://colab.research.google.com/drive/1hBCYVCcNbBchddbAWVyRC38M2Pv0lVfM#forceEdit=true&sandboxMode=true&scrollTo=OLby1mWPrPy8)

* [Fichiers](https://colab.research.google.com/drive/13HqNbsHOHdmN-o6W07D-AQRy3UgiRODN#forceEdit=true&sandboxMode=true&scrollTo=wp9QBijGXn2c)

* [Fonctions](https://colab.research.google.com/drive/1tWtvW_I1YMzqXbw8CLb3rB85qnQWrskx#forceEdit=true&sandboxMode=true)

* [Generateurs](https://colab.research.google.com/drive/12xhlfIvxSvTt2BhO8IZe5rlmAZ0c6_jk#forceEdit=true&sandboxMode=true&scrollTo=36rRdWXVkzxe)

* [Programmation orientée objet](https://colab.research.google.com/drive/1Tgp3kRsElRsRM2237hxAbRnV-Dy_-8G_#forceEdit=true&sandboxMode=true&scrollTo=NN1fv0CJLAGg)

### A VOIR / Qeustions ?

* github code spaces : conteneur avec vs code configuré python ==> Très intéressant pour diffuser un environnement de travail dans une équipe

* les extension utilisé par Hugo dans son VS Code pour le formattage, l'analyse des types

* le contenu de la formation qui m'intéressait au départ ...

Les bases de l'administration système
Analyser des logs avec les expressions régulières.
Manipuler et analyser des fichiers CSV/Excel avec Pandas.
Passer des paramètres à un script avec argparse.
Utiliser une base de données relationnelle.
Exécuter des commandes système.
Travaux pratiques
Recherche d'intrusions/erreurs dans un fichier de logs. Insertion de fichiers CSV dans une base de données relationnelle. Géolocaliser les adresses IP. Créer une archive tar/zip.

Compléments d'administration système
Se connecter à une API web avec requests et télécharger le contenu de pages HTML avec scrapy.
Envoyer des emails.
Administrer plusieurs machines avec fabric et ansible.

### Divers, bonnes pratiques etc

```python

# issubclass
sto@STOJISAS-5420B:~$ python
Python 3.10.12 (main, Nov 20 2023, 15:14:05) [GCC 11.4.0] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> issubclass(bool, int)
True

# lecture de fichier
# fixer utf-8 pour éviter des surprise, par defaut la local de l'ordinateur sur lequel s'execute le code est utilisé
# pour les gros fichiers eviter de faire f.read() pour ne pas saturer la mémoire
with open("example.txt", encoding="utf-8") as f:
    for line in f.lines();
        print(line)

import csv
# affiche 
with open("wiki_movie_plots_deduped.csv", encoding="utf8") as fichier:
    reader = csv.reader(fichier)
    colonnes = next(reader)
    print(f"Les colonnes sont {colonnes}")
    indice_titre = colonnes.index("Title")
    for i, line in enumerate(reader):
        print(line[indice_titre])
        if i > 10:
            break

# si une methode retourne un tuple on n'est pas forcement intéressé par toutes les valeurs retournées, on peut par convention indiquer que la valeur retournée ne nous interesse pas (_iteration_control)
# exemple:

import csv
with open("wiki_movie_plots_deduped.csv", encoding="utf8", newline="") as fh:
    reader = csv.reader(fh)
    colonnes = next(reader)
    index_title = colonnes.index("Title")
    for line, _iteration_control in zip(reader, range(10)):
        print(line[index_title])

# /!\ utiliser le mode='w' ecriture de fichier, ecrase le fichier en le vidant, attention 


# utilisation d'assert est très pratique pour valider un comportement

# brin_adn = ["A","C","G","T","T","A","G","C","T","A","A","C","G"]
from collections.abc import Iterable, MutableSequence

def seq_comp(brin: list[str]) -> list[str]:
  traduction = dict(A='T',T='A', C='G', G='C')
  return [traduction[base] for base in brin]

assert seq_comp(brin=["A","T"]) == ['T', 'A']

# test d'appartenance: les 'set' sont plus performants que les 'list' car dedans les valeurs sont unique, et aussi par contre non ordonnées. Attention à l'utilisation

# utiliser 'raise:' quand on code une librairie, une API, etc pour donner au code client la résponsabilité de traiter lexception dans un try except


>>> # 'sous le capot' en python dans une boucle for, un iterateur est créé pour boucler sur l'objet de la boucle
= iter([1,2,3])
next(it)
next(it)
next(it)
next(it>>> for item in [1,2,3]:
...     print(item)
... 
)
1
2
3
>>> it = iter([1,2,3])
>>> next(it)
1
>>> next(it)
2
>>> next(it)
3
>>> next(it)
Traceback (most recent call last):
  File "<stdin>", line 1, in <module>
StopIteration

# donc on peut faire une boucle 'while' pour réécrire 'for'
while True:
    try:
        item = next(it)
        print(item)
    except StopIteration:
        break


# le !r est utiles pour des type 'str' pour conserver les caractères d'echapement

# attribut de classe sont des niz à bug, car en python on peut rajouter à chaud des attribut de classe et des fonctions

```

## Conclusion

Hello la formation Python est top jusque là, même pendant le j1 sur les bases, j'ai (ré)appris des trucs surtout avec des explications pertinentes ainsi que quelques outils d'analyse de code, bonnes pratique pour rendre le code plus maintenable
