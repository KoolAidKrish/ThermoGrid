-- Illustrative figures only. These are not real utility or climate data.

INSERT INTO regions VALUES
 (1, 'Edmonton',      1150000, 1500),
 (2, 'Calgary',       1400000, 1850),
 (3, 'Red Deer',       105000,  180),
 (4, 'Lethbridge',     106000,  170),
 (5, 'Fort McMurray',   76000,  140);

-- Row 1 is a historical benchmark (is_projected = FALSE); the API ignores it.
INSERT INTO heatwave_events VALUES
 (1, 1, 2026, 36, FALSE),
 (2, 1, 2028, 38, TRUE),
 (3, 1, 2035, 42, TRUE),
 (4, 2, 2028, 37, TRUE),
 (5, 2, 2035, 41, TRUE),
 (6, 3, 2028, 36, TRUE),
 (7, 3, 2035, 40, TRUE),
 (8, 4, 2028, 40, TRUE),
 (9, 4, 2035, 44, TRUE),
 (10, 5, 2028, 35, TRUE),
 (11, 5, 2035, 39, TRUE);
