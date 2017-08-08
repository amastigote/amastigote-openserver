# amastigote-openserver
A fully deployable server for amastigote open version.
## Generate JAR
```
➜ mvn package
```
## Run
- Java 8 or higher version of JRE is required.  
- The embedded SQLite3 database file (amastigote-open-svr.db) will automatically be generated in the first run.  
- If you want to reset all the data, simply delete the database file.
```
➜ java -jar ama-openserver.jar
```
