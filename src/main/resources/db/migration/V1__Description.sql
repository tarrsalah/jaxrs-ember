-- noinspection SqlDialectInspection
create table TASK (
    ID int  PRIMARY KEY auto_increment,
    NAME VARCHAR(100),
    DONE BOOLEAN
);

INSERT INTO TASK ( NAME, DONE) VALUES ('Finish the jersey-todo-app', false);
INSERT INTO TASK ( NAME, DONE) VALUES ('Watch some awesome videos', false);