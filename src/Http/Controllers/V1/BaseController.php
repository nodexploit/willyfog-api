<?php


namespace Willyfog\Http\Controllers\V1;


use Interop\Container\ContainerInterface;
use Slim\Http\Request;
use Willyfog\Http\Response;


class BaseController
{
    /**
     * Name of the model corresponding to this controller.
     *
     * @var string
     */
    public $model_name;

    /**
     * Container injection
     */
    protected $ci;


    public function __construct(ContainerInterface $ci)
    {
        $this->ci = $ci;
        $model = str_replace('Controller', '', (new \ReflectionClass($this))->getShortName());
        $this->model_name = '\Willyfog\Models\\' . $model;
    }

    /**
     * Return Pagination of all of the elements in the database.
     *
     * @return Response
     */
    public function index(Request $request, Response $response, $args)
    {
        return $response->withJson(call_user_func($this->model_name . '::paginate'));
    }

    /**
     * Creates new model.
     *
     * @param Request $request
     * @return Response
     */
    public function create(Request $request, Response $response, $args)
    {
        $model = new $this->model_name($this->ci);

        if ($model->create($request->getParsedBody())) {
            return $response->withJson($model);
        } else {
            return $response->withJson('Ups, we can\'t create the given model', 409);
        }
    }

    /**
     * Updates the given resource.
     *
     * @param Request $request
     * @param $id
     * @return Response
     */
    public function update(Request $request, Response $response, $args)
    {
        $id = $args['id'];
        $model = new $this->model_name($this->ci);

        if ($model->update($id, $request->getParsedBody())) {
            return $response->withJson($model);
        } else {
            return $response->withJson('Ups, the given model can\'t be updated', 409);
        }
    }

    /**
     * Destroy the given resource
     *
     * @param $id
     * @return Response
     */
    public function destroy(Request $request, Response $response, $args)
    {
        $id = $args['id'];
        $model = new $this->model_name($this->ci);
        $model->delete($id);

        return $response->withJson("Resource $id successfully deleted");
    }

}