### Jib Command to build Docker local image (fullstack with v1 tag)

> mvn clean install jib:dockerBuild -Dimage=fullstack:v1

### Jib Command to push the image to Docker Hub Repository

> mvn clean install jib:build -Dimage=rehcems/spring-react-fullstack:1.0.1

### Create _jib-push-to-dockerhub_ Maven Profile to wrap the application automatically and push it to Docker Hub

> mvn clean install -P build-frontend -P jib-push-to-dockerhub -D app.image.tag=1.0.2

### Deploy Application on Elastic Bean Stalk (with Load Balancer)

* upload docker-compose.yaml 

```
 version: "3.9"
 services:
    backend:
       image: "rehcems/sprinboot-react-fullstack:latest"
       ports:
         - "80:8080"
       restart: "always"
```

## Database and Spring Data JPA

- Edit AWS Security Groups 
    - Modify Inbound rules to allow traffic from my ip, so I can connect directly from IntelliJ or Psql.

> docker run -it --rm postgres:alpine psql -h aakrj4g0c6uj98.cnoo78crq4no.eu-west-2.rds.amazonaws.com -U postgres -d postgres    

> docker run -it --rm postgres:alpine psql -h <host_name> -U <user_name> -d <database_name>

### Create Docker Network

> docker network create db

### Run a Docker Container for Postgres

    Create a folder and mount it "db-data"

- Linux Shell command

> docker run --name db -p 5432:5432 --network=db -v '$PWD:/var/lib/postgresql/data' -e POSTGRES_PASSWORD=password -d postgres:alpine

- PowerShell command

> docker run --name db -p 5432:5432 --network=db -v 'D:\LEARN\spring_react_full_stack\db-data:/var/lib/postgresql/data' -e POSTGRES_PASSWORD=password -d postgres:alpine

### Use PSQL to connect to Postgres Container through our "db" Network

    --rm Automatically remove the container when it exits 
    -it stands for interactive mode
    -h is the host which we want to connect 
    -U is the user who connects
    -v, --volume list                    Bind mount a volume
    -e, --env list                       Set environment variables
    -d, --detach                         Run container in background and print container ID

> docker run -it --rm --network=db postgres:alpine psql -h db -U postgres

### Rest Resources ###

- Get all students - api/v1/students
- Get one student - api/v1/students/{id}
- Add one student - api/v1/students
- Edit one student - api/v1/students/{id}
- Delete one student - api/v1/students/{id}
