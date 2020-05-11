DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USER_ROLES;
DELETE FROM USERS;
DELETE FROM HISTORY;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO USERS(name, email, password, registered)
VALUES ('User1', 'a@mail.ru', '{noop}pass1', now()),
       ('User2', 'b@mail.ru', '{noop}pass2', now()),
       ('User3', 'c@mail.ru', '{noop}pass3', now()),
       ('Admin1', 'd@mail.ru', '{noop}a_pas1', now());

INSERT INTO USER_ROLES(user_id, role)
VALUES (100000, 'USER'),
       (100001, 'USER'),
       (100002, 'USER'),
       (100003, 'USER'),
       (100003, 'ADMIN');

INSERT INTO RESTAURANTS(name, admin_id, vote)
VALUES ('Rest1', 100003, 5), /*'Рыба=252,Омлет=1210'),*/
       ('Rest2', 100003, 47), /*'Рыба=252,Омлет=1210'),*/
       ('Rest3', 100003, 53), /*'Рыба=252,Омлет=1210'),*/
       ('Rest4', 100003, 15), /*'Рыба=252,Омлет=1210'),*/
       ('Rest5', 100003, 25), /*'Рыба=252,Омлет=1210'),*/
       ('Rest6', 100003, 71); /*'Икра=2530,Чай=533,Шашлык=1036');*/

INSERT INTO DISHES(name, cost, restaurant_id)
values ('Рыба', 252, 100004),
       ('Картошка', 53, 100004),
       ('Чай', 533, 100004),
       ('Омлет', 733, 100004),
       ('Омлет', 1210, 100005),
       ('Шашлык', 1036, 100005),
       ('Чай', 533, 100005),
       ('Картошка', 53, 100006),
       ('Шашлык', 1036, 100006),
       ('Кофе', 1530, 100007),
       ('Чай', 254, 100007),
       ('Омлет', 1210, 100008),
       ('Картошка', 120, 100008),
       ('Картошка', 53, 100009),
       ('Икра', 2530, 100009);

INSERT INTO HISTORY(restaurant_name,vote_date, vote, menu)
VALUES ('Rest2','2020-01-01 10:00:00', 5, 'Рыба=252,Омлет=1210'),
       ('Rest1','2020-02-01 10:02:00', 8, 'Икра=2530,Чай=533,Шашлык=1036'),
       ('Rest1','2020-03-01 10:02:00', 8, 'Икра=2530,Чай=533,Шашлык=1036'),
       ('Rest1','2020-04-01 10:02:00', 8, 'Икра=2530,Чай=533,Шашлык=1036'),
       ('Rest1','2020-05-01 10:02:00', 8, 'Икра=2530,Чай=533,Шашлык=1036');
