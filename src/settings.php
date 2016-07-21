<?php
return [
    'settings' => [
        'displayErrorDetails' => true, // set to false in production
        'addContentLengthHeader' => false, // Allow the web server to send the content-length header
        
        'logger' => [
            'name' => 'slim-app',
            'path' => __DIR__ . '/../logs/app.log',
        ],
        
        'database' => [
            'host'      => '127.0.0.1',
            'name'      => 'willyfog_db',
            'username'  => 'root',
            'password'  => 'root'
        ]
    ],
];
