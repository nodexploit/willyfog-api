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
    protected $fillable = [];

    /**
     * Table name of the model.
     *
     * @var string
     */
    protected $table_name;

    /**
     * Page size of the pagination.
     *
     * @var int
     */
    protected $page_size = 10;

    public function __construct($ci)
    {
        $this->pdo = $ci->get('pdo');
    }

    public function paginate($page = null)
    {
        $table_name = $this->table_name;
        $page_size = $this->page_size;
        if ($page === null) {
            $page = 0;
        }

        $stm = $this->pdo->prepare(
            "SELECT * FROM $table_name LIMIT $page_size OFFSET $page;"
        );
        $stm->execute();

        $results = $stm->fetchAll(\PDO::FETCH_ASSOC);

        return [
            'data'          => $results,
            'page_size'     => $page_size,
            'page'          => $page
        ];
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
        $table_name = $this->table_name;

        $stm = $this->pdo->prepare(
            "INSERT INTO $table_name " . implode(',', $column_names) . ') VALUES (' . implode(',', $bindings) . ')'
        );

        return $stm->execute(array_combine($bindings, array_values($attributes)));
    }

    public function update($id, array $attributes = [])
    {
        $attributes = array_filter($attributes, function ($v, $k) {
            return strlen($v) > 0;
        }, ARRAY_FILTER_USE_BOTH);

        $column_names = array_keys($attributes);
        $bindings = array_map(function ($value) {
            return ":$value";
        }, $column_names);
        $sets = array_map(function ($binding, $value) {
            return "$binding=$value";
        }, $column_names, $bindings);
        $table_name = $this->table_name;

        $stm = $this->pdo->prepare(
            "UPDATE $table_name SET " . implode(',', $sets) . " WHERE id = $id"
        );

        return $stm->execute(array_combine($bindings, array_values($attributes)));
    }

    public function delete($id)
    {
        $table_name = $this->table_name;

        $stm = $this->pdo->prepare(
            "DELETE FROM $table_name WHERE id = $id"
        );

        return $stm->execute();
    }
}
