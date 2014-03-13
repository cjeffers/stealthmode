USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    username CHAR(64),
    fullname CHAR(64),
    password CHAR(64),
    pic_url CHAR(128),
    administrator INT(1),
    salt CHAR(128),
    PRIMARY KEY(id)

);

