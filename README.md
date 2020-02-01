## Lunch Voting System ([see Task.md](https://github.com/moncent/finaltopjava/blob/master/Task.md))
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fc6c71a9cb574cdaa054a0b3b760bf3b)](https://www.codacy.com/manual/moncent/finaltopjava?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=moncent/finaltopjava&amp;utm_campaign=Badge_Grade)

#### curl commands (context: finaltopjava)

-------------
##### USERS
-------------

**Admin profile**

* get all users:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/users``

* get user (id = 100002) with voting history:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/users/100002``

* get user by email:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/users/filter?email=user2@google.com``

* create user:
  > ``curl -u admin@site.ru:admin -X POST -H "Content-Type: application/json" -d '{"name": "newUser","email": "newUser@google.com","password":"newUser"}' http://localhost:8080/finaltopjava/rest/admin/users``

* update user (id = 100002):
  > ``curl -u admin@site.ru:admin -X PUT -H "Content-Type: application/json" -d ' {"id": 100002,"name": "Newuser2","email": "newuser2@google.com","password":"passwordNew"}' http://localhost:8080/finaltopjava/rest/admin/users/100002``

* disable user(id = 100002):
  > ``curl -u admin@site.ru:admin -X PATCH http://localhost:8080/finaltopjava/rest/admin/users/100002?enabled=false``

* enable user(id = 100002):
  > ``curl -u admin@site.ru:admin -X PATCH http://localhost:8080/finaltopjava/rest/admin/users/100002?enabled=true``

* delete user(id = 100002):
  > ``curl -u admin@site.ru:admin -X DELETE http://localhost:8080/finaltopjava/rest/admin/users/100002``

**Anonymous and Regular user profile**

* register user:
  > ``curl -X POST -H "Content-Type: application/json" -d '{"name": "todayUser","email": "todayUser@google.com","password":"todayUser"}' http://localhost:8080/finaltopjava/rest/profile/register``

**Regular user profile**

* get user profile:
  > ``curl -u user2@google.com:password2 http://localhost:8080/finaltopjava/rest/profile``

* get user profile with his voting history:
  > ``curl -u user2@google.com:password2 http://localhost:8080/finaltopjava/rest/profile/history``

* update user profile:
  > ``curl -u user2@google.com:password2 -X PUT -H "Content-Type: application/json" -d '{"id": 100002,"name": "updateUser2","email": "updateuser2@google.com","password":"updateUser"}' http://localhost:8080/finaltopjava/rest/profile``

------------------
##### RESTAURANTS
------------------

**Admin profile**

* get all restaurants:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/restaurant``

* get restaurant (id = 100005):
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/restaurant/100005``

* create restaurant:
  > ``curl -u admin@site.ru:admin -X POST -H "Content-Type: application/json" -d '{"name": "NEW RESTAURANT","zip": 569784,"country": "NEW COUNTRY","city": "NEW CITY","address": "NEW STREET"}' http://localhost:8080/finaltopjava/rest/admin/restaurant``

* update restaurant (id = 100005):
  > ``curl -u admin@site.ru:admin -X PUT -H "Content-Type: application/json" -d '{"id": 100005,"name": "Da Vinci(IT)","zip": 123456,"country": "Italy","city": "Rome","address": "Nizza street"}' http://localhost:8080/finaltopjava/rest/admin/restaurant/100005``

* delete restaurant (id = 100004):
  > ``curl -u admin@site.ru:admin -X DELETE http://localhost:8080/finaltopjava/rest/admin/restaurant/100004``

**Regular user profile**

* get all restaurants:
  > ``curl -u user2@google.com:password2 http://localhost:8080/finaltopjava/rest/profile/restaurant``

* get restaurant (id = 100005):
  > ``curl -u user2@google.com:password2 http://localhost:8080/finaltopjava/rest/profile/restaurant/100005``

-------------
##### MEALS
-------------

**Admin profile**

* get all meals:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/meals``

* get meal (id = 100009):
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/meals/100009``

* create meal:
  > ``curl -u admin@site.ru:admin -X POST -H "Content-Type: application/json" -d '{"name": "Green Tea","price": 150,"restaurant":{"id": 100004,"name": "Seraph(RUS)","zip": 222222,"country": "Russia","city": "Moscow","address": "Ilyinka street"}}' http://localhost:8080/finaltopjava/rest/admin/meals``

* update meal (id = 100006):
  > ``curl -u admin@site.ru:admin -X PUT -H "Content-Type: application/json" -d '{"id": 100006,"name": "New Green Tea","price": 250,"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}}' http://localhost:8080/finaltopjava/rest/admin/meals/100006``

* delete meal (id = 100011):
  > ``curl -u admin@site.ru:admin -X DELETE http://localhost:8080/finaltopjava/rest/admin/meals/100011``

-------------
##### MENUS
-------------

**Admin profile**

* get all menus:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/menu``

* get menu (restaurant id = 100005, menu id = 100014):
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/menu/100005/100014``

* get today menu:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/menu/now``

* get menu by date:
  > ``curl -u admin@site.ru:admin http://localhost:8080/finaltopjava/rest/admin/menu/search?date=2019-12-10``

* create menu:
  > ``curl -u admin@site.ru:admin -X POST -H "Content-Type: application/json" -d '{"lunchDate": "2020-02-01","menuItems":[{"id": 100010,"name": "Roast Beef","price":7000,"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}},{"id": 100011,"name": "Pizza","price": 10000,"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}}],"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}}' http://localhost:8080/finaltopjava/rest/admin/menu``

* update menu (id = 100015):
  > ``curl -u admin@site.ru:admin -X PUT -H "Content-Type: application/json" -d '{"id": 100015,"lunchDate": "2020-02-02","menuItems":[{"id": 100011,"name": "Pizza","price": 10000,"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}}],"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}}' http://localhost:8080/finaltopjava/rest/admin/menu/100015``

* vote for menu:
  > ``curl -u admin@site.ru:admin -X PATCH -H "Content-Type: application/json" -d '{"id":100015,"lunchDate":"2020-02-01","menuItems":[{"id":100010,"name":"Roast Beef","price":7000,"restaurant":{"id":100005,"name":"Seraph(IT)","zip":333333,"country":"Italy","city":"Rome","address":"Nizza street"}},{"id":100011,"name":"Pizza","price":10000,"restaurant":{"id":100005,"name":"Seraph(IT)","zip":333333,"country":"Italy","city":"Rome","address":"Nizza street"}}],"restaurant":{"id":100005,"name":"Seraph(IT)","zip":333333,"country":"Italy","city":"Rome","address":"Nizza street"},"voteCounter":0}' http://localhost:8080/finaltopjava/rest/admin/menu``

* delete menu (id = 100015):
  > ``curl -u admin@site.ru:admin -X DELETE http://localhost:8080/finaltopjava/rest/admin/menu/100015``

**Regular user profile**

* get today menu:
  > ``curl -u user1@google.com:password1 http://localhost:8080/finaltopjava/rest/profile/menu/now``

* vote for menu:
  > ``curl -u user2@google.com:password2 -X PATCH -H "Content-Type: application/json" -d '{"id": 100015,"lunchDate": "2020-02-01","menuItems":[{"id": 100010,"name": "Roast Beef","price": 7000,"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}},{"id": 100011,"name": "Pizza","price": 10000,"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"}}],"restaurant":{"id": 100005,"name": "Seraph(IT)","zip": 333333,"country": "Italy","city": "Rome","address": "Nizza street"},"voteCounter": 1}' http://localhost:8080/finaltopjava/rest/profile/menu``
