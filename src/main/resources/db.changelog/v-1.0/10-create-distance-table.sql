CREATE TABLE IF NOT EXISTS dc_distance (
                             distance_id INT AUTO_INCREMENT NOT NULL,
                             from_city INT not null,
                             to_city INT not null,
                             distance DECIMAL(8,2),
                             PRIMARY KEY (distance_id),
                             FOREIGN KEY (from_city) REFERENCES dc_city(city_id) ON DELETE RESTRICT,
                             FOREIGN KEY (to_city) REFERENCES dc_city(city_id) ON DELETE RESTRICT
);