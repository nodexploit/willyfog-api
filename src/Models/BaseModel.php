<?php


namespace Willyfog\Models;

class BaseModel
{
    /**
     * @var \PDO
     */
    protected $pdo;

    /**
     * Mass assignable columns.
     *
     * @var array
     */
    protected $fillable;

    /**
     * Table name of the model.
     *
     * @var string
     */
    protected $table_name;

    public function __construct($ci)
    {
        $this->pdo = $ci->get('pdo');
    }

    public function create(array $attributes = [])
    {
        $attributes = array_filter($attributes, function ($k) {
            return in_array($k, $this->fillable);
        }, ARRAY_FILTER_USE_KEY);

        $column_names = array_keys($attributes);
        $bindings = array_map(function ($value) {
            return ":$value";
        }, $column_names);

        $stm = $this->pdo->prepare(
            'INSERT INTO ' . $this->table_name .' (' . implode(',', $column_names) . ') VALUES (' . implode(',', $bindings) . ')'
        );

        return $stm->execute(array_combine($bindings, array_values($attributes)));
    }
}
