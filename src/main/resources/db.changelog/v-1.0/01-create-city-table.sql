CREATE TABLE IF NOT EXISTS dc_city (
                         city_id INT AUTO_INCREMENT NOT NULL,
                         city_name VARCHAR(50) not null,
                         latitude VARCHAR(10) not null,
                         longitude VARCHAR(10) not null,
                         PRIMARY KEY (city_id)
);