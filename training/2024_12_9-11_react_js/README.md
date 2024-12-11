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

### Creation d'un projet type Checklist, les TPs

l'ensemble des exercices sont inspiré du site [todomvc.com](todomvc.com)

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

cd dans le projet
puis
`npm install`
puis
`npm run dev`, lance l'app dans un navigateur et surveille les modifications du code pour faire un build à la volée

en Typescript:  

let element: ReactNode; // type à utiliser pour un element react  

react utilise un algo pour faire le diff entre 2 "versions" de la page avant de la mettre à jour en modifiant uniquement ce qui a changé, parfois on est amené dans le code à rajouter une info en plus pour faciliter cela, exemple avec les list et le mot clé 'key' pour la mise à jour d'un tableau

les events

usesState pour mettre à jour un composant, exemple une horloge...

## Frameworks

Next.js, inclu un routeur (recommandé pour site web)
Redux, plus adapté pour des projets plus grands que l'exemple de Todo list de la formation,  
npmtrends: <https://npmtrends.com/react-vs-reduc-vs-redux> la tendance indique que redux est moins utiliser, et explique que react à inclu en natif des choses que fait redux

### redux tool kit (RTK)

recommandé par les éditeurs de Redux, [RTK](https://redux-toolkit.js.org/tutorials/quick-start#usage-summary) permet de simplifier la gestion de projet au niveau des interactions, s'installe avec Redux inclu dedans
> npm i @reduxjs/toolkit

grosso-modo on reprend le pattern d'échange d'évenements, mais ça s'appele des actions, et on passe par un couple [store+dispatcher]
