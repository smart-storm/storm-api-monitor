# storm-api-monitor
This aim of this project is to provide a fully configurable bridge between diffrent REST APIs. Designed to be run from the command line, provides means for automation of data collecting, mapping and sending.

## How to run
Provided commands should be executed in top directory.

Building the application:
```bash
mvn package
```
Running:
```bash
java -jar target/json_translator-1.0-SNAPSHOT.jar
```
The program will respond with:
```bash
Required arguments: mapping_yaml download_yaml upload_yaml
```
Downloading sample data from openweathermap.org and sending them to smart-storm:
```bash
java -jar target\json_translator-1.0-SNAPSHOT.jar src\main\resources\map_ow_smartstorm.yml src\main\resources\downloadOW.yml src\main\resources\sendSmartstorm.yml
```

Sample config files are placed at src/main/resources.

## System overview
![System architecture]https://raw.githubusercontent.com/smart-storm/storm-api-monitor/master/readme_media/system.png "System architecture")

The system does the following:
1. Reads the provided donwload.yml config file for an "url" field and builds a GET query string based on the "authentication" field.
2. Downloads the data.
3. Builds data to send as specified in the mapping.yml file.
4. Sends each JSON to the url specified in send.yml.

## Mapping notation
The mapping.yml file syntax is specified as follows.
Each top-level field is treated as an object to send, except for "mapping" field. The value read from this key specifies which field from the further specified objects to send should be replaced. The value in those fields specifies the input data key, from which the true value for the mapped field will be taken. A dot "." specifies an embedded object, see the openweathermap example for deatails.
