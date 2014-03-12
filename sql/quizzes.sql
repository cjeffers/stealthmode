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
    PRIMARY KEY (id)
);

-- INSERT INTO quizzes (name, description, timed, multiple_pages, creation_time) VALUES
