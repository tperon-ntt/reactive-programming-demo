# DEMO REACTIVE PROGRAMMING

It's a simple example of reactive servers and client.

### Prerequisites

- OpenJDK 17
- Gradle (7.5.1)
- NodeJs 18.x
- Yarn
- Local MongoDB
- Local Redis

### Project overview

![Overview of the project](Overview.png?raw=true "Overview of the project")

### Build & Execute
Please, If you are using a Windows os you should use **gradle.bat** command.

- Build/Test current project from root of the project
```
$ ./gradlew clean build

$ cd nodejs-client && yarn install && cd -
```

- Run Spring boot servers from root of the project
```
$ ./gradlew customer-service:bootRun 

$ ./gradlew message-server:bootRun    

$ ./gradlew marketing-service:bootRun  
```

- Run NodeJs client for import data 
```
$ cd nodejs-client && yarn ts-node app.ts --type=import && cd -
```

- Run NodeJs client for get all customers data
```
$ cd nodejs-client && yarn ts-node app.ts --type=import && cd -
```
