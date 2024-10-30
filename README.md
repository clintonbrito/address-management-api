# Address Management API

This project is the final assessment for the Squadra New Thinkers Bootcamp. It consists of an API for managing addresses, people, and related geographic data, supporting CRUD operations for states, cities, neighborhoods, addresses, and people.

## Requirements

- Java 17
- Maven 3.9.9
- Docker

## Running the project

To run the project, follow these steps:

1. Clone the repository and enter the project directory:

```bash
git clone git@github.com:clintonbrito/address-management-api.git
cd address-management-api
```

2. Now you need to start the Oracle database. To do this, run the following command:

```bash
docker-compose up -d
```

3. Now you can start the application. To do this, run the following command:

```bash
mvn spring-boot:run
```

> [!NOTE]
> The application will be available at `http://localhost:8080`.

## API Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.

## Endpoints

The API has the following endpoints:

- `/uf`: CRUD operations for states
- `/municipio`: CRUD operations for cities
- `/bairro`: CRUD operations for neighborhoods
- `/pessoa`: CRUD operations for people

## Database

The database is an Oracle database. The database schema is created automatically when the application starts. The schema is created by the `schema.sql` file, and the data is inserted by the `data.sql` file.

## Technologies

- Java
- Spring Boot
- Spring Data JPA
- Oracle Database
- Docker
- Maven

## Architecture

The project follows the MVC architecture. The project is divided into the following packages:

- `controller`: Contains the controllers for the API endpoints
- `entity`: Contains the entities for the database
- `repository`: Contains the repositories for the entities
- `service`: Contains the services for the entities
- `exception`: Contains the exceptions for the application
- `dto`: Contains the DTOs for the API

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

- [Clinton Brito](https://github.com/clintonbrito)