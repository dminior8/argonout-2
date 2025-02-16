INSERT INTO game (id, route_id, is_completed, start_time, updated_at, version)
VALUES
    ('550e8400-e29b-41d4-a716-446655440000', '660e8400-e29b-41d4-a716-446655440111', FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1),
    ('660e8400-e29b-41d4-a716-446655440222', '770e8400-e29b-41d4-a716-446655440333', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2);

INSERT INTO visited_place (id, place_id, game_id, visited_at)
VALUES
    ('770e8400-e29b-41d4-a716-446655440444', '880e8400-e29b-41d4-a716-446655440555', '550e8400-e29b-41d4-a716-446655440000', CURRENT_TIMESTAMP),
    ('990e8400-e29b-41d4-a716-446655440666', '990e8400-e29b-41d4-a716-446655440777', '660e8400-e29b-41d4-a716-446655440222', CURRENT_TIMESTAMP);
