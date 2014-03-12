USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<

DROP TABLE IF EXISTS questions;
 -- remove table if it already exists and start from scratch

CREATE TABLE questions (
    id INT NOT NULL AUTO_INCREMENT,
    quiz_id INT,
    q_type CHAR(64),
    prompt NVARCHAR(255),
    content_0 NVARCHAR(255),
    content_1 NVARCHAR(255),
    content_2 NVARCHAR(255),
    content_3 NVARCHAR(255),
    content_4 NVARCHAR(255),
    content_5 NVARCHAR(255),
    content_6 NVARCHAR(255),
    content_7 NVARCHAR(255),
    content_8 NVARCHAR(255),
    content_9 NVARCHAR(255),

    PRIMARY KEY (id)
);

INSERT INTO questions (quiz_id, q_type, prompt, content_0, content_1) VALUES
(3, "basic", "You don't know my life!", "Who's the best band ever?", "Led Zeppelin, duh."),
(2, "basic", "Answer the question.", "How much wood would a woodchuch chuck?", "If a woodchuck would chuck wood."),
(1, "basic", "Answer the question", "Who would change his last name to Lion?", "Snoop Dogg"),
(1, "basic", "Answer the question.", "Who was the first US President?", "George Washington");
