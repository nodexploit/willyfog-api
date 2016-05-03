#!/usr/bin/env bash

mysql -uroot -e "CREATE DATABASE willyfog_db";
mysql -uroot -e willyfog_db < db/schema.sql