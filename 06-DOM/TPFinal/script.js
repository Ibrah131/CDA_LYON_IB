let motADeviner = ""; // sera rempli après l'appel à l'API
let longueurMot = 0; // Longueur du mot à deviner (ex: 4 lettres)

let essaisRestants = 5; // Nombre d’essais 

let lettresTapees = []; // Stocke les lettres que le joueur tape 

let ligneActuelle = 0; // ligne actuelle (commence à 0, monte à chaque essai)

const lignes = document.querySelectorAll(".ligne"); //  Récupère toutes les lignes 

const boutons = document.querySelectorAll(".clavier button"); // Récupère tous les boutons du clavier virtuel


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Fonction principale : gère toutes les touches
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
function gererTouche(touche) {
    // Récupère les cases de la ligne en cours
    const cases = lignes[ligneActuelle].querySelectorAll(".case");
  
    //  Si c’est une lettre (a-z) et qu’il reste de la place
    if (/^[a-z]$/i.test(touche) && lettresTapees.length < longueurMot) {
      lettresTapees.push(touche.toLowerCase()); // On ajoute la lettre

      // On l’affiche dans la case 
      cases[lettresTapees.length - 1].textContent = touche.toUpperCase();
    }
  
    // Si "backspace" -> efface la dernière lettre
    if (touche === "backspace") {
      if (lettresTapees.length > 0) {
        // Vide la case correspondante
        cases[lettresTapees.length - 1].textContent = "";
        lettresTapees.pop(); // Retire la lettre du tableau
      }
    }
  
    // Si "enter" -> on vérifie le mot
    if (touche === "enter") {
      // Si  mot  incomplet, on bloque
      if (lettresTapees.length !== longueurMot) {
        alert("Mot incomplet !");
        return;
      }
  
      // On reconstruit le mot à partir des lettres tapées
      const motSaisi = lettresTapees.join("");
  
      // On vérifie chaque lettre
      for (let i = 0; i < longueurMot; i++) {
        const caseCourante = cases[i];
        const lettre = lettresTapees[i];
  
        // Bien placée (même lettre, même position)
        if (lettre === motADeviner[i]) {
          caseCourante.classList.add("valide"); // vert
        }
        // Mal placée (présente mais ailleurs)
        else if (motADeviner.includes(lettre)) {
          caseCourante.classList.add("present"); // jaune
        }
        // Pas dans le mot
        else {
          caseCourante.classList.add("faux"); // gris
        }
      }
  
      // Si mot correct 
      if (motSaisi === motADeviner) {
        setTimeout(() => alert("Bravo ! Mot trouvé : " + motADeviner), 100);
        return; 
      }
  
      // Sinon on passe à la ligne suivante
      ligneActuelle++;
      lettresTapees = []; // On vide les lettres pour la prochaine ligne
  
      // Si nombre d’essais depassé -> game over
      if (ligneActuelle >= essaisRestants) {
        setTimeout(() => alert("Perdu ! Le mot était : " + motADeviner), 100);
      }
    }
  }
  
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Appel l'API pour récupérer un mot
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

fetch(`https://random-word-api.herokuapp.com/word?length=4`)
  .then(response => response.json())
  .then(data => {
    motADeviner = data[0].toLowerCase(); // récupère le mot
    longueurMot = motADeviner.length; // maintenant on peut définir la longueur
    document.getElementById("mot-secret").textContent = motADeviner; // affiche dans la zone secrète

    // Clavier physique 
    document.addEventListener("keydown", (event) => {
      gererTouche(event.key); 
    });

    // Clavier virtuel
    boutons.forEach(btn => {
      btn.addEventListener("click", () => {
        const val = btn.textContent.toLowerCase(); // Texte du bouton (ex: "A")

        if (val === "enter") {
            gererTouche("enter")

        } else if (val === "backspace"){ 
            gererTouche("backspace")

        } else {
            gererTouche(val)
        };
      });
    });

  })

  .catch(error => {
    console.error("Erreur API, on prend un mot par défaut");
    motADeviner = "chat"; // Si l'API ne marche pas, mot par défaut
    longueurMot = motADeviner.length;
    document.getElementById("mot-secret").textContent = motADeviner;

    // Clavier physique 
    document.addEventListener("keydown", (event) => {
      gererTouche(event.key); 
    });

    // Clavier virtuel
    boutons.forEach(btn => {
      btn.addEventListener("click", () => {
        const val = btn.textContent.toLowerCase(); // Texte du bouton (ex: "A")

        if (val === "enter") {
            gererTouche("enter")

        } else if (val === "suppr"){ 
            gererTouche("backspace")

        } else {
            gererTouche(val)
        };
      });
    });

  });
