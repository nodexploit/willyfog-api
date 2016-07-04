#!/usr/bin/env bash

read -r -d '' DROPEANDO << ROTO
DROP USER IF EXISTS willyfog_user;
DROP DATABASE IF EXISTS willyfog_db;
CREATE USER IF NOT EXISTS 'willyfog_user'@'localhost' IDENTIFIED BY '11112222';
CREATE DATABASE willyfog_db;
GRANT ALL PRIVILEGES ON willyfog_db.* to 'willyfog_user'@'localhost';
ROTO

echo "$DROPEANDO" >> /tmp/stand.sql

mysql -u root -p willyfog_db < /tmp/stand.sql
mysql -u root -p willyfog_db < ../db/schema.sql