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
java -jar target/json_translator-1.0-SNAPSHOT.jar src/main/resources/map_ow_smartstorm.yml src/main/resources/downloadOW.yml
src/main/resources/sendSmartstorm.yml
```

Sample config files are placed at src/main/resources.

## System overview
![System architecture](https://raw.githubusercontent.com/smart-storm/storm-api-monitor/master/readme_media/system.png "System architecture")

The system does the following:
1. Reads the provided donwload.yml config file for an "url" field and builds a GET query string based on the "authentication" field.
2. Downloads the data.
3. Builds data to send as specified in the mapping.yml file.
4. Sends each JSON to the url specified in send.yml.

## Sample mapping.yml config file
The mapping.yml file syntax is specified as follows.
Each top-level field is treated as an object to send, except for "mapping" field. The value read from this key specifies which field from the further specified objects to send should be replaced. The value in those fields specifies the input data key, from which the true value for the mapped field will be taken. A dot "." specifies an embedded object, see the openweathermap example below for deatails.
```
# The field name which will be mapped
mapping: measure_value
# Message objects
temperatura:
    user_id: t178705@mvrht.net
    sensor_id: 5a45771b2c2a5166534a2719
    desc: temperatura
    measure_value: main.temp
wilgotnosc:
    user_id: t178705@mvrht.net
    sensor_id: 5a4577262c2a5166534a271a
    desc: wilgotnosc
    measure_value: main.humidity
```
The following will get the corresponding values from a message of this form:
http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22

## Sample download.yml config file

- url - this field specifies where to send the get request
- authentication - fields specified in this object will be converted to GET request parameters

```
# URL to send the GET request to.
url: http://samples.openweathermap.org/data/2.5/weather
# This fields are intended for authentication, but can be used to add any fields to the request
authentication:
    lat: 35
    lon: 139
    appid : b6907d289e10d714a6e88b30761fae22
```

## Sample send.yml config file
The url field is required and specifies where messages created from the mapping.yml configuration will be sent.
```
# The URL to which the post requests will be issued
url: http://alfa.smartstorm.io/api/v1/measure
```
