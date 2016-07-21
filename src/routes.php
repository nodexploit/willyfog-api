<?php

$controllers = 'Willyfog\Http\Controllers';
$app->group('/api/v1', function () use ($controllers) {
    $namespace = "$controllers\\V1";
    $this->get('/', "$namespace\\HomeController:index");
});
