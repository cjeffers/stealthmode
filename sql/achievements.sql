USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
DROP TABLE IF EXISTS achievements;
 -- remove table if it already exists and start from scratch

CREATE TABLE achievements (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    amateur_author INT(1),
    amateur_author_time BIGINT,
    prolific_author INT(1),
    prolific_author_time BIGINT,
    prodigious_author INT(1),
    prodigious_author_time BIGINT,
    quiz_machine INT(1),
    quiz_machine_time BIGINT,
    is_greatest INT(1),
    is_greatest_time BIGINT,
    practice_perfect INT(1),
    practice_perfect_time BIGINT,

    PRIMARY KEY (id)
);
