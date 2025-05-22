<?php
require_once 'Animal.php';

class Chat implements Animal {
    public function makeSound(): void {
        echo "Le chat miaule\n";
    }
}
