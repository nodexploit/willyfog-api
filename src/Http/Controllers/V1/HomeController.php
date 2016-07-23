<?php


namespace Willyfog\Http\Controllers\V1;

use Interop\Container\ContainerInterface;
use OAuth2\Request as OAuth2Request;
use Willyfog\Models\User;
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;

class HomeController
{
    protected $ci;

    public function __construct(ContainerInterface $ci)
    {
        $this->ci = $ci;
    }

    public function index(Request $request, Response $response, $args)
    {
        $server = $this->ci->get('oauth');
        $token = $server->getAccessTokenData(OAuth2Request::createFromGlobals());

        $user = new User($this->ci);

        return $response->withJson($user->paginate());
    }
}
