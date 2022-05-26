# Geolocation REST Service

### Project purpose - Create and test an efficient REST service in spring boot used to store and read geolocation and support user login mechanism

#### How it works

After launching the service we have five methods available:

- POST /login: Used to log in and obtain access token.
- POST /logout: Used to log out user and forget access token.
- POST /home/geolocation: Used to store geolocation in the database. Geolocation must be in format:

{

    "deviceId": "deviceId",

    "latitude": "latitude",

    "longitude": "longitude"

}

Any other data format entered or incorrectly entered data will result in a bad request error.

- GET /home/geolocation: Used to retrieve all geolocations in database.
- GET /home/geolocation/{deviceId}: Use to retrieve geolocation with provided in URI device id.

All requests except login require an access token in the header, otherwise, you will get unauthorized error.

### To simplify data storage, user and geolocation stores were created in the program using the singleton pattern.

### User login mechanism

The implemented simplified login mechanism is based on user authentication by means of a token, which is given to the
user upon correct login. When trying to request without a token or with an invalid token an unauthorized error will be
thrown

### Service components have been tested for incorrect data entry and attempts by unauthenticated users to acquire or enter data