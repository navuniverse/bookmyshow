# Book My Show

## Implementation

1. Application is developed in Spring Boot 2.0.0 with Java 8 on Spring Tool Suite IDE. Database used is MySQL 5.7.17.

2. You can book movie tickets using the application.

3. Mandatory entities to book a ticket are - user, movie, theater with seats, shows of configured movies in configured theater with seats.

4. Logging is done on console as well as file. Log file can be accessed at `/var/log/bookmyshow.log`.

5. Exception Handling is done using ExceptionInterceptor. 

6. Unit test cases are present for User Operations - User Registration and User Get. Cases for exceptions are also handled.


## Assumptions

For the simplicity of system and time limit, I have made following assumptions while implementing the solution -

1. Single User Model - One user will use at once. No locking implemented for seat selection. 
2. Single Screen Theaters - No multiple screen handling for a theater has been done. However an option is given for future scope.
3. 10 seats for each show are configured - 5 for CLASSIC and 5 for PREMIUM. Seat numbers are kept fixed in all theaters. 
4. No Payment flow used.


## Setup the Application

1. Create a database `bookmyshow` using the sql file `bookmyshow.sql` provided in `src/main/resources`.

2. Open `src/main/resources/application.properties` and change `spring.datasource.username` and `spring.datasource.password` properties as per your MySQL installation.

3. Type `mvn spring-boot:run` from the root directory of the project to run the application.


## Setting up the data

1. Access the application using swagger on `http://localhost:8080/bms/swagger-ui.html`

2. Execute the API `http://localhost:8080/bms/initialize/generate` from swagger inside the heading `data-populator`. This will create a sample user, movie, theater, theater seats, shows, shows seats that will be used in booking ticket.


## Booking a ticket

1. Use the `show-controller` heading in swagger and call the `search` API inside it using `pageNo=1` and `limit=10` to see the available list of shows.

2. Select any show from the above search result and copy its id.

3. Now go to `ticket-controller` in the swagger and  execute the book ticket API using the following request body - 

{"seatType":"CLASSIC","seatsNumbers":["1A"],"showId":1,"userId":1}

`{`
  `"seatType": "CLASSIC",`
  `"seatsNumbers": [`
    `"1A"`
  `],`
  `"showId": 1,`
  `"userId": 1`
`}`

This will book a ticket for you and you will get ticket id along with show details in response.


## Verifying the results from DB

1. Login to your MySQL and go to `bookmyshow` DB

2. `SELECT * FROM bookmyshow.users;` to see all registered users.

3. `SELECT * FROM bookmyshow.movies;` to see all movies.

4. `SELECT * FROM bookmyshow.theaters;` to see all theaters.

5. `SELECT * FROM bookmyshow.theater_seats;` to see all theater's seats.

6. `SELECT * FROM bookmyshow.shows;` to see all shows for movies in theaters.

7. `SELECT * FROM bookmyshow.show_seats;` to see all show's seats by type.

8. `SELECT * FROM bookmyshow.tickets;` to see all booked tickets.


## Other API Details

1. `UserController` -  API to add and get user

2. `MovieController` - API to add and get movie

3. `TheaterController` - API to add and get theater

4. `ShowController` - API to add and search show


## Future Scope

1. Multiple user handling 
2. Seat locking during payment
3. Multiple Screen handling in theater
4. Seat management on the fly
5. Payment Flow
6. Login and User Account Management