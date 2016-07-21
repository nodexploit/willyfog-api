<?php


namespace Willyfog\Http\Controllers\V1;

use Interop\Container\ContainerInterface;

class HomeController
{
    protected $ci;

    public function __construct(ContainerInterface $ci)
    {
        $this->ci = $ci;
    }

    public function index($request, $response, $args)
    {
        return 'hello';
    }
}
