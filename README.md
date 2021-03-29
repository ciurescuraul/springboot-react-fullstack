### Jib Command to build Docker local image (fullstack with v1 tag)

> mvn clean install jib:dockerBuild -Dimage=fullstack:v1

### Jib Command to push the image to Docker Hub Repository

> mvn clean install jib:build -Dimage=rehcems/spring-react-fullstack:v1

### Create _jib-push-to-dockerhub_ Maven Profile to wrap the application automatically and push it to Docker Hub

> mvn clean install -P build-frontend -P jib-push-to-dockerhub -D app.image.tag=1.0.1

### Deploy Application on Elastic Bean Stalk (with Load Balancer)

    - upload docker-compose.yaml 

## Database and Spring Data JPA

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