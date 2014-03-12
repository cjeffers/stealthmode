USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
    id INT,
    username CHAR(64),
    Fullname CHAR(64),
    password CHAR(64)
    -- Administrator BIT,
    -- AmateurAuthor BIT,
    -- ProlificAuthor BIT,
    -- ProdigiousAuthor BIT,
    -- QuizMachine BIT,
    -- IsGreatest BIT,
    -- PracticePerfect BIT

);

INSERT INTO users VALUES
	(1, "cam", "Cameron","rootbeer"),
    (2, "jedidiah", "Jed","lazy"),
	(3, "ben", "Ben","soccer"),
	(4, "juicy", "Jacob","vincent"),
	(5, "pat", "Patrick","youngpatrick");
