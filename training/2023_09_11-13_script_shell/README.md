# Notes sur la formation

## Intro

### Evaluation
https://eval.orsys.fr/
4K24R4w8a

## Historique

### Normes

POSIX pour poser des bases communes entre les differents shell (csh, sh, ksh, ...)

ksh88 est le point de départ de POSIX au niveau des fonctionnalités



### Execution

#### Redirection

< est une redirection

sh < script.sh va dire de lire le fichier, et l'executer de manière interactive

```shell
[user@lx-15-9 2023_09_11-13_script_shell]$ ps
    PID TTY          TIME CMD
   8240 pts/0    00:00:00 bash
  10144 pts/0    00:00:00 ps
```


### metacaracteres

* espace: est un separateur d'arguments
* retour chariot (entrée): est une fin de commande pour laisser le shell interpreter la ligne de commande tapée et créer le tableau


dans le tableau le premier indice est 0, puis on vérifie:
* si il y a un / dans le premier
* si c'est un alias
* si c'est une primitive du shell

> astuce: pour court-circuiter un alias on peut faire \, exemple alias ls=vi, on peut utiliser \ls pour ne pas se faire avoir...

```shell
# echo est une primitive du shell, le resultat est rapide
[user@lx-15-9 2023_09_11-13_script_shell]$ time for i in $(seq 1 5000); do echo coucou > /dev/null; done

real	0m0,053s
user	0m0,029s
sys	0m0,024s
# echo est utilisé comme un script quelconque (comme si ce n'était pas une primitive à cause du /) et le résultat est lent
[user@lx-15-9 2023_09_11-13_script_shell]$ time for i in $(seq 1 5000); do /bin/echo coucou > /dev/null; done

real	0m3,100s
user	0m1,748s
sys	0m1,508s
[user@lx-15-9 2023_09_11-13_script_shell]$ time for i in $(seq 1 50000); do echo coucou > /dev/null; done

real	0m0,508s
user	0m0,276s
sys	0m0,227s
# meme exemple avec une exageration du cas de mauvaise perf
[user@lx-15-9 2023_09_11-13_script_shell]$ time for i in $(seq 1 50000); do /bin/echo coucou > /dev/null; done

real	0m33,491s
user	0m17,661s
sys	0m17,415s

```



recherche de la commande dans cette liste de répértoire stockée dans PATH

[user@lx-15-9 2023_09_11-13_script_shell]$ echo $PATH
/home/user/.local/bin:/home/user/bin:/usr/local/bin:/usr/local/sbin:/usr/bin:/usr/sbin:/var/lib/snapd/snap/bin

eviter de mettre '.' dedans car on peut avoir des surprises (substitution de commande, un faux ls qui existe dans un autre répértoire, et quand on y tombe... il est executé à la place du ls primitive du système)


