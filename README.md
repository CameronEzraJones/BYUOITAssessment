# BYUOITAssessment
Assessment of Vue.js front-end and Kotlin back-end skills
![](Screencast-from-01-12-2019-07_04_02-PM.gif)

## Instructions
Two bash scripts are provided to run the webapp and webservice. They require a Linux environment to run.

1. Change directory into the root of the project
2. To run the webservice, run `./webservice-startup.sh`
3. To run the webapp, run `./webapp-startup.sh`. NOTE: both webapp and webservice have a default port of 8080. However, the webapp is able to change to a different port if it detects that port 8080 is being used. Therefore, you need to run `./webservice-startup.sh` before `./webapp-startup.sh`

## About
### Webservice
The webservice uses a Kotlin backend created with the Spring Boot starter. I used Gradle to handle dependency installation and running the project. I used the java.net.URL class to consume the Movie DB API.

### Webapp
The webapp uses a Vue SPA created with the Vue CLI. I used the Burma CSS Framework to provide styling to the application. I used Vuex to handle the state of the application

## Requirements
### backend
  - [x] Must be written in Kotlin (a simple but powerful JVM based programming language)
  - [x] Single endpoint must adhere to the following API spec:
```
Request: GET ..../movies?search={title}
Response:
[
  {
    "movie_id": integer,
    "title": string,
    "poster_image_url": string, (https://developers.themoviedb.org/3/getting-started/images)
    "popularity_summary": string, (i.e. "{popularity} out of {vote_count}")
  },
... (limit of 10)
]
```
  - [x] Must call the search movies endpoint in TMDB API
    - [x] Use the response from TMDB API to construct the above response (i.e. set the "movie_id" and "title" fields to the same fields returned in TMDB API response, use the "poster_image_url" from TMDB API response to construct a FQDN URL to return in your back-end, etc.)

### frontend
  - [x] Must use Vue.js
  - [x] Must have a search input field and a search button that calls your Kotlin web service
  - [x] Must display the search results for each movie returned from your web service:
    - [x] Title
    - [x] Image of the poster
    - [x] Popularity summary

## Future Work
  - [ ] Search TV shows
  - [ ] Indicate genre of movie
