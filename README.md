
# Running Application

To run this application navigate to CsvUploaderApplication class and click on run

# Testing Application

This application can be tested using postman or similar tool.

The following are the endpoints that can be tested:

- GET http://localhost:8080/medicine/Type 2
- GET http://localhost:8080/medicines
- POST http://localhost:8080/medicines (select form-data and attach csv file to test with)
- DELETE http://localhost:8080/medicines

# View DB

This application uses a h2 in memory db. The h2 console can be accessed when the application is running 
by navigating to this url on the browser:
http://localhost:8080/h2-console