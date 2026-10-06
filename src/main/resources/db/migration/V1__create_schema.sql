CREATE TABLE regions (
  region_id             BIGINT PRIMARY KEY,
  name                  VARCHAR(100) NOT NULL,
  population            INT NOT NULL,
  base_grid_capacity_mw DOUBLE PRECISION NOT NULL
);

CREATE TABLE heatwave_events (
  event_id        BIGINT PRIMARY KEY,
  region_id       BIGINT NOT NULL REFERENCES regions(region_id),
  target_year     INT NOT NULL,
  peak_heat_index DOUBLE PRECISION NOT NULL,
  is_projected    BOOLEAN NOT NULL
);

CREATE INDEX idx_events_year ON heatwave_events(target_year, is_projected);
