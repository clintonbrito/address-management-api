# Address Management API

This project is the final assessment for the Squadra New Thinkers Bootcamp. It consists of an API for managing addresses, people, and related geographic data, supporting CRUD operations for states, cities, neighborhoods, addresses, and people.

## Requirements

- Java 21
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
- Lombok
- MapStruct

## Architecture

The project follows the MVC architecture. The project is divided into the following packages:

Each entity has its own package with the following structure:

- `controller`: Contains the controllers for the API endpoints
- `dto`: Contains the DTOs for the API
- `mapper`: Contains the mappers for the DTOs
- `model`: Contains the entities for the application
- `repository`: Contains the repositories for the entities
- `service`: Contains the services for the entities
- `util`: Contains utility classes
- `validator`: Contains the validator classes

Besides the entity packages, the project has the following packages:

- `common`: Contains common classes for the application
- `common.exception`: Contains the exception classes for the application
- `GlobalExceptionHandler` file: Contains the global exception handler for the application

Besides the packages, the project has the following files:
- `application.properties`: Contains the application properties
- `criar_usuario_c##java_com_suas_permissões.sql`: Contains the SQL script to create the user and grant the necessary permissions
- `script_sql_criar_tabelas.sql`: Contains the SQL script to create the tables
- `docker-compose.yml`: Contains the Docker Compose file to start the Oracle database

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

- [Clinton Brito](https://github.com/clintonbrito)