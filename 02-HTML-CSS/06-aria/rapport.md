# Rapport – Accessibilité du formulaire de réservation

## Objectif

Créer un formulaire accessible aux personnes en situation de handicap.
Le but est de garantir l'autonomie et la compréhension pour tout le monde.

---

## Structure sémantique

- Utilisation de semantique et balises HTML structurantes :
  - `<main>, <form>, <fieldset>, ... `
  pour aider les lecteurs d’écran.

---

## Attributs ARIA utilisés

- `aria-labelledby`
    pour assurer que l’intitulé est bien lu par le lecteur d’écran.

- `aria-describedby`  
  pour donner un contexte utile à l’utilisateur.

- `aria-required="true"`  
  Pour indiquer qu’un champ est obligatoire.  

- `aria-invalid="false"`  
  Pour signaler une erreur de saisie.  


- `role="form"` et `role="main"`  
  Pour une navigation structurée.

---

## Navigation clavier

- Tous les champs sont accessibles avec `Tab`.
- Ordre logique.

---

## Menu de navigation accessible

- `role="navigation"` sur `<nav>` pour le repère.
- `aria-label="Menu principal"` pour nommer cette zone.
- `role="menubar"` et `role="menuitem"` pour structurer la liste.
- `aria-current="page"` sur l’élément actif.

Pour savoir où on se trouve.

---

## Tests réalisés
- NVDA (Windows)

Résultats :
- Menu est perçu comme tel et lu correctement.
- Labels et champs bien lus.
- Indications de champ obligatoire.