## Test project

Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.

-----------------------------

## API documentation

***_curl command examples:_

**USER commands**

**_Lunches_**

Get all
`curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches`

Get by id
`curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches/100008`

**_Vote and ratings_**

Vote
`curl -u user@yandex.ru:user -X PUT http://localhost:8080/testapp/lunches/100015`

Get rating by restaurant 
`curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches/ratingByRestaurant?restaurant=100003`

Get rating by lunch 
`curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches/ratingByLunch?lunch=100015`

**ADMIN commands**

**_Edit restaurants_**

GetAll
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants`

Create new
`curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"name": "RestaurantNew"}' http://localhost:8080/testapp/restaurants`

Delete (Restaurant1)
`curl -u admin@gmail.com:admin -X DELETE http://localhost:8080/testapp/restaurants/100002`

GetByID (Restaurant2)
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants/100003`

Update (Restaurant2)
`curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X PUT -d '{"name": "RestaurantUpdated", "id": 100003}' http://localhost:8080/testapp/restaurants/100003`

**_Edit dishes by restaurant_**

GetAll
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants/100003/dishes`

Create new
`curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"dishName":"Dish3R2","dishPrice":133}' http://localhost:8080/testapp/restaurants/100003/dishes`

Delete
`curl -u admin@gmail.com:admin -X DELETE http://localhost:8080/testapp/restaurants/100003/dishes/100006`

GetByID
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants/100003/dishes/100007`

Update
`curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X PUT -d '{"dishName":"Dish1R2Updated","dishPrice":350}' http://localhost:8080/testapp/restaurants/100003/dishes/100007`

**_Edit lunches by restaurant_**

GetAll
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants/100003/lunches`

Create new
`curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"disheIds":[100006, 100007]}' http://localhost:8080/testapp/restaurants/100003/lunches`

Delete
`curl -u admin@gmail.com:admin -X DELETE http://localhost:8080/testapp/restaurants/100002/lunches/100010`

GetByID
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants/100003/lunches/100009`

**_Edit users_**

GetAll
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/admin`

Create new
`curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"email":"new@mail.ru", "password":"newPassword"}' http://localhost:8080/testapp/admin`

Update
`curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"email":"updated@mail.ru", "updatedpassword":"newPassword"}' http://localhost:8080/testapp/admin/100000`

Delete
`curl -u admin@gmail.com:admin -X DELETE http://localhost:8080/testapp/admin/100001`

GetByID
`curl -u admin@gmail.com:admin http://localhost:8080/testapp/admin/100000`