<?php
require_once 'AnimalFactory.php';
require_once 'Chat.php';

class ChatFactory extends AnimalFactory {
    public function createAnimal(): Animal {
        return new Chat();
    }
}
