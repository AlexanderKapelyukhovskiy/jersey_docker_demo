CREATE DATABASE AmazingCo;

USE AmazingCo;

CREATE TABLE Companies (
     id INT NOT NULL,
     name CHAR(30) NOT NULL,
     parent_id INT,
     PRIMARY KEY (id)
);

INSERT INTO Companies (id, name, parent_id) VALUES 
  (1, 'Root Company', NULL),

  (2, 'Company A', 1),
  (3, 'Company B', 1),
  (4, 'Company C', 1),

  (5, 'Company A1', 2),
  (6, 'Company A2', 2),
  (7, 'Company A3', 2),

  (8, 'Company A1', 2),
  (9, 'Company A2', 2),
  (10, 'Company A3', 2),

  (11, 'Company B1', 3),
  (12, 'Company B2', 3),
  (13, 'Company B3', 3),

  (14, 'Company C1', 3),
  (15, 'Company C2', 3),
  (16, 'Company C3', 3)