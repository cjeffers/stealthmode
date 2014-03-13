USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS quizzes;
 -- remove table if it already exists and start from scratch

CREATE TABLE quizzes (
    id INT NOT NULL AUTO_INCREMENT,
    name CHAR(64),
    description NVARCHAR(255),
    timed TINYINT(1),
    multiple_pages TINYINT(1),
    creation_time BIGINT,
    creator_id INT,
    PRIMARY KEY (id)
);

INSERT INTO quizzes (name, description, timed, multiple_pages, creation_time, creator_id) VALUES
("jacob's quiz", "hello", 1, 0, 1394600144985, 1),
("Cameron's Quiz", "This is a quiz by Cam", 0, 0, 1394678442994, 1),
("Led Zep", "Rock and roll", 0, 0, 1394686353303, 3),
("Caitlin's Quiz", "for PHEs", 0, 0, 1394698037642, 3),
("Deal With It", "GIFs", 0, 0, 1394698235739, 1),
("Ben's Quiz", "a quiz by Cameron, about Ben", 0, 0, 1394741845564, 5);
