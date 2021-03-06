# Jumia Api Exercise

A Java project based on a job interview for Jumia
## Getting Started

### Installing

* Download database sample.db: https://bit.ly/3pqdsA1
* Install Docker: https://www.docker.com
* After running docker, get the project image with the command:
```
docker pull icarorez/api-exercise
```

### Executing program

* After installing docker and downloading the public image, run a container redirecting to your localhost 8080 port:
```
docker run -p 8080:8080 icarorez/api-exercise
```
* Copy downloaded database (sample.db) to container (use "docker ps" command to find container id):
```
docker cp "<your local directory>\sample.db" <container id>:/jumia
//example docker cp "D:\workspace-jumia\app\jumia\sample.db" d1d486b37f1d:/jumia
```

Restart the container and you should be able to test the API with data

## Testing API

### Usage

You can use the follow parameters on "/phones/filter" GET request:
* page = Current page of a pagination
* size = Number of elements per page
* sort = Name of the table field to sort
* country = Filter by the country's phone
* state = Filter by the state of the phone number (valid or not valid)
### cURLs example requests

Return all phones
```
curl localhost:8080/phones/
```
Return the first page of 10 phones list, ordering by state ASC
```
curl localhost:8080/phones/filter?size=10&page=0&sort=state
```
Return the first page of 10 phones, filtering by Uganda country
```
curl localhost:8080/phones/filter?size=10&page=0&country=Uganda
```
Return pages of phones, ordering by state DESC (use "-" for descending order)
```
curl localhost:8080/phones/filter?size=10&sort=-state
```