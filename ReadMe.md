Java Enterprise Graduation Project 
===============================

### curl samples (application deployed in application context `MyVote`).
> For windows use `Git Bash `

#### get All Users
`curl -s http://localhost:8080/rest/admin/users --user d@mail.ru:a_pas1`

#### get Users 100001
`curl -s http://localhost:8080/rest/admin/users/100001 --user d@mail.ru:a_pas1`

#### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/profile/register`

#### get Profile
`curl -s http://localhost:8080/rest/profile --user test@mail.ru:test-password`

#### get All Restaurants
`curl -s http://localhost:8080/rest/profile/restaurants --user a@mail.ru:pass1`

#### get Restaurant 100003
`curl -s http://localhost:8080/rest/admin/restaurants/100003  --user d@mail.ru:a_pas1`

#### filter History
`curl -s "http://localhost:8080/rest/history/filter?startDate=2020-02-01&endDate=2020-05-31" --user a@mail.ru:pass1`

#### get Restaurant not found
`curl -s -v http://localhost:8080/rest/admin/restaurants/1000 --user d@mail.ru:a_pas1`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100004 --user d@mail.ru:a_pas1`

#### create Restaurant with menu
`curl -s -X POST -d '{"name":"NewRest","menu":[{"name":"fish","cost":100},{"name":"tea","cost":50}]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants --user d@mail.ru:a_pas1`

#### update Restaurant with menu
`curl -s -X PUT -d '{"name":"NewRest2","menu":[{"name":"fish","cost":100},{"name":"tea2","cost":70}]}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants --user d@mail.ru:a_pas1`

#### create Dish
`curl -s -X POST -d '{"name":"fish","cost":55}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/rest/admin/restaurants/100005/dishes --user d@mail.ru:a_pas1`

#### getAll Dishes
`curl -s http://localhost:8080/rest/admin/restaurants/100005/dishes --user d@mail.ru:a_pas1`

#### getOne Dish
`curl -s http://localhost:8080/rest/admin/restaurants/100005/dishes/100015 --user d@mail.ru:a_pas1`

#### update Dish
`curl -s -X PUT -d '{"name":"Updateed","cost":100}' -H 'Content-Type: application/json' http://localhost:8080/rest/admin/restaurants/100005/dishes/100015 --user d@mail.ru:a_pas1`

#### delete Dish
`curl -s -X DELETE http://localhost:8080/rest/admin/restaurants/100005/dishes/100015 --user d@mail.ru:a_pas1`
