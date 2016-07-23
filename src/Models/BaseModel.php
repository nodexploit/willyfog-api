<?php


namespace Willyfog\Models;

class BaseModel
{
    /**
     * @var \PDO
     */
    protected $_pdo;

    /**
     * Table name of the model.
     *
     * @var string
     */
    protected $_table_name;

    /**
     * Page size of the pagination.
     *
     * @var int
     */
    protected $_page_size = 10;

    public function __construct($ci)
    {
        $this->_pdo = $ci->get('pdo');
    }

    public function paginate($page = null)
    {
        $table_name = $this->_table_name;
        $page_size = $this->_page_size;
        if ($page === null) {
            $page = 0;
        }

        $stm = $this->_pdo->prepare(
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

    /**
     * Fill an instance with attributes. legacy.
     *
     * @param array $attributes
     * @return $this
     */
    public function fill(array $attributes = [])
    {
        $attributes = $this->filterAttributes($attributes);

        foreach ($attributes as $key => $value) {
            $this->$key = $value;
        }

        return $this;
    }

    /**
     * Insert the model into database.
     *
     * TODO: check why not null constraint is not checked
     *
     * @return bool
     */
    public function save()
    {
        $attributes = $this->insertableAttributes();
        $column_names = array_keys($attributes);
        $bindings = array_map(function ($value) {
            return ":$value";
        }, $column_names);
        $table_name = $this->_table_name;

        $stm = $this->_pdo->prepare(
            "INSERT INTO $table_name (" . implode(',', $column_names) . ') VALUES (' . implode(',', $bindings) . ')'
        );

        $success = $stm->execute(array_combine($bindings, array_values($attributes)));

        $this->id = $this->_pdo->lastInsertId();

        return $success;
    }

    /**
     * Update given columns of the model.
     *
     * @param $id
     * @param array $attributes
     * @return bool
     */
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
        $table_name = $this->_table_name;

        $stm = $this->_pdo->prepare(
            "UPDATE $table_name SET " . implode(',', $sets) . " WHERE id = $id"
        );

        $success = $stm->execute(array_combine($bindings, array_values($attributes)));

        return $success;
    }

    /**
     * Soft deletes the entity.
     *
     * @param $id
     * @return bool
     * @throws \Exception
     */
    public function delete($id)
    {
        if ($this->isAlreadyDeleted($id)) {
            throw new \Exception('Trying to delete an already deleted user.');
        }

        $table_name = $this->_table_name;
        $now = date("Y-m-d h:i:s");

        $stm = $this->_pdo->prepare(
            "UPDATE $table_name SET deleted_at = '$now' WHERE id = $id"
        );

        return $stm->execute();
    }

    private function isAlreadyDeleted($id)
    {
        $table_name = $this->_table_name;

        $stm = $this->_pdo->prepare(
            "SELECT COUNT(id) AS deleted FROM $table_name WHERE id = $id AND deleted_at IS NOT NULL"
        );

        $stm->execute();

        $result = $stm->fetchAll(\PDO::FETCH_ASSOC);

        return $result[0]['deleted'] == 1;
    }

    /**
     * Returns an assoc arrays of the attributes of the object. Only the one that matters to the database.
     *
     * @return array
     */
    private function getObjectVars()
    {
        $attributes = get_object_vars($this);

        return array_filter($attributes, function ($v, $k) {
            return $k[0] != '_';
        }, ARRAY_FILTER_USE_BOTH);
    }

    /**
     * Filter attributes to only assignable.
     *
     * @param $attributes
     * @return array
     */
    private function filterAttributes($attributes)
    {
        $vars = $this->getObjectVars();
        $object_keys = array_keys($vars);

        return array_filter($attributes, function ($k) use ($object_keys) {
            return in_array($k, $object_keys);
        }, ARRAY_FILTER_USE_KEY);
    }

    /**
     * Returns insertable attributes into the database.
     *
     * @return array
     */
    private function insertableAttributes()
    {
        return array_filter($this->getObjectVars(), function ($v, $k) {
            return $v !== null;
        }, ARRAY_FILTER_USE_BOTH);
    }
}
