Multiplication Application:

The application is built using below architectural patterns:

1. RESTful APIs using Java /SpringBoot
2. Event Driven Microservices
3. h2 database
4. RabbitMQ for asynchronous messaging
5. Spring Cloud Consul: Service Discovery and Load Balancing

Application Flow:

1. User logs in to the application.
2. a REST api call is initiated to retrieve 2 factors for the multiplication challenge.
3. user enters a guess (an attempt) for the provided challenge.
4. this attempt is sent as a POST request to one of the microservice (a multiplication service) to verify the guess.
5. multiplication service responds to the UI (user) of the correctness of the attempt made.
6. parallely, the attempt is forwarded as another REST api call (initially) and as an event (with event driven architecture) implemented later.
7. the receiving microservices (game service) assigns a score and a badge for the user attempt and stores it in local DB (h2).
8. UI hits a REST api endpoint exposed by game microservice to show up the LeaderBoard (as seen in the screen shot attached here).

![Multiplication_Application](https://github.com/user-attachments/assets/7d8a4e70-1817-4fe4-898b-d432d1f32b15)
