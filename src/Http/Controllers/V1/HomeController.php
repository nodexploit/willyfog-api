<?php


namespace Willyfog\Http\Controllers\V1;

use Interop\Container\ContainerInterface;
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
        $user = new User($this->ci);
        return $user->greet();
    }
}
