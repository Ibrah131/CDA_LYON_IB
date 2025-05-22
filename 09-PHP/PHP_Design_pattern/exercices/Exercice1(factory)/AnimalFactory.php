<?php
require_once 'Animal.php';

abstract class AnimalFactory {
    abstract public function createAnimal(): Animal;
}
