const affichage = document.querySelector("#affichage");
const operation = document.querySelector("#operation");

let nbActuel = ""; //chiffre tapé
let firstNumber ;
let operator ;

// Fonction de calcul
function fctCalcul(a, b, op) {
    if (op === "+") return a + b;
    if (op === "-") return a - b;
    if (op === "x") return a * b;
    if (op === "÷") return a / b;
}

/////////// On recupére les boutons appuyés ////////////////

// Chiffres
const numbers = document.querySelectorAll(".btn-number");
for (let i = 0; i < numbers.length; i++) {
    numbers[i].addEventListener("click", function () {
        nbActuel += numbers[i].innerText;      // On ajoute le chiffre cliqué
        affichage.textContent = nbActuel;        // On affiche ce qu'on tape
        if (firstNumber !== undefined && operator !== undefined) {
            operation.textContent = `${firstNumber} ${operator} ${nbActuel}`;
        } else {
            operation.textContent = nbActuel;
        }
    });
}

// Opérateurs 
const operators = document.querySelectorAll(".btn-operator");
for (let i = 0; i < operators.length; i++) {
    operators[i].addEventListener("click", function () {
        if (nbActuel !== "") { // S'il y a déjà un calcul en cours, on le fait d'abord
            if (firstNumber !== null && operator !== null) {
                firstNumber = fctCalcul(firstNumber, parseFloat(nbActuel), operator);
                affichage.textContent = firstNumber;
            } else {
                // Sinon, on commence le 1er calcul
                firstNumber = parseFloat(nbActuel);
            }
            operator = operators[i].innerText; // On garde le nouvel opérateur
            nbActuel = "";                 // On vide pour taper le prochain nombre
            operation.textContent = `${firstNumber} ${operator}`;
        }
    });
}

// , 
document.querySelector(".btn-dot").addEventListener("click", function () {
    if (!nbActuel.includes(".")) { // Si nbActuel est vide, on commence avec "0."
        if (!nbActuel) {
            nbActuel = "0.";
        } else {
            nbActuel += ".";
        }
        affichage.textContent = nbActuel;
        operation.textContent = `${firstNumber ?? ""} ${operator ?? ""} ${nbActuel}`;
    }
});

///////////////////////////////////////////////////////

// =
document.querySelector(".btn-equals").addEventListener("click", function () {
    if (firstNumber !== null && operator !== null && nbActuel !== "") {
        const secondNumber = parseFloat(nbActuel);
        const result = fctCalcul(firstNumber, secondNumber, operator);
        
        operation.textContent = `${firstNumber} ${operator} ${secondNumber} =`;
        // On prépare pour un nouveau calcul

        affichage.textContent = result;
        nbActuel = result.toString();
        firstNumber = null;
        operator = null;
    }
});

// C (reset)
document.querySelector(".btn-clear").addEventListener("click", function () {
    nbActuel = "";
    firstNumber = null;
    operator = null;
    affichage.textContent = "0";
    operation.textContent = "";
});
