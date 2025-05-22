// Récupération du form
const form = document.getElementById("reservationForm");
const nameInput = document.getElementById("name");
const dateInput = document.getElementById("date");
const timeInput = document.getElementById("time");
const participantsInput = document.getElementById("participants");

// Récupération des zones d'erreur
const nameError = document.getElementById("nameError");
const dateError = document.getElementById("dateError");
const timeError = document.getElementById("timeError");
const participantsError = document.getElementById("participantsError");

// Chargement du stockage local (s'il y'a)
const reservations = JSON.parse(localStorage.getItem("reservations")) || [];


////////////////////////////////////////////////////////////////////////////
// Empêche la sélection de dates antérieures 
dateInput.min = new Date().toISOString().slice(0, 10);


// Enregistrer dans le localStorage
function saveReservations() {
  localStorage.setItem("reservations", JSON.stringify(reservations));
}

////////////////////////////////////////////////////////////////////////////
// Bonus 
function updateReservedDates() {
    const dateField = document.getElementById("date");
  
    dateField.addEventListener("input", () => {
      const selectedDate = dateField.value;
  
      // Filtrer toutes les réservations pour cette date
      const count = reservations.filter(r => r.date === selectedDate).length;
  
      // Si les 2 créneaux sont déjà réservés -> rouge
      dateField.style.backgroundColor = (count >= 2) ? "#fdd" : "";
    });
  }
  

////////////////////////////////////////////////////////////////////////////
// Affiche les réservations 
function displayReservations() {
  const tbody = document.querySelector("#reservationsTable tbody");
  let html = "";

  for (let i = 0; i < reservations.length; i++) {
    html += `
      <tr>
        <td>${reservations[i].name}</td>
        <td>${reservations[i].date}</td>
        <td>${reservations[i].time}</td>
        <td>${reservations[i].participants}</td>
        <td><button class="delete-btn" onclick="deleteReservation(${i})">Supprimer</button></td>
      </tr>`;
  }

  tbody.innerHTML = html;
}


// Supprime une réservation
function deleteReservation(index) {
  reservations.splice(index, 1);
  saveReservations();            
  displayReservations();         
  updateReservedDates();         
}

////////////////////////////////////////////////////////////////////////////
// formulaire
form.addEventListener("submit", function (e) {
  e.preventDefault();

  // Reset messages d'erreur
  nameError.textContent = "";
  dateError.textContent = "";
  timeError.textContent = "";
  participantsError.textContent = "";

  const name = nameInput.value;
  const date = dateInput.value;
  const time = timeInput.value;
  const participants = parseInt(participantsInput.value);
  const nameRegex = /^[A-Za-zÀ-ÿ]{3,}$/;
  const timeRegex = /^(09:00|13:30)$/;

  let valid = true;


  ////////////////////////////////////////////////////////////////////////////
  // Vérifier le nom
  if (!nameRegex.test(name)) {
    nameError.textContent = "Le nom doit contenir au moins 3 lettres et uniquement des lettres.";
    valid = false;
  }

  // Vérifier la date
  if (!date) {
    dateError.textContent = "La date est obligatoire.";
    valid = false;
  }

  // Vérifier l'heure
  if (!timeRegex.test(time)) {
    timeError.textContent = "L’heure doit être 09:00 ou 13:30.";
    valid = false;
  }

  // Vérifier créneau déjà pris
  if (reservations.some(r => r.date === date && r.time === time)) {
    timeError.textContent = "Ce créneau est déjà réservé.";
    valid = false;
  }

  // Vérifier les participants
  if (isNaN(participants) || participants < 1 || participants > 10) {
    participantsError.textContent = "Le nombre doit être entre 1 et 10.";
    valid = false;
  }

  // Si erreur
  if (!valid) return;

  // Nouvelle réservation
  const newRes = { name, date, time, participants };
  reservations.push(newRes);
  saveReservations();
  displayReservations();
  updateReservedDates();
  form.reset();
});


// Affiche les réservations au chargement
displayReservations();
updateReservedDates();
