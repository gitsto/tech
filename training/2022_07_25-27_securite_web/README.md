# Securité des Applications Web

## Arabe

* Haram: Mauvais
* Hallal: opposé de Bien 
* Khallas: Terminé
* Abadan: Jamais

## Importantes règles

* La faiblesse des uns fait la force des autres
* Connaître les attaques qui existent (type, outils et commandes qui permettent de les executer), permet de mieux se défendre
* La confiance, n'exclu pas la vérification; exemple une personne de ta famille te donne de l'argent, mais tu recompte à part :)
* Le niveau de sécurité élevé, implique une baisse des performances
* 

### Astuces

Commande pour passer en clavier azerty français dans la session

> setxkbmap fr

ou bien modifier le fichier

>cat /etc/default/keyboard

```ini
XKBLAYOUT="fr"
```

## Cas de risques

* Sollicitations non sécurisées (armée utilisation de gmail)
* Contenu des informations critique (l'information n'est pas adaptée au destinataire)
* Faire des logs protégés pour détecter les attaques, éviter que les attaquants puissent les lires, modifier, supprimer
* Mixe entre manque de compléxité vs investiguation: un gamin qui 'hack' le compte d'Obama, car le mdp était le nom de son chien...

## HTTPS

"HTTPS over TLS" (version courante 1.3), pour chiffrer de bout en bout les données.  
Exemple: On ne sait pas si les données sont stokées en clair, et quelle machine sur le réseau peut lire. Les données chez l'hébergeurs ne sont pas à l'abris de subir une attaque de leur base de données (yahoo en 2010, 500M d'utilisateurs)

## ANSI 

<https://www.cert.ssi.gouv.fr/>  
vols de cookies  
<https://www.cert.ssi.gouv.fr/cti/CERTFR-2022-CTI-005/>

## Netcraft

Site, non commercial, pour référencer les niveau de sécurité etc...

![](img/web_servers_graph.png)

## Outils, astuces

### Antivirus

Vérifier par 3 antivirus minimum avant d'installer, sinon c'est ('harass' une faute professionnelle

Utiliser ce site pour faire vérifier par plusieurs antivirus des fichiers xpi, exe, pdf, etc: <https://www.virustotal.com/gui/home/upload>

Exemple pour récupérer le fichier de l'extension d'un browser (xpi ou autre)  

Manière simple: croiser les navigateur pour aller récupérer le fichier de l'extention au lieu de l'installer
Manière moins simple: aller en ftp, ou changer le type de fichier dans l'entête HTTP pour récupérer le fichier

### Utilitaire protocoles

* SCAPY terminal python pour manipuler des protocoles facilement: <https://github.com/secdev/scapy>
* Chrome ou firefox : http_header_live pour lire les échanges de requêtes  
exemple http://www.google.fr (on observe qu'il redirige sur https, qu'il propose du HTTP/2, ...)

## Protocoles

HTTP/3: <https://en.wikipedia.org/wiki/QUIC>  
Optimisation qui intègre TLS pour remplacer TCP : QUIC est utilisé par Zoom, Teams, ...


## Attaques

### Couches basses

#### TCP SYN Flooding

TCP complèxe et vulnérables à certaines attaques; TCP est géré par le noyau de l'OS  
[SYN flood](https://en.wikipedia.org/wiki/SYN_flood)  
Pour se défendre: Firewall, [SYN cookie](https://en.wikipedia.org/wiki/SYN_cookie)  
Intgré dans le noyau Linux, et activée par défaut,  
Sous windows, le fichier est à créer il n'existe pas par défaut.

```shell
cat /proc/sys/net/ipv4/tcp_syncookies  
1
```

exemple d'attaque sur le site d'orsys :)

>hping3 --flood --syn --rand-source -p 80 ww.orsys.fr

```shell
arp -a
? (192.168.3.15) at 00:0c:29:7e:0c:86 [ether] on eth0
? (192.168.3.101) at 00:0c:29:39:89:04 [ether] on eth0
? (192.168.3.11) at 00:19:99:f2:cf:e1 [ether] on eth0
? (192.168.3.7) at 00:19:99:f2:7c:e4 [ether] on eth0
? (192.168.3.20) at 00:0c:29:aa:c6:04 [ether] on eth0
? (192.168.3.3) at 00:19:99:f2:34:6c [ether] on eth0
? (192.168.3.100) at 00:19:99:f2:34:63 [ether] on eth0
? (192.168.3.150) at 00:0c:29:3d:b6:49 [ether] on eth0
? (192.168.3.14) at 00:19:99:f2:d0:30 [ether] on eth0
? (192.168.3.6) at 00:19:99:f2:34:9e [ether] on eth0
? (192.168.3.212) at 00:0c:29:00:96:62 [ether] on eth0
? (192.168.3.13) at 00:19:99:f2:d0:42 [ether] on eth0
? (192.168.3.254) at 00:00:cd:33:9d:af [ether] on eth0
? (192.168.3.5) at 00:19:99:f2:34:ba [ether] on eth0
? (192.168.3.60) at 00:0c:29:5c:10:0b [ether] on eth0
? (192.168.3.110) at 00:0c:29:2d:14:87 [ether] on eth0
? (192.168.3.4) at 00:19:99:f2:34:5c [ether] on eth0
? (192.168.3.105) at 00:0c:29:c8:76:ca [ether] on eth0
```