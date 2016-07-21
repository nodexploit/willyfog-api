<?php


namespace Willyfog\Http\Controllers\V1;

use Interop\Container\ContainerInterface;
use OAuth2\Request as OAuth2Request;

class HomeController
{
    protected $ci;

    public function __construct(ContainerInterface $ci)
    {
        $this->ci = $ci;
    }

    public function index($request, $response, $args)
    {
        $server = $this->ci->get('oauth');
        $token = $server->getAccessTokenData(OAuth2Request::createFromGlobals());

        return "Hello user ${token['user_id']}";
    }
}
