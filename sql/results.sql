USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS results;
 -- remove table if it already exists and start from scratch

CREATE TABLE results (
    id INT NOT NULL AUTO_INCREMENT,
    quiz_id INT,
    user_id INT,
    score INT,
    num_quest INT,
    taken_at BIGINT,
    duration BIGINT,
    PRIMARY KEY (id)
);

INSERT INTO results (quiz_id, user_id, score, num_quest, taken_at, duration) VALUES
(1, 1, 4, 5, 100, 10),
(1, 1, 5, 5, 105, 15),
(2, 1, 3, 10, 120, 20),
(2, 2, 10, 10, 125, 10);
