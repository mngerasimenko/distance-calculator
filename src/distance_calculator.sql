DROP TABLE IF EXISTS dc_distance;
DROP TABLE IF EXISTS dc_city;

CREATE TABLE dc_city (
    city_id INT AUTO_INCREMENT not null,
    city_name VARCHAR(50) not null,
    latitude VARCHAR(10) not null,
    longitude VARCHAR(10) not null,
    PRIMARY KEY (city_id)
);

CREATE TABLE dc_distance (
    distance_id INT AUTO_INCREMENT not null,
    from_city INT not null,
    to_city INT not null,
    PRIMARY KEY (distance_id),
    FOREIGN KEY (from_city) REFERENCES dc_city(city_id) ON DELETE RESTRICT
);