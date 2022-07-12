DROP DATABASE IF EXISTS ijse;
CREATE DATABASE IF NOT EXISTS ijse;
SHOW DATABASES;

USE ijse;

DROP TABLE IF EXISTS Student;
CREATE TABLE IF NOT EXISTS Student(

    student_id VARCHAR (45),
    student_name VARCHAR (45) NOT NULL,
    email TEXT NOT NULL,
    contact VARCHAR(20) NOT NULL,
    Address TEXT NOT NULL,
    nic VARCHAR(45) NOT NULL,
    CONSTRAINT PRIMARY KEY(student_id)

);

SHOW TABLES;
DESC Student;

DROP TABLE IF EXISTS Teacher;
CREATE TABLE IF NOT EXISTS Teacher(

    teacher_id VARCHAR(45),
    name VARCHAR(45) NOT NULL,
    nic VARCHAR(45) NOT NULL,
    contact VARCHAR(45) NOT NULL,
    address TEXT NOT NULL,
    CONSTRAINT PRIMARY KEY(teacher_id)

);

SHOW TABLES;
DESC Teacher;

DROP TABLE IF EXISTS Subject;
CREATE TABLE IF NOT EXISTS Subject(

    subject_id VARCHAR(45),
    subject_name VARCHAR(45) NOT NULL,
    credit DOUBLE,
    teacher_id VARCHAR(45) NOT NULL,
    CONSTRAINT PRIMARY KEY (subject_id),
    CONSTRAINT FOREIGN KEY(teacher_id) REFERENCES Teacher(teacher_id)
    ON DELETE CASCADE ON UPDATE CASCADE

);

SHOW TABLES;
DESC Subject;

CREATE TABLE IF NOT EXISTS Course(
    course_id VARCHAR(45) NOT NULL,
    course_name VARCHAR(45),
    cost DOUBLE,
    duration VARCHAR(45),
    subject_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (course_id),
    CONSTRAINT FOREIGN KEY(subject_id) REFERENCES Subject(subject_id) ON DELETE CASCADE ON UPDATE CASCADE);

SHOW TABLES;
DESC Course;

DROP TABLE IF EXISTS intake;
CREATE TABLE IF NOT EXISTS intake(

    intake_id VARCHAR(45),
    start_date DATE NOT NULL,
    intakecol VARCHAR(45),
    description VARCHAR(45),
    course_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY(intake_id),
    CONSTRAINT FOREIGN KEY(course_id) REFERENCES Course(course_id)
    ON DELETE CASCADE ON UPDATE CASCADE


);

SHOW TABLES;
DESC intake;


CREATE TABLE IF NOT EXISTS Registration(
    registration_id VARCHAR(45) NOT NULL,
    reg_date DATE,
    student_id VARCHAR(45),
    intake_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (registration_id),
    CONSTRAINT FOREIGN KEY(student_id) REFERENCES Student(student_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY(intake_id) REFERENCES Intake(intake_id) ON DELETE CASCADE ON UPDATE CASCADE);

SHOW TABLES;
DESC Registration;



CREATE TABLE IF NOT EXISTS Payment(
    payment_id VARCHAR(45) NOT NULL,
    date DATE,
    cost DOUBLE,
    registration_id VARCHAR(45),
    CONSTRAINT PRIMARY KEY (payment_id),
    CONSTRAINT FOREIGN KEY(registration_id) REFERENCES Registration(registration_id) ON DELETE CASCADE ON UPDATE CASCADE);

SHOW TABLES;
DESC Payment;


INSERT INTO student VALUES
    ('C01', 'Nimal', 'Nimal@yahoo.com' , '07643526763' , 'No:20 Galle', '345876345v' ),
    ('C02', 'Sasanka', 'Sas@yahoo.com' , '07753332245' , 'No:30 panadura', '678345647v' );

SELECT * FROM student;

INSERT INTO teacher VALUES
    ('T01', 'Asanka', '0987945v' , '07643346763' , 'No:20 Galle' ),
    ('T02', 'Pushpika', '987456456v' , '07745332245' , 'Homagama' );

SELECT * FROM teacher;

INSERT INTO subject VALUES
    ('SU01', 'Engilsh', 65 , 'T01'  ),
    ('SU02', 'Maths', 78.5 , 'T02'  );

SELECT * FROM subject;

INSERT INTO course VALUES
    ('CO01', 'Engilsh Course', 40000.00 , 'Three Months', 'SU01' ),
    ('CO02', 'Maths Course', 58000.00 , 'Four Moths', 'SU02'  );

SELECT * FROM course;

INSERT INTO intake VALUES
    ('I01', '2023-03-01', 'abcd' , 'Test1', 'CO01' ),
    ('I02', '2024-11-08', 'adcf' , 'Test2', 'CO02'  );

SELECT * FROM intake;

INSERT INTO registration VALUES
    ('R01', '2020-04-01', 'C01' , 'I01' ),
    ('R02', '2021-02-08', 'C02' , 'I02' );

SELECT * FROM registration;

INSERT INTO payment VALUES
    ('P01', '2020-04-01', 90000 , 'R01' ),
    ('P02', '2021-04-08', 35000 , 'R02' );

SELECT * FROM payment;

