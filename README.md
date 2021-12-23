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
Return all phones
```
curl https://localhost:8080/phones/
```
Return the first page of 10 phones list ordering by state ASC
```
curl https://localhost:8080/phones/filter?size=10&page=0&sort=state
```
Return the first paget of 10 phones filtering by Uganda country
```
curl https://localhost:8080/phones/filter?size=10&page=0&country=Uganda
```
Return pages of phones ordering by state DESC (use "-" for descending order)
```
curl https://localhost:8080/phones/filter?size=10&sort=-state
```