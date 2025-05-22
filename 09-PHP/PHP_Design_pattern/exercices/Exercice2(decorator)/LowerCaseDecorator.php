<?php
require_once 'TextDecorator.php';

class LowerCaseDecorator extends TextDecorator {
    public function transform(): string {
        return mb_strtolower($this->innerText->transform());
    }
}
