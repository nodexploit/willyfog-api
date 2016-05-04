# willyfog API

![Willy Fog](docs/willy-fog.jpg "Willy Fog")

API for end-of-degree project for ERASMUS. This will serve an application to simplify
the process of setting equivalences between subjects of different universities.

## Database

```
# In the root of the project
mysql -uuser -p -e "CREATE DATABASE willyfog_dev"
mysql -uuser -p willyfog_dev < db/schema.sql
```