<?php
require_once 'Text.php';

abstract class TextDecorator implements Text {
    protected Text $innerText;

    public function __construct(Text $text) {
        $this->innerText = $text;
    }

    abstract public function transform(): string;
}
