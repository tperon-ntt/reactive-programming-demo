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

1) The [nodejs-client](nodejs-client) read a [csv file](nodejs-client/files/customers.csv) that contains a list of customers data and it make a rest call into message-server for each line of csv.
2) [Message-server](message-server) receive the client request, so it sends the customer data into customer-service and marketing-service.
3) [Customer-service](customer-service) receive the customer data from message-server, so it saves the data into MongoDB.
4) [Marketing-service](marketing-service) receive the customer data from message-server, so it saves the data into Redis DB. 

[nodejs-client](nodejs-client) also can read all saved data from message-server APIs.


### Build & Execute
Please, If you are using a **Windows OS** you should use **gradle.bat** command.

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

- Run NodeJs client for import and get all customers data
```
Realtime import
$ cd nodejs-client && yarn ts-node app.ts --type=import && cd - 

Import customer every 1 second
$ cd nodejs-client && yarn ts-node app.ts --type=import && cd - 

Get customers data from message-server every 1 second
$ cd nodejs-client && yarn ts-node app.ts --type=getWDelay && cd -

Get customers data from message-server 
$ cd nodejs-client && yarn ts-node app.ts --type=get && cd -
```
