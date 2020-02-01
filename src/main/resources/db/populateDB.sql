DELETE FROM user_vote;
DELETE FROM menu_item;
DELETE FROM lunch_menu;
DELETE FROM lunch_meals;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('Admin', 'admin@site.ru', '{bcrypt}$2a$10$h7MLerDKrQ4e87BMvQC9weqG6crqgBNxFk/TLLf2YgoYtSMlR./Pq'),
('user1', 'user1@google.com', '{bcrypt}$2a$10$faD.ya906aTTEk.S201vJeXl8kbMFxWrGT2WG6ZAhlu2SeBW226XO'),
('user2', 'user2@google.com', '{bcrypt}$2a$10$d8BQjDenE4DAi24QYx3siuVRulKX9mlCMDMdHaKgfQVKwcFnmSgle');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_ADMIN', 100000),
('ROLE_REGULAR_USER', 100000),
('ROLE_REGULAR_USER', 100001),
('ROLE_REGULAR_USER', 100002);

INSERT INTO restaurants (name, country, zip, city, address) VALUES
('Seraph(RUS)', 'Russia', 222222, 'Moscow', 'Ilyinka street'),
('Seraph(US)', 'USA', 111111, 'NY', 'Wall street'),
('Seraph(IT)', 'Italy', 333333, 'Rome', 'Nizza street');

INSERT INTO lunch_meals(name, price, restaurant_id) VALUES
('Borsch', 2000, 100003),
('Eggs', 200, 100003),
('Black Tea', 100, 100003),
('Roast Beef', 5000, 100004),
('Roast Beef', 7000, 100005),
('Pizza', 10000, 100005);

INSERT INTO lunch_menu(lunch_date, vote_count, restaurant_id) VALUES
('2019-12-09', 0, 100003),
('2019-12-09', 0, 100004), -- ('2019-12-09', 1, 100004),
('2019-12-10', 1, 100005);


INSERT INTO menu_item(meals_id, lunch_menu_id) VALUES
(100006, 100012),
(100007, 100013),
(100008, 100013),
(100009, 100013),
(100010, 100014),
(100011, 100014);

INSERT INTO user_vote(user_id, lunch_menu_id) VALUES
-- (100001, 100013),
(100002, 100014);