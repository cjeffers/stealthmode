USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS metropolises;
 -- remove table if it already exists and start from scratch

CREATE TABLE metropolises (
    id INT,
    metropolis CHAR(64),
    continent CHAR(64),
    population BIGINT
);

INSERT INTO metropolises VALUES
	(1, "Mumbai","Asia",20400000),
    (2, "New York","North America",21295000),
	(3, "San Francisco","North America",5780000),
	(4, "London","Europe",8580000),
	(5, "Rome","Europe",2715000),
	(6, "Melbourne","Australia",3900000),
	(7, "San Jose","North America",7354555),
	(8, "Rostov-on-Don","Europe",1052000);