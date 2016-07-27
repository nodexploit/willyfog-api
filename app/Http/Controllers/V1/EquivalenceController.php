<?php


namespace Willyfog\Http\Controllers\V1;


use Slim\Http\Request;
use Slim\Http\Response;

class EquivalenceController extends BaseController
{
    public function listAll(Request $request, Response $response, $args)
    {
        $stm = $this->ci->get('pdo')
            ->prepare(
                "SELECT e.id, s.id AS subject_id, s.name AS subject_name,
                 se.id AS equivalent_id, se.name AS equivalent_name
                 FROM equivalence e
                 JOIN `subject` s ON e.subject_id = s.id
                 JOIN `subject` se ON e.subject_id_eq = se.id"
            );

        $stm->execute();

        return $response->withJson($stm->fetchAll(\PDO::FETCH_ASSOC));
    }
}