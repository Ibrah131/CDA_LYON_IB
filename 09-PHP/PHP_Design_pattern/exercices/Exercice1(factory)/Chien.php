<?php
require_once 'Animal.php';

class Chien implements Animal {
    public function makeSound(): void {
        echo "Le chien wouf!\n";
    }
}
