<?php
require_once 'AnimalFactory.php';
require_once 'Chien.php';

class ChienFactory extends AnimalFactory {
    public function createAnimal(): Animal {
        return new Chien();
    }
}
