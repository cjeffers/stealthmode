USE c_cs108_jvangogh;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS ratings;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT,
    quiz_id INT,
    rating INT,
    review nvarchar(255),
    PRIMARY KEY(id)

);