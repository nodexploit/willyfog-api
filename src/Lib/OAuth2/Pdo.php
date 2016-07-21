<?php


namespace Willyfog\Lib\OAuth2;

class Pdo extends \OAuth2\Storage\Pdo
{
    /**
     * Pdo constructor. override default table names.
     * @param $connection
     * @param array $config
     */
    public function __construct($connection, array $config = [])
    {
        parent::__construct($connection, $config);

        $this->config = array_replace(
            $this->config,
            [
                'client_table'          => 'oauth_client',
                'access_token_table'    => 'oauth_access_token',
                'refresh_token_table'   => 'oauth_refresh_token',
                'code_table'            => 'oauth_authorization_code',
                'user_table'            => 'user',
                'jwt_table'             => 'oauth_jwt',
                'scope_table'           => 'oauth_scope'
            ]
        );
    }
}
