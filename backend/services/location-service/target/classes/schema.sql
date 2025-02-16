CREATE TABLE IF NOT EXISTS place (
                       id UUID PRIMARY KEY, -- UUID is stored as a string
                       name VARCHAR(255) NOT NULL, -- Name of the place
                       description TEXT, -- Description of the place
                       latitude DOUBLE PRECISION NOT NULL, -- Latitude coordinate
                       longitude DOUBLE PRECISION NOT NULL, -- Longitude coordinate
                       more_info_link VARCHAR(255), -- Link to more information
                       created_at TIMESTAMP NOT NULL, -- Timestamp when the record was created
                       updated_at TIMESTAMP NOT NULL, -- Timestamp when the record was last updated
                       version BIGINT NOT NULL -- Version for optimistic locking
);