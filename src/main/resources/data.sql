insert into TYPES (ID, NAME)values(1, 'NEW_RELEASE');
insert into TYPES (ID, NAME)values(2, 'REGULAR_RENTAL');
insert into TYPES (ID, NAME)values(3, 'OLD_FILM');

insert into FILMS (ID, TITLE,TYPE_ID)values(1,'Matrix 11', 1);
insert into FILMS (ID, TITLE,TYPE_ID)values(2,'Spider Man', 2);
insert into FILMS (ID, TITLE,TYPE_ID)values(3, 'Spider Man 2', 2);
insert into FILMS (ID, TITLE,TYPE_ID)values(4, 'Out of Africa', 3);

insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME, BONUS_POINTS)values(1, 'John', 'Smith', 0);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME, BONUS_POINTS)values(2, 'Mary', 'Johnson', 0);
insert into CUSTOMERS (ID, FIRST_NAME, LAST_NAME, BONUS_POINTS)values(3, 'David', 'Brown', 0);
