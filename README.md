# amastigote-openserver
Deployable server for amastigote open version.
## Generate JAR
```
➜ mvn package
```
## Run
- Java 8 or higher version of JRE is required.  
- The embedded SQLite3 database file (amastigote-open-svr.db) will automatically be generated in the first run.  
- If you want to reset all the data, simply delete the database file.
- Server is running at port 8080 in default.
```
➜ java -jar ama-openserver.jar
```
## Source Structure
```
.
├── Application.java
├── conf
│   ├── DataSourceConfiguration.java
│   └── JpaConfiguration.java
├── data
│   ├── model
│   │   ├── local
│   │   │   ├── Category.java
│   │   │   ├── Item.java
│   │   │   └── Tag.java
│   │   └── remote
│   │       ├── CategoryRequestBody.java
│   │       ├── ItemPageSubResponse.java
│   │       ├── ItemRequestBody.java
│   │       └── Response.java
│   ├── repository
│   │   ├── CategoryRepo.java
│   │   ├── ItemRepo.java
│   │   └── TagRepo.java
│   └── service
│       ├── CategoryServiceImpl.java
│       ├── CategoryService.java
│       ├── ItemServiceImpl.java
│       ├── ItemService.java
│       ├── TagServiceImpl.java
│       └── TagService.java
└── web
    ├── CategoryController.java
    ├── ItemController.java
    └── TagController.java

8 directories, 22 files
```
