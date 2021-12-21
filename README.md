# Jumia Api Exercise

A Java project based on a job interview for Jumia
## Getting Started

### Installing

* Install Docker and run the project Image: https://www.docker.com
* Clone this repository and run with maven: https://maven.apache.org

### Executing program

* After installing docker create a container with the image
```
docker run -p 8080:8080 api-exercise
```


## Testing API

### cURLs requests
Return all customers
```
curl https://localhost:8080/customers/
```
Return the first page of 20 customers ordering by Country ASC
```
curl https://localhost:8080/customers/filter?sort=country&size=20&page=1
```
Return pages of 10 customers ordering by state DESC (use "-" for descending order)
```
curl https://localhost:8080/customers/filter?sort=-country.state&size=10
```
