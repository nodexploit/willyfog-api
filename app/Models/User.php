<?php


namespace Willyfog\Models;

class User extends BaseModel
{
    protected $_table_name = 'user';

    protected $_hidden = ['digest'];

    public $id;
    public $name;
    public $surname;
    public $nif;
    public $email;
    public $digest;
    public $created_at;
    public $updated_at;
}
