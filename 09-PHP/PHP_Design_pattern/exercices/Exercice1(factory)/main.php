<?php
require_once 'ChatFactory.php';
require_once 'ChienFactory.php';

function testFactory(AnimalFactory $factory) {
    $animal = $factory->createAnimal();
    $animal->makeSound();
}

testFactory(new ChatFactory());
testFactory(new ChienFactory());
