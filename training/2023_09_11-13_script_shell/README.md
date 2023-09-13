# Notes sur la formation

## Formateur

* [Yves Rougy](https://rougy.net/)
* [mail](yves@rougy.net)
* [YouTube](https://www.youtube.com/@yrougy)

### références/livre recommandés
* de O'Reilly : Google : "index+of" unix power tools pdf
* https://www.baeldung.com/linux/bash-single-vs-double-brackets
* chatgpt: y faire des recherches génériques, pas de copier coller d'algo ou d'info relatives à la vie privée ou pro...
* https://explainshell.com/
* https://crontab.guru/
* http://www.cronmaker.com/
* https://regex101.com/

### Orsys - Evaluation
https://eval.orsys.fr/
4K24R4w8a

## Intro

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
```shell
[user@lx-15-9 2023_09_11-13_script_shell]$ echo $PATH
/home/user/.local/bin:/home/user/bin:/usr/local/bin:/usr/local/sbin:/usr/bin:/usr/sbin:/var/lib/snapd/snap/bin

eviter de mettre '.' dedans car on peut avoir des surprises (substitution de commande, un faux ls qui existe dans un autre répértoire, et quand on y tombe... il est executé à la place du ls primitive du système)
```

### EXERCICE DU CHAPITRE 2

DÉCOUVERTE DE L’ENVIRONNEMENT
Connectez-vous sur votre machine en suivant les indications de votre formateur.  
* Quel shell utilisez-vous ?
```shell
ps
```
* Quels sont les shells disponibles sur la machine ?  
```shell
cat /etc/shells  

```

ou  
chsh -list

* La commande chsh permet de changer le shell de connexion de l’utilisateur.
Vérifiez que le ksh est installé sur votre machine, et demandez à votre formateur
si il n’est pas présent. Quand il est installé, changez votre shell de connexion par
le ksh. Regardez le fichier /etc/passwd et vérifiez que votre utilisateur a bien le
ksh en shell par défaut. Déconnectez-vous (faire un session disconnect avec bouton) et reconnectez-vous.  
Êtes-vous avec le ksh ?
Oui

• Remettez en place le shell d’origine.  
MANIPULATIONS SUR LE SHELL

* Affichez les alias existants sur votre machine.
* Créez les alias suivant:
* ll pour ls -l
* ctmp pour cd /tmp
* la pour ls -la


cat /etc/passwd | grep -i '/home/user:'

```shell
touch fichier{1,2,3,4,5,6,7,8,9,10,11,12,13,14,a,b,c,d,e,f,A,B,C,D}
```

la commande date est evalué après l'affectation de variable et donc le résultat est changé à la volé
```shell
[user@lx-15-9 ~]$ date
lun. 11 sept. 2023 14:35:17 CEST
[user@lx-15-9 ~]$ LANG=C date
Mon Sep 11 14:35:22 CEST 2023
```


### EXERCICE DU CHAPITRE 3

SHELLS D’EXÉCUTION
Écrivez le script suivant (exo3.sh):
• Exécutez les commandes à la main, et notez ce que vous obtenez:

[user@lx-15-9 tmp]$ cd
echo -n 'répertoire courant: '
pwd
echo -n 'liste des processus: '
ps
cd /tmp
echo -n 'Numéro PID du shell courant: '
echo $$
echo -n 'répertoire courant: '
pwd


répertoire courant: /home/user
liste des processus:     PID TTY          TIME CMD
  71678 pts/0    00:00:00 bash
  73397 pts/0    00:00:00 ps
Numéro PID du shell courant: 71678
répertoire courant: /tmp


_____________________________________________________________

• Exécutez le script de 3 façons. Qu’obtenez vous ?
```shell
user@lx-15-9 2023_09_11-13_script_shell]$ sh exo3.sh 
répertoire courant: /home/user
liste des processus:     PID TTY          TIME CMD
  71678 pts/0    00:00:00 bash
  73481 pts/0    00:00:00 sh
  73482 pts/0    00:00:00 ps
Numéro PID du shell courant: 73481
répertoire courant: /tmp
[user@lx-15-9 2023_09_11-13_script_shell]$ 
[user@lx-15-9 2023_09_11-13_script_shell]$ 
[user@lx-15-9 2023_09_11-13_script_shell]$ 
[user@lx-15-9 2023_09_11-13_script_shell]$ /bin/bash exo3.sh 
répertoire courant: /home/user
liste des processus:     PID TTY          TIME CMD
  71678 pts/0    00:00:00 bash
  73503 pts/0    00:00:00 bash
  73504 pts/0    00:00:00 ps
Numéro PID du shell courant: 73503
répertoire courant: /tmp
[user@lx-15-9 2023_09_11-13_script_shell]$ 
[user@lx-15-9 2023_09_11-13_script_shell]$ 
[user@lx-15-9 2023_09_11-13_script_shell]$ chmod +x exo3.sh 
[user@lx-15-9 2023_09_11-13_script_shell]$ ./exo3.sh 
répertoire courant: /home/user
liste des processus:     PID TTY          TIME CMD
  71678 pts/0    00:00:00 bash
  73524 pts/0    00:00:00 bash
  73525 pts/0    00:00:00 ps
Numéro PID du shell courant: 73524
répertoire courant: /tmp
[user@lx-15-9 2023_09_11-13_script_shell]$ ksh < exo3.sh 
répertoire courant: /home/user
liste des processus:     PID TTY          TIME CMD
  71678 pts/0    00:00:00 bash
  74714 pts/0    00:00:00 ksh
  74715 pts/0    00:00:00 ps
Numéro PID du shell courant: 74714
répertoire courant: /tmp

```
_____________________________________________________________
• Notez le PID du shell courant dans les 4 cas:
1. 71678
2. 73481
3. 73503
4. 73524
• Est-il différent de votre shell courant ? 
Oui
Pourquoi ?
C'est un processus fils créé à chaque fois



PATH
• Quel est le fichier exécuté par la commande find ~ -print ?
/home/user

• Quels sont les répertoires parcourus lors de la recherche ?
tous les répértoires sous ~ y compris les répertoires cachés commençants par .

• Quels répertoires restaient à parcourir ?
Je ne sais pas


MANIPULATIONS
• À l’aide de echo affichez
• Aujourd’hui, cours de programmation shell

```shell
echo Aujourd\’hui, cours de programmation shell
```

• Affichez tous les fichiers commençant par u du répertoire /usr/bin
```shell
ls /usr/bin/u*
```

• Placez vous dans votre répertoire de connexion. Quel est le résultat de la commande
• echo zzzz*

```shell
[user@lx-15-9 2023_09_11-13_script_shell]$ cd
[user@lx-15-9 ~]$ echo zzzz*
zzzz*
```
• Pourquoi ?
Il n'y a pas de fichiers commençant par zzzz dans le repertoire de connexion


• Enregistrez la liste des fichiers dont le nom se termine par sh et qui se trouvent
dans /bin dans le fichier ~/shells.

```shell
[user@lx-15-9 ~]$ ls /bin/*sh > ~/shells
[user@lx-15-9 ~]$ cat shells 
/bin/bash
/bin/brltty-config.sh
/bin/brltty-prologue.sh
/bin/chsh
/bin/dockerd-rootless-setuptool.sh
/bin/dockerd-rootless.sh
/bin/gettext.sh
/bin/gvmap.sh
/bin/hash
/bin/instmodsh
/bin/ksh
/bin/lchsh
/bin/lesspipe.sh
/bin/pax11publish
/bin/python-argcomplete-tcsh
/bin/rescan-scsi-bus.sh
/bin/rksh
/bin/setup-nsssysinit.sh
/bin/sh
/bin/source-highlight-esc.sh
/bin/src-hilite-lesspipe.sh
/bin/ssh
/bin/stapsh
/bin/tclsh
/bin/tpm2_hash
/bin/tpm2_policycphash
/bin/tpm2_policynamehash
/bin/virsh
/bin/vmware-license-check.sh
/bin/vmware-license-enter.sh
/bin/wish
/bin/zsh
```

À l’aide de la commande touch, créez les fichiers suivants un par un:
* \*  
touch \\*

* un fichier  
touch un\ fichier

* []  
touch []

* -f
touch /home/user/-f  
ou touch ./-f  
ou touch -- -f avec le -- qui est une option spéciale de plusieurs primitives de shell qui indique qu'on ignore l'interpretation du - comme un prefix d'une option

* Supprimez les un par un:

rm '*' 

## Redirections

le metacaractère > fichier signifique que la sortie standart (stdout ou '1') (implicite) est redirigée vers un fichier

exemple pour écraser un fichier  
\> fichier

on peut faire  
ls > fichier pour écrire dans le fichier ce qui aurait dû aller en stdout. Sans les couleurs venant du terminal

ls >> fichier

/!\ on traite les metacaractères avant d'executer la commande

cat < passwd > passwd  
cat passwd      <<< vidé ! car on a traité les < puis > AVANT d'executer la commande  cat

/dev/null est un périphérique qui ne fait on peut souvent utiliser pour rediriger la sortie stderr dessus pour ne pas polluer ce qui est affiché au terminal 


## Variables

les variables globales n'existent pas dans les shell

une variable d'environnement est transmise à un processus fils par COPIE



## connaitre les commandes disponibles dans le system

```shell
su
root    # c'est le mdp

mandb -c  #

# revenir en user normal, CTRL+D

apropos userid   # le paramettre permet de chercher le terme present dans la description de la commande à retrouver
cuserid (3)          - get username
whoami (1)           - print effective userid

#ou bien aussi

man cd

NAME
       bash,  :,  ., [, alias, bg, bind, break, builtin, caller, cd, command, compgen, complete, compopt, continue,
       declare, dirs, disown, echo, enable, eval, exec, exit, export, false, fc, fg, getopts, hash, help,  history,
       jobs,  kill,  let,  local,  logout,  mapfile,  popd, printf, pushd, pwd, read, readonly, return, set, shift,
       shopt, source, suspend, test, times, trap, true, type, typeset, ulimit, umask, unalias, unset, wait  -  bash
       built-in commands, see bash(1)


<https://www.geeksforgeeks.org/list-all-available-commands-and-aliases-in-linux/>

```

jobs


## SCRIPTS

### portabilité d'un script

l'utilisation de la 1ère ligne dans un fichier

> #!/usr/bin/env bash  

est plus recommandé que le classique quand on ne sait pas sur quelle machine sera executé

> #!/bin/bash

les test condition avec if [] sont des standard posix, les if [[ ]] sont des évolutions côtés bash et ksh


### verifier un script, outil shellcheck

[ShellCheck](https://www.shellcheck.net/) existe en outil ligne de commande pour vérifier les potentielles erreurs le script shell.


avec un read on peut rajouter une variable bidon pour filtrer les parametres ajoutés derrière pour 'protéger' la saisi. Exemple d'application pour un oui/non

```shell
read reponse junk
```
reponse pourra tester oui ou non, mais si un utilisateur écrit oui je suis d'accord.... junk sera là pour récupérer le rest non utile...

`case`

utilise des motifs (patterns) pas de regex 


## Robustesse

pour éviter les problèmes, toujours utiliser les guillements autour des valeurs de variables

echo "$2"
expr "$num" "$oper" "$num2"

`IFS` = internal field separator, par défaut c'est espace mais on peut le modifier

dans un programme sensible pour éviter de se faire embéter utiliser un unset IFS pour être sur de vider la valeur qu'un petit malin aurait setté avant en var d'env


## Expression regulières

on les utilisent partout sans le savoir

dans un mail les spam passent par des filtres

grep (re = regular expression) inplementent les 'BREG' = basic regular exprerssion
egrep Egrep 'e' pour EREG = enhanced regular expression

### Divers

`mktemp` commande qui créer un fichier temporaire avec un nom aléatoire

`cron`  
fourni avec un environnement minimaliste, il nous pose des pbs exemple son PATH ne contient pas tout les répartoire habituels qu'on a utilisé pour dev le script.

Alors on peut faire un source du dossier manquant pour avoir les binaires chargés /usr/local/bin
ou bien (moins pratique) écrire tout les chemins des binaires qui ne sont pas connu par le PATH par défaut du cron...

