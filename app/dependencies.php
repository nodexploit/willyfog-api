<?php

$container = $app->getContainer();

$container['logger'] = function ($c) {
    $settings = $c->get('settings')['logger'];
    $logger = new Monolog\Logger($settings['name']);
    $logger->pushProcessor(new Monolog\Processor\UidProcessor());
    $logger->pushHandler(new Monolog\Handler\StreamHandler($settings['path'], Monolog\Logger::DEBUG));

    return $logger;
};

$container['pdo'] = function ($c) {
    $database = $c->get('settings')['database'];
    $dsn = "mysql:dbname=${database['name']};host=${database['host']};charset=utf8";

    return new PDO($dsn, $database['username'], $database['password']);
};

/*
 * This OAuth2 Server only validates request. So we need basic functionality.
 */
$container['oauth'] = function ($c) {
    $database = $c->get('settings')['database'];
    $dsn = "mysql:dbname=${database['name']};host=${database['host']};charset=utf8";
    
    $storage = new \Willyfog\Lib\OAuth2\Pdo([
        'dsn' => $dsn,
        'username' => $database['username'],
        'password' => $database['password']
    ]);
    
    return new OAuth2\Server($storage);
};

$container['errorHandler'] = function ($c) {
    return function ($request, $response, $exception) use ($c) {
        if ($exception instanceof \Willyfog\Http\Exceptions\ResourceNotFound) {
            return $response->withJson('Resource not found', 404);
        } else {
            return $response;
        }
    };
};
