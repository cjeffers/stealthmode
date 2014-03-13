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
    salt CHAR(128),
    administrator INT(1),
    amateur_author INT(1),
    prolific_author INT(1),
    prodigious_author INT(1),
    quiz_machine INT(1),
    is_greatest INT(1),
    practice_perfect INT(1),
    PRIMARY KEY(id)

);
INSERT INTO users (username, fullname, password, pic_url, salt, administrator, amateur_author, prolific_author, prodigious_author, quiz_machine, is_greatest, practice_perfect) VALUES
("ben_test", "Ben", "85219179fa48c2180da4bdf38f9b4403dde21303", NULL, "a6iuus8k", 0, 0, 0, 0, 0, 0, 0),
("ben_test_friend", "Ninja", "b6f0cac8f892ca88b71610d99820d30a52834386", NULL, "vfpxe931", 1, 0, 0, 0, 0, 0, 0),
("jed", "Jed", "8be9599b849d8c64db5f7399b977d01317401690", NULL, "4i61mj5b", 0, 0, 0, 0, 0, 0, 0),
("james", "Jed", "6366a0b22909b6a12d96fbc67084c74976fb9bbe", NULL, "if1i4t9g", 0, 0, 0, 0, 0, 0, 0),
("Bob", "Ben Ulmer", "b2d94a0eb22bb4aca8d5e734b794fcb8d41cdbb0", "testin", "23uwkpz0", 0, 1, 0, 0, 0, 0, 0);

