## Shoppiq Online Marketplace
Java EE assignment | JU19 | ITHS
Made in winter 2020/2021 by Emily Inns, Christoffer Clausen and Rickard Forslund.

To access this project please run the Spring Boot application (ShoppiqApplication) and use the web interface. 
By default this is found at localhost:8080/ or localhost:8080/home.

### Run instructions

Run this app, and the secondary jms app at the same time.

Start an instance of RabbitMQ server in docker using:
````
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
````
### Item Endpoints

To create an item, go to `/api/v1/item/create`

To search for items, go to `/api/v1/item/search`

The item search page uses the item endpoints `/search/category`, `/search/category/asc`, `/search/category/desc` and `/search/name`

To view an item, click on it's ID from the list or search screen, or go to `/api/v1/item/view/{id}`

To view all items, go to `/api/v1/item/list` (Requires ADMIN)

### User Endpoints
To create a user, go to `/api/v1/user/create`

To log in, go to `/login` which calls upon UserRepository.findByUsernameAndPassword

To view a user, click on it's name from the list screen, or go to `/api/v1/user/view/{name}`

To view all users, go to `/api/v1/user/list` (Requires ADMIN)

###Security

In accordance to our projects security structure, some webpages and endpoints are reserved for users of the appropriate clearance.

Without logging into an account, you can only access `/home`, `/login` and `/api/v1/user/create` to register and log into an account.

Created accounts are automatically assigned to the AuthGroup USER, giving them access to most of the projects pages and endpoints. Users can
upload and search through items as well as view item and user pages.

Accounts in the AuthGroup ADMIN have access to all pages and endpoints, including the `/admin` console and the list pages `/api/v1/item/list` and `/api/v1/user/list`