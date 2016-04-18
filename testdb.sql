DROP DATABASE IF EXISTS testdb;
CREATE DATABASE testdb;
USE testdb;

CREATE TABLE test (
	id INT NOT NULL AUTO_INCREMENT,
    description VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

INSERT INTO test (description) VALUES ("test1");
INSERT INTO test (description) VALUES ("test2");
INSERT INTO test (description) VALUES ("test3");
INSERT INTO test (description) VALUES ("test4");
INSERT INTO test (description) VALUES ("test5");