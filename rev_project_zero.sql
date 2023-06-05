--Database for anime, heroes and antagonists
--schema should be called "rev_project_zero_schema"
--create table and constraints for heroes 
CREATE TABLE heroes(
	hero_id serial PRIMARY KEY,
	hero_name TEXT,
	hero_weapon TEXT
);

--create table and constraints for villains
CREATE TABLE villains(
	villain_id serial PRIMARY KEY,
	villain_name TEXT,
	villain_weapon TEXT
);
	
--create table and constraints for anime movies
--The script for this table must be run last because of its references 
CREATE TABLE movies(
	movie_id serial PRIMARY KEY,
	movie_name TEXT,
	movie_release_year int,
	hero_id_fk int REFERENCES heroes(hero_id),
	villain_id_fk int REFERENCES villains(villain_id)
);

--insert values for heroes table and show table
INSERT INTO heroes(hero_name,hero_weapon)
VALUES ('Kirito','Elucidator & Dark Repulser'),
	('Kirito','Kagemitsu G4 photon sword & FN Five-seveN'),
	('Naruto','Shadow clone jutsu & Nine Tailed Fox,');
SELECT * FROM heroes;

--insert values for villains table and show table
INSERT INTO villains(villain_name,villain_weapon)
VALUES ('Kayaba Akihiko','Liberator'),
	('Death Gun','Death Gun'),
	('Orochimaru','Sword of Kusanagi'),
	('Madara','Gunbai Uchiwa');
SELECT* FROM villains;

--insert values for anime movies table and show table
INSERT INTO movies(movie_name, movie_release_year, hero_id_fk, villain_id_fk)
VALUES ('Sword Art Online', 2012, 1,1),
	('Sword Art Online II', 2012, 2,2),
	('Naruto', 1999, 3, 3),
	('Naruto Shippuden', 2009, 3,4);	
SELECT * FROM movies;



