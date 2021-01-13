## Shoppiq Online Marketplace
Java EE assignment | JU19 | ITHS
Made in winter 2020/2021 by Emily Inns, Christoffer and Rickard.

To access this project please run the Spring Boot application (ShoppiqApplication) and use the web interface. 
By default this is found at localhost:8080/ or localhost:8080/home.

### Item Endpoints
To view all items, go to `/api/v1/item/list`

To create an item, go to `/api/v1/item/create`

To search for items, go to `/api/v1/item/search`

The item search page uses the item endpoints `/search/category`, `/search/category/asc`, `/search/category/desc` and `/search/name`

To view an item, click on it's ID from the list or search screen, or go to `/api/v1/item/view/{id}`

### User Endpoints
To create a user, go to `/api/v1/user/create`

To log in, go to `/login` which calls upon UserRepository.findByUsernameAndPassword

To view a user, click on it's name from the list screen, or go to `/api/v1/user/view/{name}`