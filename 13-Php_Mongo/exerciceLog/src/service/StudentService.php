<?php

namespace src\service;

use src\model\Student;
use src\repository\StudentRepository;

// [1] Connexion à MongoDB via Composer autoload
require_once __DIR__ . '/../../vendor/autoload.php';

use MongoDB\Client;

class StudentService
{
    // Définition des regex à utiliser sous forme de constantes
    const DATE_PATTERN = "/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/";
    const EMAIL_PATTERN = "/^[\w\-\.]+@([\w-]+\.)+[\w-]{2,}$/";

    private $logCollection;
    public function __construct(private StudentRepository $studentRepository)
    {
        // 1. Connexion MongoDB et sélection de la collection
        $client = new Client("mongodb://localhost:27017");
        $this->logCollection = $client->exerciceLog->logs;
    }

    // Permet d'afficher les étudiants
    function displayStudents(): void
    {
        $students = $this->studentRepository->findAll();
        echo "=== Affichage des étudiants ===\n";
        if(empty($students))
            echo "Aucun étudiant";

        foreach ($students as $student) {
            // On affiche chaque étudiant récupéré depuis la base de données
            echo $student . PHP_EOL;
        }

        // 2. Log automatique lors de l'affichage
        $this->log('DEBUG', 'Affichage', 'Affichage des étudiants');
    }

    // Créé un étudiant et effectue des vérifications
    function createStudent(): bool
    {
        echo "Saisir le prénom : ";
        $firstname = readline();

        if (empty($firstname)) {
            echo "Prénom incorrect";
            return false;
        }

        echo "Saisir le nom : ";
        $lastname = readline();

        if (empty($lastname)) {
            echo "Nom incorrect";
            return false;
        }

        echo "Saisir date naissance (aaaa-mm-jj): ";
        $dob = readline();

        if (!preg_match(self::DATE_PATTERN, $dob)) {
            echo "Date incorrecte";
            return false;
        }

        echo "Saisir email: ";
        $email = readline();

        if (!preg_match(self::EMAIL_PATTERN, $email)) {
            echo "Email incorrect";
            return false;
        }

        return $this->studentRepository->save(new Student(null, $firstname, $lastname, $dob, $email));
    }

    // Permet d'éditer un étudiant
    function editStudent(): void
    {
        echo "Saisir l'id de l'étudiant: ";
        $id = (int)readline();

        // On récupère l'étudiant en base de données s'il existe
        $student = $this->studentRepository->findById($id);

        // Si l'étudiant n'est pas trouvé, on quitte la fonction
        if (!$student) {
            echo "Aucun étudiant trouvé avec l'id {$id}";
            return;
        }
        readline();

        echo "Saisir prénom: ";
        $firstname = readline();

        // Si l'utilisateur ne saisit rien, firstname garde son ancienne valeur
        if (!empty($firstname)) {
            $student->firstname = $firstname;
        }

        echo "Saisir nom: ";
        $lastname = readline();

        if (!empty($lastname)) {
            $student->lastname = $lastname;
        }

        echo "Saisir date naissance: ";
        $dob = readline();

        if (!empty($dob) && !preg_match(self::DATE_PATTERN, $dob)) {
            $student->date_of_birth = $dob;
        }

        echo "Saisir email: ";
        $email = readline();

        if (!empty($email) && !preg_match(self::EMAIL_PATTERN, $email)) {
            $student->email = $email;
        }

        $this->studentRepository->update($student);
    }

    // Supprime un étudiant par son id
    function deleteStudent(): void
    {
        echo "Saisir l'id de l'étudiant: ";
        $id = (int)readline();

        $success = $this->studentRepository->deleteById($id);

        if($success)
            echo "L'étudiant avec l'ID $id a été supprimé.\n";
        else
            echo "L'id est incorrecte.\n";
    }

    function searchStudentsByIdentity(): void {
        // On prépare le paramètre pour le like
        echo "Saisir le nom ou prénom de l'étudiant: ";
        $input = '%' . readline() . '%';

        $students = $this->studentRepository->findAllByName($input);

        echo "=== Affichage de tout étudiants ayant $input dans leur nom ou prénom === \n";
        foreach ($students as $student) {
            // On affiche chaque étudiant récupéré depuis la base de données
            echo $student . PHP_EOL;
        }
    }

    // 2. Insérer un log dans MongoDB
    private function log(string $type, string $operation, string $message): void
    {
        $this->logCollection->insertOne([
            'type' => $type,
            'operation' => $operation,
            'message' => $message,
            'created_at' => new \MongoDB\BSON\UTCDateTime()
        ]);
    }

    // 3. Méthode pour afficher les 10 derniers logs
    public function afficherLogs(): void
    {
        $logs = $this->logCollection->find([], [
            'sort' => ['created_at' => -1],
            'limit' => 10
        ])->toArray();

        echo "=== Derniers logs ===\n";
        foreach ($logs as $log) {
            echo "{$log['type']} | {$log['operation']} | {$log['message']}\n";
        }
    }

    // 4. Méthode pour vider la collection de logs
    public function viderLogs(): void
    {
        $this->logCollection->deleteMany([]);
        echo "Tous les logs ont été supprimés!\n";
    }
}
