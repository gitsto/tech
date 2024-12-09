# Notes de formation

[Orsys : ReactJS, maîtriser le framework JavaScript de Facebook](https://www.orsys.fr/formation/tjs)
node
## Formateur

* Github: [Romain Bohdanowicz](https://github.com/bioub)

## Références

Officiel | Description
---------|----------
[React](https://react.dev/) | Site officiel de la librairie javascript React
[React Router](https://reactrouter.com/) | Facilite la composition d'un site par composant
[Vercel](https://vercel.com/) | Hébergeur could de projet web javascript, contributeurs de React, créateur du framework Next qui 'pousse' à l'utilisation de Next...

### Intro du cours

A partir du support de cours react.pdf,

* [Exemple select classique](https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select)
* [React Select](https://react-select.com/home)

fnm gestionnaire de package codé en rust, disponible sur win, mac et linux, compilé et rapide...

#### creation d'un projet

```shell
sto@STOJISAS-5420B:~/sndbx/formation/exemples$ npx create-vite
Need to install the following packages:
create-vite@6.0.1
Ok to proceed? (y) y

✔ Project name: … hello-react
? Select a framework: › - Use arrow-keys. Return to submit.
    Vanilla
    Vue
    React
    Preact
❯   Lit     <---- Google
    Svelte  <------ performant
    Solid   <------ performant
    Qwik
    Angular <------ Google
    Others

✔ Select a framework: › React
? Select a variant: › - Use arrow-keys. Return to submit.
❯   TypeScript
    TypeScript + SWC
    JavaScript
    JavaScript + SWC
    React Router v7 ↗

```
Note: babeljs outil qui traduit une syntaxe en javascript


`npm run dev`, lance l'app dans un navigateur et surveille les modifications du code pour faire un build à la volée
