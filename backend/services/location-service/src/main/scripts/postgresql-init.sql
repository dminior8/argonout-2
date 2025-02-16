DROP DATABASE IF EXISTS locationservice;
DROP ROLE IF EXISTS location_service;
CREATE DATABASE locationservice ENCODING 'UTF8';
CREATE ROLE location_service WITH LOGIN PASSWORD 'password';
GRANT CONNECT ON DATABASE locationservice TO location_service;
GRANT CREATE, TEMPORARY ON DATABASE locationservice TO location_service;
GRANT SELECT, INSERT, UPDATE, DELETE, TRUNCATE, REFERENCES, TRIGGER
      ON ALL TABLES IN SCHEMA public TO location_service;
GRANT USAGE, CREATE ON SCHEMA public TO location_service;
GRANT EXECUTE ON ALL FUNCTIONS IN SCHEMA public TO location_service;
