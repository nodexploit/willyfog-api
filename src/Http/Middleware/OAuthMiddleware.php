<?php


namespace Willyfog\Http\Middleware;

use Interop\Container\ContainerInterface;
use OAuth2\Request;
use Willyfog\Http\Response;

class OAuthMiddleware
{
    private $ci;

    public function __construct(ContainerInterface $ci)
    {
        $this->ci = $ci;
    }

    /**
     * Checks if the request contains a valid OAuth2 Authorization header.
     *
     * @param $request
     * @param $response
     * @param $next
     * @return static
     */
    public function __invoke($request, $response, $next)
    {
        $server = $this->ci->get('oauth');

        if (!$server->verifyResourceRequest(Request::createFromGlobals())) {
            $response = Response::fromOAuthResponse($server->getResponse());
        } else {
            $response = $next($request, $response);
        }

        return $response;
    }
}
