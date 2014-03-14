USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS messages;
 -- remove table if it already exists and start from scratch

CREATE TABLE messages (
    id INT NOT NULL AUTO_INCREMENT,
    sender CHAR(64),
    receiver CHAR(64),
    text CHAR(255),
    typem CHAR(1),
    quiz INT,
    sendtime BIGINT,
    PRIMARY KEY(id)

);