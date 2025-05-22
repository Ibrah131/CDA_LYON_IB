<?php
require_once 'Text.php';

class PlainText implements Text {
    private string $content;

    public function __construct(string $text) {
        $this->content = $text;
    }

    public function transform(): string {
        return $this->content;
    }
}
