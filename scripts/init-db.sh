#!/usr/bin/env bash

while true; do
    read -p "Do you wish to run this script? (The database will be erased if already created): [y/n] " yn
    case $yn in
        [Yy]* ) break;;
        [Nn]* ) exit;;
        * ) echo "Please answer yes or no.";;
    esac
done

DB_SCHEMA=willyfog_db

echo "Dumping schema ..."
mysql -h 0.0.0.0 -P 3306 -uroot -proot < database/schema.sql

for i in database/inserts/* ; do
  if [ -f "$i" ]; then
    echo "Dumping script ${i} ..."
    mysql -h 0.0.0.0 -P 3306 -uroot -proot < ${i}
  fi
done

echo "**********************************************************"
echo "******** Database Successfully create ********************"
echo "**********************************************************"