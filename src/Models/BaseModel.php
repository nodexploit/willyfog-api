<?php


namespace Willyfog\Models;

class BaseModel
{
    /**
     * @var \PDO
     */
    protected $pdo;

    public function __construct($ci)
    {
        $this->pdo = $ci->get('pdo');
    }

    public function greet()
    {
        return "hello";
    }
}
