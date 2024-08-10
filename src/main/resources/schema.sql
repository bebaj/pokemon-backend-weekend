DROP TABLE IF EXISTS USERS;
CREATE TABLE USERS (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      username VARCHAR(12) NOT NULL,
                      password VARCHAR(12) NOT NULL
);
