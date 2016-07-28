<?php


namespace Willyfog\Models;

class Equivalence extends BaseModel
{
    protected $_table_name = 'equivalence';

    public $id;
    public $subject_id;
    public $subject_eq_id;
    public $created_at;
    public $updated_at;
}
