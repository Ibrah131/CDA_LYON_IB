/////////////////////////////////////////////////////////////////////////////////////////
// Fonctions : 
/////////////////////////////////////////////////////////////////////////////////////////

//  chercher un Pokémon (saisir nom ou n°)
function chercherPokemon(saisie) {
    fetch(`https://pokeapi.co/api/v2/pokemon/${saisie}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Pokémon introuvable !');
            }
            return response.json();
        })
        .then(data => {
            pokemonActuel = data.id; // mettre à jour l'id du pokemon actuel
            afficherPokemon(data);
            chargerEvolutions(); 
        })

        .catch(error => {
            document.getElementById("resultat").innerHTML = `<p>${error}</p>`;
        });


    }

    // Afficher le pokemon récupéré
    function afficherPokemon(data) {
        document.getElementById("resultat").innerHTML = `
            <h2>${data.name} (#${data.id})</h2>
            <p> <strong> Poids: </strong> ${data.weight}</p>
            <p> <strong> Taille: </strong> ${data.height}</p>
            <p> <strong> Version normale: </strong></p>
            <img src="${data.sprites.front_default}">
            <img src="${data.sprites.back_default}">
            <p> <strong> Shiny: </strong></p>
            <img src="${data.sprites.front_shiny}">
            <img src="${data.sprites.back_shiny}">



            <p><strong>Types:</strong>
            ${data.types.map(type =>
                `<a href="#" class="type-link" data-type="${type.type.name}">${type.type.name}</a>`
            ).join(", ")}
            </p>

            <p><strong>Capacités:</strong>
            ${data.abilities.map(ability =>
                `<a href="#" class="ability-link" data-ability="${ability.ability.name}">${ability.ability.name}</a>`
            ).join(", ")}
            </p>

        `;

        //  Ajoute les événements de clic
        document.querySelectorAll('.type-link').forEach(link => {
            link.addEventListener('click', event => {
            event.preventDefault();
            const typeName = event.target.dataset.type;
            afficherPokemonParType(typeName);
            });
        });
        
        document.querySelectorAll('.ability-link').forEach(link => {
            link.addEventListener('click', event => {
            event.preventDefault();
            const abilityName = event.target.dataset.ability;
            afficherPokemonParCapacite(abilityName);
            });
        });
        

        fetch(`https://pokeapi.co/api/v2/pokemon-species/${data.name}`)
        .then(res => res.json())
        .then(speciesData => {
            document.getElementById("resultat").innerHTML += `<p><strong>Génération:</strong> ${speciesData.generation.name}</p>`;
        });
    

    }



/////////////////////////////////////////////////////////////////////////////////////////
// Programme : 
/////////////////////////////////////////////////////////////////////////////////////////
let pokemonActuel = 1; // initialisé au pokémon n°1 



// boutons suivant
document.getElementById("suivant").addEventListener("click", () => {
    if (pokemonActuel < 1025) { // Le nombre max pokedex actuel
        chercherPokemon(pokemonActuel + 1);
    } else {
        alert("C'est le dernier Pokémon !");
    }
});


// btn précédent
document.getElementById("precedent").addEventListener("click", () => {
    if (pokemonActuel > 1) {
        chercherPokemon(pokemonActuel - 1);
    } else {
        alert("C'est le premier Pokémon !");
    }
});

// rechercher
document.getElementById("searchButton").addEventListener("click", () => {
    const saisie = document.getElementById("pokemonInput").value.toLowerCase();
    chercherPokemon(saisie);
});


//////////////////////////////////////////////////////////////////////////////////////
// Bonus 1 : Afficher les generations
//////////////////////////////////////////////////////////////////////////////////////

fetch('https://pokeapi.co/api/v2/generation/')
    .then(res => res.json())
    .then(data => {
        const selectGen = document.getElementById('generations');
        data.results.forEach((gen, index) => {
            selectGen.innerHTML += `<option value="${index + 1}">${gen.name}</option>`;
        });
    });

// Afficher les pokemons quand tu sélectionnes une génération 
document.getElementById('generations').addEventListener('change', (event) => {
    const genId = event.target.value;

    if (genId !== "") {
        fetch(`https://pokeapi.co/api/v2/generation/${genId}`)
            .then(res => res.json())

            .then(data => {
                const liste = data.pokemon_species.map(p => p.name).sort();

                document.getElementById('listePokemonGeneration').innerHTML = `
                    <h3>Pokémon de la génération ${genId} :</h3>
                    <ol>${liste.map(poke =>
                         `<li>${poke}</li>`).join('')}
                    </ol>
                `;
            });

    } else {
        document.getElementById('listePokemonGeneration').innerHTML = '';
    }
});

//////////////////////////////////////////////////////////////////////////
// Bonus 2 : Les evolutions
//////////////////////////////////////////////////////////////////////////

// Afficher les évolutions à partir de "pokemonActuel"


function chargerEvolutions() {
    fetch(`https://pokeapi.co/api/v2/pokemon-species/${pokemonActuel}/`)
        .then(res => res.json())
        .then(species => fetch(species.evolution_chain.url))
        .then(res => res.json())
        .then(chainData => {
            const evolutions = obtenirEvolutions(chainData.chain); // tableau de noms

            // Fetch tous les Pokémon de la chaîne pour avoir leur image
            const fetches = evolutions.map(name =>
                fetch(`https://pokeapi.co/api/v2/pokemon/${name}`).then(res => res.json())
            );

            // Quand tous les fetch sont faits :
            Promise.all(fetches).then(pokemons => {
                const evo = pokemons.map(poke => `
                    <li>
                        <p><strong>${poke.name}</strong></p>
                        <img src="${poke.sprites.front_default}" alt="${poke.name}">
                    </li>
                `).join(' ');

                document.getElementById('evolutions').innerHTML = `<ol>${evo}</ol>`;
            });
        })
        .catch(error => {
            console.error("Erreur lors du chargement des évolutions :", error);
        });
}



// Fonction récursive pour parcourir la chaîne d'évolution
function obtenirEvolutions(chain) {
    const evolutions = [chain.species.name];
    let evoSuivante = chain.evolves_to;

    while (evoSuivante.length > 0) {
        evolutions.push(evoSuivante[0].species.name);
        evoSuivante = evoSuivante[0].evolves_to;
    }

    return evolutions;
}

//////////////////////////////////////////////////////////////////////////
// Bonus 3 : Les 
//////////////////////////////////////////////////////////////////////////


function afficherPokemonParType(typeName) {
    fetch(`https://pokeapi.co/api/v2/type/${typeName}`)
      .then(res => res.json())
      .then(data => {
        const liste = data.pokemon.map(p => p.pokemon.name).sort();

        document.getElementById('listePokemonGeneration').innerHTML = `
            <h3>Pokémon de type ${typeName} :</h3>
            <ol>
                ${liste.map(name => `<li><a href="#" onclick="chercherPokemon('${name}')">${name}</a></li>`).join('')}
            </ol>
        `;
      });
}

  
function afficherPokemonParCapacite(abilityName) {
    fetch(`https://pokeapi.co/api/v2/ability/${abilityName}`)
      .then(res => res.json())
      .then(data => {
        const liste = data.pokemon.map(p => p.pokemon.name).sort();

        document.getElementById('listePokemonGeneration').innerHTML = `
            <h3>Pokémon avec la capacité ${abilityName} :</h3>
            <ol>
                ${liste.map(name => `<li><a href="#" onclick="chercherPokemon('${name}')">${name}</a></li>`).join('')}
            </ol>
        `;
      });
}


