SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS destinations (
    id int PRIMARY KEY auto_increment,
    location VARCHAR
);

CREATE TABLE IF NOT EXISTS adventures (
    adventureId int PRIMARY KEY auto_increment,
    destinationPoint INTEGER,
    category VARCHAR,
    title VARCHAR,
    description VARCHAR,
    duration VARCHAR,
    peak VARCHAR,
    rating FLOAT
);

