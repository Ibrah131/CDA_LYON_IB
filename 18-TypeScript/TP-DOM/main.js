/* =========================================================
   TP DOM
   ========================================================= */

// --------- Sélection des éléments ---------
const elThemeToggle = document.getElementById('theme-toggle');

const elDesc = document.getElementById('desc');
const elBtnDesc = document.getElementById('btn-desc');

const elPseudo = document.getElementById('pseudo');
const elPseudoError = document.getElementById('pseudo-error');

const elAvatars = document.getElementById('avatars');
const elNext = document.getElementById('btn-next');

const elSetup = document.getElementById('setup');
const elProfile = document.getElementById('profile');
const elProfileAvatar = document.getElementById('profile-avatar');
const elProfileName = document.getElementById('profile-name');
const elProfileDesc = document.getElementById('profile-desc');
const elReset = document.getElementById('btn-reset');

// --------- Données et état ---------
const DESCRIPTIONS = [
  "Claire Obscure",
  "Trouble de rature",
  "Courbera Eiffel",
];

const state = {
  theme: 'light',
  descIndex: null,     // indice dans DESCRIPTIONS
  pseudo: '',
  pseudoValid: false,
  avatarId: null,      // 1 , 2 , 3
};

// --------- Fonctions ---------

/** Retourne un index aléatoire différent de previous */
function getNextIndex(previous){
  if (DESCRIPTIONS.length === 1) return 0;
  let i;
  do {
    i = Math.floor(Math.random() * DESCRIPTIONS.length);
  } while (i === previous);
  return i;
}

/** Valider le pseudo : lettres uniquement + min 3 */
function validatePseudo(value){
  const regex = /^[a-zA-Z]+$/;
  if (!value) return {valid:false, msg:''};  // pas d'erreur quand vide
  if (!regex.test(value)) return {valid:false, msg:'Uniquement des lettres (a-z / A-Z).'};
  if (value.length < 3) return {valid:false, msg:'Au moins 3 lettres.'};
  return {valid:true, msg:''};
}

/** Active/désactive le btn Suivant */
function updateNextButton(){
  const ok = state.pseudoValid && state.descIndex !== null && state.avatarId !== null;
  elNext.disabled = !ok;
}

/** Applique le thème (texte du bouton + data-theme) */
function applyTheme(){
  document.body.setAttribute('data-theme', state.theme);
  elThemeToggle.textContent = state.theme === 'dark' ? '🌙 Thème sombre' : '🌞 Thème clair';
}

// --------- Événements ---------

// Thème
elThemeToggle.addEventListener('click', () => {
  state.theme = state.theme === 'light' ? 'dark' : 'light';
  applyTheme();
});

// Description (au clic)
elBtnDesc.addEventListener('click', () => {
  const next = getNextIndex(state.descIndex);
  state.descIndex = next;
  elDesc.textContent = DESCRIPTIONS[next];
  elDesc.style.color = "inherit";
  updateNextButton();
});

// Pseudo (validation en direct)
elPseudo.addEventListener('input', () => {
  state.pseudo = elPseudo.value.trim();
  const {valid, msg} = validatePseudo(state.pseudo);
  state.pseudoValid = valid;

  // style + messages
  elPseudo.classList.toggle('valid', valid);
  elPseudo.classList.toggle('invalid', !valid && state.pseudo.length > 0);
  elPseudoError.textContent = msg;

  updateNextButton();
});

// Avatars
elAvatars.addEventListener('click', (e) => {
  const btn = e.target.closest('.avatar');
  if (!btn) return;

  // désélectionner les autres
  document.querySelectorAll('.avatar.selected').forEach(b => b.classList.remove('selected'));
  // sélectionner celui-ci
  btn.classList.add('selected');
  state.avatarId = btn.getAttribute('data-id');

  updateNextButton();
});

// Suivant -> afficher la carte profil
elNext.addEventListener('click', () => {
  if (elNext.disabled) return;

  const selectedImg = document.querySelector('.avatar.selected img');
  elProfileAvatar.src = selectedImg ? selectedImg.src : '';

  elProfileName.textContent = state.pseudo;
  elProfileDesc.textContent = DESCRIPTIONS[state.descIndex];

  elSetup.classList.add('hidden');
  elProfile.classList.remove('hidden');
});



// Reset -> revenir au formulaire
elReset.addEventListener('click', () => {
  // état
  state.descIndex = null;
  state.pseudo = '';
  state.pseudoValid = false;
  state.avatarId = null;

  // UI
  elDesc.textContent = '(Une description apparaîtra)';
  elDesc.style.color = ""; // revient à la couleur par défaut
  elPseudo.value = '';
  elPseudo.classList.remove('valid', 'invalid');
  elPseudoError.textContent = '';
  document.querySelectorAll('.avatar.selected').forEach(b => b.classList.remove('selected'));
  elNext.disabled = true;

  elProfile.classList.add('hidden');
  elSetup.classList.remove('hidden');

  // relancer une première description
  initDescription();
});

// --------- Initialisation ---------
function initDescription(){
  const i = getNextIndex(null);
  state.descIndex = i;
  elDesc.textContent = DESCRIPTIONS[i];
  elDesc.style.color = "inherit";
}
function initTheme(){
  state.theme = 'light';
  applyTheme();
}

window.addEventListener('DOMContentLoaded', () => {
  initTheme();
  initDescription();
  updateNextButton();
});
