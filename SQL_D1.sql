-- CREATE DATABASE HCL_Practice;
-- USE HCL_Practice;
-- create table Department(
--     Dept_id INT primary key,
--     Dept_name varchar(50),
--     Dept_location varchar(50)
-- );
-- CREATE TABLE Trainers(
--     Faculty_id INT Primary key,
--     Faculty_name Varchar(20),
--     Designation Varchar(50),
--     Mobile_no BIGINT,
--     Experience INT,
--     Salary DOUBLE check(Salary > 0),
--     Dept_id INT,
--     FOREIGN KEY (Dept_id) REFERENCES Department(Dept_id)
-- );
-- ALTER table Trainers
-- add column Age int check(Age > 18);

-- ALTER TABLE Trainers
-- DROP FOREIGN KEY trainers_ibfk_1;

-- ALTER TABLE Department
-- MODIFY COLUMN Dept_id INT AUTO_INCREMENT;

-- alter table Trainers
-- add FOREIGN KEY (Dept_id) REFERENCES Department(Dept_id);

-- ----------------------------------------------------------------------------------

-- DDL task
-- create database school_db;
use school_db;

-- create table students(
--     student_id int primary key AUTO_INCREMENT,
--     name varchar(20) not null,
--     email varchar(100) UNIQUE,
--     age int check(age >=5),
--     city varchar(20) DEFAULT 'Delhi',
--     admission_date DATE
-- )

-- create table marks(
--     mark_id INT PRIMARY KEY,
--     student_id INT,
--     foreign key(student_id) REFERENCES students(student_id),
--     subject varchar(30),
--     score INT

-- );

-- alter table students
-- add column phone BIGINT;

-- alter table students
-- modify column phone varchar(15);

-- alter table students
-- rename column city to hometown;

-- create index index_student_name
-- on students(name);

-- create table student_backup as select * from students;

-- DML task
-- task1: insert


-- INSERT INTO students (name, email, age, hometown, admission_date, phone)
-- VALUES
--     ('Aarav Sharma', 'aarav.sharma@example.com', 14, 'Ghaziabad', '2026-04-01', '9876543210'),
--     ('Ananya Verma', 'ananya.verma@example.com', 15, DEFAULT, '2026-04-02', '9876543211'),
--     ('Rohan Gupta', 'rohan.gupta@example.com', 13, 'Noida', '2026-04-03', '9876543212'),
--     ('Priya Singh', 'priya.singh@example.com', 16, 'Lucknow', '2026-04-04', '9876543213'),
--     ('Kabir Malhotra', 'kabir.malhotra@example.com', 14, DEFAULT, '2026-04-05', '9876543214'),
--     ('Meera Nair', 'meera.nair@example.com', 15, 'Jaipur', '2026-04-06', '9876543215'),
--     ('Siddharth Joshi', 'siddharth.joshi@example.com', 17, 'Chandigarh', '2026-04-07', '9876543216'),
--     ('Neha Tiwari', 'neha.tiwari@example.com', 13, DEFAULT, '2026-04-08', '9876543217'),
--     ('Aditya Yadav', 'aditya.yadav@example.com', 16, 'Varanasi', '2026-04-09', '9876543218'),
--     ('Kavya Mishra', 'kavya.mishra@example.com', 14, 'Kanpur', '2026-04-10', '9876543219');


-- SELECT * from students;


-- INSERT INTO marks (mark_id, student_id, subject, score)
-- VALUES
--     (1, 1, 'Maths', 88),
--     (2, 1, 'Science', 92),
--     (3, 1, 'English', 85),
--     (4, 2, 'Maths', 79),
--     (5, 2, 'Science', 84),
--     (6, 2, 'English', 91),
--     (7, 3, 'Maths', 95),
--     (8, 3, 'English', 82),
--     (9, 4, 'Science', 76),
--     (10, 5, 'Maths', 89);

-- select * from marks;


-- task2: update and delete


-- update marks
-- set score= score + 5
-- where subject= 'Maths';

-- select * from marks;

-- update students
-- set hometown = 'Noida'
-- where student_id =2;

-- update students
-- set age= age +1
-- where hometown= 'Delhi';

-- SELECT * FROM marks WHERE student_id = 6;

-- delete from students
-- where student_id=6;

-- delete from marks
-- where score < 20;


-- DQL task
-- task 1: Filtering and sorting

-- select * from students;

-- select name as "Student Name", hometown from students;

-- select * from students where age between 10 and 15;

-- select * from students where name like 'A%';

-- select * from students where hometown in ('Delhi', 'Noida') order by age desc;

-- select * from students
-- order by age desc
-- limit 3;

-- select distinct hometown from students;

-- task 2: aggregates and joins

-- select hometown, COUNT(*) as student_count
-- from students
-- group by hometown;

-- select subject,
-- AVG(score) as avg_score,
-- MAX(score) as highest_score,
-- MIN(score) as minimum_score
-- from marks
-- group by subject;

-- select subject,
-- AVG(score) as avg_score
-- from marks
-- group by subject
-- having avg(score) >60;

-- select s.name as student_name,
-- m.subject,
-- m.score
-- from students s
-- inner join marks m on s.student_id=m.student_id;

-- select s.name as student_name,
-- m.subject,
-- m.score
-- from students s
-- left join marks m on s.student_id= m.student_id;

-- select DISTINCT s.student_id, s.name
-- from students s
-- join marks m on s.student_id = m.student_id
-- where m.score > (select avg(score) from marks);


--
-- select s.student_id,
-- s.name as student_name,
-- COALESCE(sum(m.score), 0) as total_marks,
-- rank() over (order by COALESCE(sum(m.score),0) desc) as rank_position
-- from students s
-- left join marks m on s.student_id = m.student_id
-- group by s.student_id, s.name;
