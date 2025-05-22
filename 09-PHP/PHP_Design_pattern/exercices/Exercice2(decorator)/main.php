<?php
require_once 'PlainText.php';
require_once 'UpperCaseDecorator.php';
require_once 'LowerCaseDecorator.php';
require_once 'PrefixDecorator.php';
require_once 'SuffixDecorator.php';

$text = new PlainText("TA poêle est très élégante!");

$text = new PrefixDecorator($text, "~~ ");
$text = new SuffixDecorator($text, " <<");
$text = new UpperCaseDecorator($text);

echo $text->transform();  



