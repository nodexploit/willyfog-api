<?php


namespace Willyfog\Models;

class User extends BaseModel
{
    protected $fillable = ['name', 'surname', 'nif', 'email', 'digest'];

    protected $table_name = 'user';
}
