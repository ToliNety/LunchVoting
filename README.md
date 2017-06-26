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

There are 2 types of Users: User and Admin. Admin has rights to edit data. User can get lunches for current day, ratings for restaurants or lunches and vote for them.
Only authenticated users can access REST resources (basic http authentication is used). 

All resources accept HTTP methods GET (get entity or list of entities), POST (create new entity), PUT (update entity), DELETE (delete entity by id)

There are 3 main resources:
* Restaurants (only for Admin)
    - Lunches of selected restaurant
    - Dishes of selected restaurant
* Users (only for Admin)
* Lunches (for all Users)


**Commands examples**

**_Restaurants resource_**

* Show All
    - `GET /restaurants`
    - `curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants`
* Create new
    - `POST /restaurants`
    - `curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"name": "RestaurantNew"}' http://localhost:8080/testapp/restaurants`
* Delete
    - `DELETE /restaurants/{id}`
    - `curl -u admin@gmail.com:admin -X DELETE http://localhost:8080/testapp/restaurants/100015`

**_Dishes of selected restaurant resource_**

* Show All
    - `GET /restaurants/{restaurantId}/dishes`
    - `curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants/100003/dishes`
* Create new
    - `POST /restaurants/{restaurantId}/dishes`
    - `curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"dishName":"Dish3R2","dishPrice":133}' http://localhost:8080/testapp/restaurants/100003/dishes`
* Delete
    - `DELETE /restaurants/{restaurantId}/dishes/{dishId}`
    - `curl -u admin@gmail.com:admin -X DELETE http://localhost:8080/testapp/restaurants/100003/dishes/100006`
    - ** _Dish updated (not deleted from DB), set deleted field = true._

**_Lunches of selected restaurant resource_**

* Show All Lunches
    - `GET /restaurants/{restaurantId}/lunches`
    - `curl -u admin@gmail.com:admin http://localhost:8080/testapp/restaurants/100003/lunches`
* Create new
    - `POST /restaurants/100003/lunches`
    - `curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"disheIds":[100006, 100007]}' http://localhost:8080/testapp/restaurants/100003/lunches`
    - **Lunch created for current day with selected dishes (dishIds are used). Update Lunch is not permitted. Lunch can be deleted and than created new lunch. 

**_Users resource_**

* Show All
    - `GET /admin`
    - `curl -u admin@gmail.com:admin http://localhost:8080/testapp/admin`
* Create
    - `POST /admin`
    - `curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X POST -d '{"email":"new@mail.ru", "password":"newPassword"}' http://localhost:8080/testapp/admin`
* Update
    - `PUT /admin/{userId}`
    - `curl -u admin@gmail.com:admin -H 'Content-Type: application/json' -X PUT -d '{"email":"updated@mail.ru", "updatedpassword":"newPassword"}' http://localhost:8080/testapp/admin/100000`
* Delete
    - `DELETE /admin/{userId}`
    - `curl -u admin@gmail.com:admin -X DELETE http://localhost:8080/testapp/admin/100001`

**_Lunches resource_**

* Show all lunches with it restaurant and dishes to current day
    - `GET /lunches`
    - `curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches`
* Show selected lunch
    - `GET /lunches/{lunchId}`
    - `curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches/100008`
* Vote to selected lunch (restaurant)
    - `PUT /lunches/{lunchId}`
    - `curl -u user@yandex.ru:user -X PUT http://localhost:8080/testapp/lunches/100008`
* Show restaurant's rating 
    - `GET /lunches/ratingByRestaurant?restaurant={restaurantId}`
    - `curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches/ratingByRestaurant?restaurant=100003`
* Show current lunch rating 
    - `GET /lunches/ratingByLunch?lunch={lunchId}`
    - `curl -u user@yandex.ru:user http://localhost:8080/testapp/lunches/ratingByLunch?lunch=100008`