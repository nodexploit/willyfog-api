<?php


namespace Willyfog\Http\Controllers\V1;


use Slim\Http\Request;
use Slim\Http\Response;

class EquivalenceController extends BaseController
{
    public function search(Request $request, Response $response, $args)
    {
        $query = $request->getQueryParam("query");

        $stm = $this->ci->get('pdo')
            ->prepare(
                "SELECT e.id, s.id AS subject_id, s.name AS subject_name,
                 se.id AS equivalent_id, se.name AS equivalent_name
                 FROM equivalence e
                 JOIN `subject` s ON e.subject_id = s.id
                 JOIN `subject` se ON e.subject_eq_id = se.id
                 WHERE s.name LIKE :query 
                 OR se.name LIKE :query"
            );

        $stm->execute([
            ':query' => "%$query%"
        ]);

        return $response->withJson($stm->fetchAll(\PDO::FETCH_OBJ));
    }
}