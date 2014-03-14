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

INSERT INTO questions (quiz_id, q_type, prompt, content_0, content_1, content_2, content_3, content_4, content_5, content_6, content_7, content_8, content_9) VALUES
(1  , "basic"           , "Answer the question."       , "If a woodchuck would chuck wood."  , "How much wood would a woodchuch chuck?"                                                                     , NULL                         , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(3  , "basic"           , "Answer the question."       , "Snoop Dogg" ,  "Who would change his last name to Lion?"                                                                                          , NULL                         , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(3  , "basic"           , "Answer the question."       , "George Washington"         , "Who was the first US President?"                                                                                    , NULL                         , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(5  , "basic"           , "You don't know my life!"    , "Led Zeppelin, duh."               , "Who's the best band ever?"                                                                                   , null                         , null         , null               , null                                  , null , null , null , null),
(9  , "fill_in_blank"   , "Fill in the blank."         , "Roble."                                  , "Take off your pants for"                                                                              , "ey ey ey"                     , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(5 , "basic"           , "Answer the question."       , "Robert Plantt."                          , "Who is the lead singer?"                                                                              , NULL                         , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(5 , "multiple_choice" , "Choose the best option."    , "3"                                       , "Physical Graffiti"                                                                                    , "The Doors"                    , "Led Zeppelin" , "Stairway to Heaven" , "What was their first album?"           , NULL , NULL , NULL , NULL),
(5 , "fill_in_blank"   , "Fill in the blank."         , "mountain"                                , "I used to sing on the"                                                                                , "but the mountain washed away" , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(5 , "picture"         , "What album is this from?"   , "Led Zeppelin"                            , "http://upload.wikimedia.org/wikipedia/en/e/ef/Led_Zeppelin_-_Led_Zeppelin_%281969%29_front_cover.png" , NULL                         , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(7 , "multiple_choice" , "Choose the best option."    , "2"                                       , "4-6 hours"                                                                                            , "7-9 hours"                    , "9-12 hours"   , "12-15 hours"        , "Optimal amount of sleep per night?"    , NULL , NULL , NULL , NULL),
(7 , "fill_in_blank"   , "Fill in the blank."         , "Time"                                    , NULL                                                                                                       , "is the only way to sober up." , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(9 , "picture"         , "What is this a picture of?" , "Deal with it."                           , "http://cdn.arwrath.com/8/86610.gif"                                                                   , NULL                         , NULL         , NULL               , NULL                                  , NULL , NULL , NULL , NULL),
(11 , "multiple_choice" , "Choose the best option."    , "1"                                       , "Gerrard"                                                                                              , "Messi"                        , "Ronaldo"      , "Xavi"               , "Who is Ben's favourite soccer player?" , NULL , NULL , NULL , NULL);
