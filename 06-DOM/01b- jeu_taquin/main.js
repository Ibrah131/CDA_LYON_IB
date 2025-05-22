const t0 = document.getElementById("t0");
const t1 = document.getElementById("t1");
const t2 = document.getElementById("t2");
const t3 = document.getElementById("t3");
const t4 = document.getElementById("t4");
const t5 = document.getElementById("t5");
const t6 = document.getElementById("t6");
const t7 = document.getElementById("t7");
const t8 = document.getElementById("t8");

////////////////////////////////////////////////////////////////////////

// On met toutes les cases dans un tableau
const cases = [t0, t1, t2, t3, t4, t5, t6, t7, t8];

// Mélanger les valeurs au démarrage
const valeurs = ['1', '2', '3', '4', '5', '6', '7', '8', ''];
valeurs.sort(() => Math.random() - 0.5); 



// Appliquer les valeurs mélangées dans les cases
for (let i = 0; i < 9; i++) {
  cases[i].textContent = valeurs[i];

  if (valeurs[i] === '') {
    cases[i].classList.add("vide");
  } else {
    cases[i].classList.remove("vide");
  }
}


////////////////////////////////////////////////////////////////////////

// Gérer les cases
for (let i = 0; i < 9; i++) {
  cases[i].addEventListener("click", function () {

    //Chercher la case cliquée
    let col = 0;                                       // colonne gauche
    if (i === 1 || i === 4 || i === 7) col = 1;        // Si la case est au milieu 
    if (i === 2 || i === 5 || i === 8) col = 2;        // Si elle est à droite


    let row = 0;                                    // 1ere ligne
    if (i >= 3 && i <= 5) row = 1;                  // 2ème ligne
    if (i >= 6 && i <= 8) row = 2;                  // 3ème ligne


    // determiner les voisins : 
    const voisins = [];

    if (row > 0) voisins.push(i - 3);       // Haut
    if (row < 2) voisins.push(i + 3);       // Bas
    if (col > 0) voisins.push(i - 1);       // Gauche
    if (col < 2) voisins.push(i + 1);       // Droite


    // verifier quel voisin est "vide", echanger avec lui
    for (let j = 0; j < voisins.length; j++) {
      const voisin = voisins[j];

      if (cases[voisin].classList.contains("vide")) {
        cases[voisin].textContent = cases[i].textContent;
        cases[i].textContent = "";
        cases[voisin].classList.remove("vide");
        cases[i].classList.add("vide");
        break;
      }
    }
  });
}

////////////////////////////////////////////////////////////////////////
// Bouton recommencer 
const boutonRecommencer = document.getElementById("recommencer");

boutonRecommencer.addEventListener("click", function () {
  location.reload(); 
});
