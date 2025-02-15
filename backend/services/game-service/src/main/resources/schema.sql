CREATE TABLE game (
                      id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
                      route_id UUID NOT NULL,
                      is_completed BOOLEAN NOT NULL,
                      start_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP,
                      version BIGINT DEFAULT 0
);

CREATE TABLE visited_place (
                               id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
                               place_id UUID NOT NULL,
                               game_id UUID,
                               visited_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               FOREIGN KEY (game_id) REFERENCES game(id)
);
