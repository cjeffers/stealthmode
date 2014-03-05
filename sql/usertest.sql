USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
    id INT,
    name CHAR(64),
    password CHAR(64),
    Administrator BIT,
    AmateurAuthor BIT,
    ProlificAuthor BIT,
    ProdigiousAuthor BIT,
    QuizMachine BIT,
    IsGreatest BIT,
    PracticePerfect BIT

);

INSERT INTO users VALUES
	(1, "Cameron","rootbeer", 1, 1, 1, 0, 0, 0 ,0),
    (2, "Jed","lazy", 1, 1, 0, 0, 0, 0 ,0),
	(3, "Ben","soccer", 1, 1, 1, 1, 1, 0 ,0),
	(4, "Jacob","vincent", 0, 1, 0, 0, 0, 0 ,0),
	(5, "Patrick","youngpatrick", 0, 1, 1, 0, 0, 0 ,0),