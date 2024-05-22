# Antisocial

---

### Technologies
- Jakarta Servlet
- JSP
- JDBC
- PostgreSQL
- JUnit
- Maven
- Tomcat
- Docker

---

### Running the application locally

> This guide is intended for Unix-like operating systems: MacOS and Linux. Sorry Windows guys.

Clone project's codebase and enter the created folder:

```shell
git clone https://github.com/andrewsixsixsix/antisocial.git && cd antisocial
```

> Run the commands below from project's root folder (you should be already in, if the command above was executed
> successfully)

Grant execution permission 1) to the folder containing scripts to start/stop the application and cleanup your machine
from Docker images and containers created to run the application; 2) to the Maven wrapper (required to build the
project).

```shell
sudo chmod u+x ./scripts && sudo chmod u+x ./mvnw
```

To run the application on your machine, execute the following command. It will grant execution permission to the Maven
wrapper (required to build the project) and to the script that starts the application and will execute it immediately.

> ***NOTE:*** To start the application, you need a Docker engine installed and running on your machine

```shell
./scripts/start.sh
```

Verify that Docker container with application is up and running:

```shell
docker ps
```

Now you should be able to see the content under http://localhost:8080/ URL in your browser.

Stop the application:

```shell
./scripts/stop.sh
```

Clean up created Docker image and container:

```shell
./scripts/cleanup.sh
```