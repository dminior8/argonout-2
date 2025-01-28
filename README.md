# Argonout 2

**Argonout 2** will be a platform based on microservices architecture, enabling the organization and participation in outdoor exploration games inspired by Geocaching. The system will be divided into independent microservices that will communicate with each other via REST API and asynchronous queuing mechanisms (e.g., Kafka or RabbitMQ). The backend will be implemented using Spring Boot, while the frontend will be built with React (for the web version) and React Native (for the mobile application).

## Table of Contents

1. [Architecture](#architecture)
2. [Development Roadmap](#development-roadmap)
3. [License](#license)



## Planned Architecture

1. **User Service** – User management and authentication
   - User registration and login (JWT)
   - Profile management
   - Handling permissions and roles

2. **Game Service** – Game logic and gameplay mechanisms
   - Creating and managing games
   - Tracking user progress
   - Game result validation and completion mechanisms

3. **Location Service** – Managing locations and routes in games
   - Storing map points
   - Map and GPS integration
   - QR code support

4. **Leaderboard Service** – Ranking and statistics system
   - Generating rankings for users
   - History of achievements and results
   - Supporting competitions within leagues

5. **Messaging Service** – Messaging and notifications system
   - Sending game progress notifications
   - Handling messages between users and administrators

6. **Gateway API** – Central communication gateway
   - Routing queries between microservices
   - Authentication and access control
   - Rate limiting and traffic monitoring

7. **Event Service** – Asynchronous communication
   - Event queuing for game actions (e.g., scoring a point, completing a stage)
   - Real-time notifications handling

8. **Admin Panel Service** – Administrative interface
   - Managing users and games
   - Moderating content and statistics


## Development Roadmap

The entire system will be deployed in Docker containers, with data stored in PostgreSQL and Redis (for caching). In future phases, integration with cloud is planned for increased scalability.


## License

This project is licensed under the [MIT License](https://github.com/dminior8/argonout-2/blob/main/LICENSE).


## Author and Creation Date

- **Author**: Daniel Minior
- **Creation Date**: 28 January 2025
