# Architecture 

## Vue d’ensemble
- **Frontend**: Create React App + React Router + MUI.
- **Backend**: Spring Boot (JAR), écoute par défaut sur `http://localhost:8080`.
- **Échanges**: REST JSON. En dev, CRA proxy `frontend/package.json` → `"proxy": "http://localhost:8080"`.

## Frontend
- **Pages**:
    - `/employees` → liste, recherche, pagination, actions (EDIT/DELETE).
    - `/add-employee` → création.
    - `/edit-employee/:id` → édition.
- **Composants**:
    - `EmployeeList.js`, `EmployeeForm.js`.
- **Appels API**: `axios.defaults.baseURL = '/api'` (ou `/api/v1` si précisé dans `openapi.yaml`).
- **Responsive**: table scrollable horizontal, inputs full width.

## Backend
- REST endpoints (alignés avec `openapi.yaml`) :
    - `GET /api/employees`
    - `GET /api/employees/{id}`
    - `POST /api/employees`
    - `PUT /api/employees/{id}`
    - `DELETE /api/employees/{id}`
    - *(optionnel)* `GET /api/departments` pour le select du formulaire.

## Démarrage
- **Backend**: `java -jar backend/target/<jar>.jar` (8080).
- **Frontend**: `npm run frontend` (3000).  
  Appeler l’API via des **URLs relatives** (ex: `/api/employees`) → proxysées vers 8080.

## Choix
- Pagination côté front (simple & conforme maquette).
- Validation formulaire légère (email/âge).
- Séparation claire liste/formulaire (UX basique).


# How to run

## Run locally

### Backend
(powershell)
$jar = Get-ChildItem backend\target\*.jar | Select-Object -First 1
java -jar $jar.FullName

### Frontend
npm run frontend
# si 3000 est occupé:
$env:PORT=3001; npm run frontend

