<?php
require_once 'TextDecorator.php';

class UpperCaseDecorator extends TextDecorator {
    public function transform(): string {
        return mb_strtoupper($this->innerText->transform());
    }
}