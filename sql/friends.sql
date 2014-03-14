USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS friends;
 -- remove table if it already exists and start from scratch

CREATE TABLE friends (
    id INT NOT NULL AUTO_INCREMENT,
    friends_with CHAR(64),
    my_name CHAR(64),
    PRIMARY KEY(id)

);
