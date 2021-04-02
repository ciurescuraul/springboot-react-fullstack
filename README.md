# SpringBoot React Fullstack / Student Management Application Demo
  Application used to manage students (exercise app).

  - Backend used technologies: 
    - Java, SpringBoot, Postgresql, Maven
  - Frontend used technologies:
    - React
  - DevOps :
    - Git, Docker, Jit, AWS Elastic BeanStalk, CI/CD

### CI/CD with GitHub Actions
### GitHub Actions -> This will trigger a CI(build) automated workflow for Pull Request
- Create a folder .github/workflows/ 
  - build.yaml

### GitHub Actions -> This will trigger a CD(deploy) workflow on Merge to Master
- Create a folder .github/workflows/
    - deploy.yaml
    
    Steps:
1. Slack notification (CI/CD Ongoing... + info)
    - create a new app on https://api.slack.com/ and activate incoming webhooks to match the slack channel you want to receive messages to.
    - in github action add a new secret to match the link generated above.
    
2. Checkout code into the runner (build workflow)
   
3. Setup Java
   
4. Generate a build number
   
5. Perform a Docker login
   - in github action add a new secret to match the docker hub password.

6. Maven clean package and include the Jib profile and if success push the docker image that contains our application to DockerHub
   
7. Slack notification (pushed <image_name>:<new_version> to docker hub... + info)
   
8. Update the docker-compose.yaml (by committing to the repository)
   
9. Slack notification (Deployment started... + info)
   
10. Deploy to AWS action will deploy the application into Elastic Beanstalk
    - create a user and grab credentials, in our AWS account, that can perform deployments on Elastic Beanstalk
        - create a group, create a new policy, create a new user to this group
        - add Access key ID and Secret access key to Github Action Settings Secrets

11. Slack notification (Everything was successful + info)
    
12. SUCCESS!, we can access the new version of our application

### Jib Command to build Docker local image (fullstack with v1 tag)

> mvn clean install jib:dockerBuild -Dimage=fullstack:v1

### Jib Command to push the image to Docker Hub Repository

> mvn clean install jib:build -Dimage=rehcems/spring-react-fullstack:1.0.1

### Create _jib-push-to-dockerhub_ Maven Profile to wrap the application automatically and push it to Docker Hub

> mvn clean install -P build-frontend -P jib-push-to-dockerhub -D app.image.tag=1.0.4

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

### Slack Links - #build-spring-react-fullstack
curl -X POST -H 'Content-type: application/json' --data '{"text":"Github, Actions"}' https://hooks.slack.com/services/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