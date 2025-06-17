<?php

require 'vendor/autoload.php';

use MongoDB\Client;
use MongoDB\Driver\Exception\Exception as MongoException;

try {
    // Connexion à MongoDB
    $client = new Client("mongodb://localhost:27017");

    // Sélection de la base de données "cinema"
    $db = $client->selectDatabase("cinema");

    // Sélection ou création automatique de la collection "films_test"
    $collectionTest = $db->selectCollection("films_test");

    // ===== Insertion d'un document =====
    $document = [
        'titre' => 'Les 1001 recettes de Fred',
        'annee_production' => 2025,
        'genres' => ['Drame', 'Romance', 'Aventure']
    ];

    $result = $collectionTest->insertOne($document);

    echo "✅ Document inséré avec l'ID : " . $result->getInsertedId();

} catch (MongoException $e) {
    echo "❌ Erreur MongoDB : " . $e->getMessage();
    exit;
}
