CREATE DATABASE IF NOT EXISTS `cqrs_ddd_scala_example`;
CREATE DATABASE IF NOT EXISTS `cqrs_ddd_scala_example_acceptance_tests`;

CREATE USER 'root'@'localhost' IDENTIFIED BY 'c0d3ly';
GRANT ALL ON *.* TO 'root'@'%';
