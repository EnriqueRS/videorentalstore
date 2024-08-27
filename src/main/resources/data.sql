insert into PRICES (ID, NAME, AMOUNT, CURRENCY)values(1, 'PREMIUM', 40.00, 'EUR');
insert into PRICES (ID, NAME, AMOUNT, CURRENCY)values(2, 'BASIC', 30.00, 'EUR');

insert into TYPES (ID, NAME, PRICE_ID)values(1, 'NEW_RELEASE', 1);
insert into TYPES (ID, NAME, PRICE_ID)values(2, 'REGULAR_RENTAL', 2);
insert into TYPES (ID, NAME, PRICE_ID)values(3, 'OLD_FILM', 2);

insert into FILMS (ID, TITLE,TYPE_ID)values(1,'Matrix 11', 1);
insert into FILMS (ID, TITLE,TYPE_ID)values(2,'Spider Man', 2);
insert into FILMS (ID, TITLE,TYPE_ID)values(3, 'Spider Man 2', 2);
insert into FILMS (ID, TITLE,TYPE_ID)values(4, 'Out of Africa', 3);
