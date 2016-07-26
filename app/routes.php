<?php

$controllers = 'Willyfog\Http\Controllers';

$app->group('/api/v1', function () use ($controllers) {
    $namespace = "$controllers\\V1";
    $this->get('/', "$namespace\\HomeController:index");

    $this->get('/users', "$namespace\\UserController:index");
    $this->get('/users/{id}', "$namespace\\UserController:show");
    $this->post('/users', "$namespace\\UserController:create");
    $this->put('/users/{id}', "$namespace\\UserController:update");
    $this->delete('/users/{id}', "$namespace\\UserController:destroy");

    $this->get('/equivalences', "$namespace\\EquivalenceController:listAll");
});
