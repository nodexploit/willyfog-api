<?php


namespace Willyfog\Http\Controllers\V1;

use Interop\Container\ContainerInterface;
use OAuth2\Request as OAuth2Request;
use Willyfog\Models\User;

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

        $user = new User($this->ci);
        $user->create([
            'name' => 'pepe',
            'surname' => 'piscinas',
            'nif' => 'asd',
            'email' => 'asd',
            'digest' => 'asd'
        ]);

        $flasg = $user->update(13, [
            'name' => 'pepo',
            'nif' => ''
        ]);
        
        return "Hello user ${token['user_id']}";
    }
}
