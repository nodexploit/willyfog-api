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
                "SELECT * FROM equivalence"
            );

        $stm->execute();

        return $response->withJson($stm->fetchAll(\PDO::FETCH_ASSOC));
    }
}