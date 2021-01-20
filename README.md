## Shoppiq Online Marketplace
Java EE assignment | JU19 | ITHS
Made in winter 2020/2021 by Emily Inns, Christoffer Clausen and Rickard Forslund.

To access this project please run the Spring Boot application (ShoppiqApplication) and use the web interface. 
By default this is found at localhost:8080/ or localhost:8080/home.

### Running instructions

1: Start docker on your computer.

2: Create a network ``docker network create jms-network``

3: Pull RabbitMQ image ``docker pull rabbitmq:3-management``

4.1: Maneuver to the Shoppiq project source, in a terminal.

4.2: Package the Shoppiq project ``mvn clean package``

4.3: Make a docker image ``docker build -t shoppiq .``

5.1: Maneuver to the Jms-Server project source, in a terminal.

5.1: Package the Jms-Server project ``mvn clean package``

5.2: Make a docker image ``docker build -t jms-receiver .``

6: Run images on the jms-network (make sure to run the rabbitmq image first)

6.1: ``docker container run -d --network jms-network --name rabbitmq -p 15672:15672 -p 5672:5672 rabbitmq:3-management``

6.2: ``docker container run -d -p 8080:8080 --network jms-network --name shoppiq-app shoppiq``

6.3: ``docker container run -d -p 8081:8080 --network jms-network --name receiver-app jms-receiver``

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