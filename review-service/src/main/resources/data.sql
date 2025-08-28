DROP TABLE IF EXISTS review;
CREATE TABLE review AS SELECT * FROM CSVREAD('classpath:review.csv');