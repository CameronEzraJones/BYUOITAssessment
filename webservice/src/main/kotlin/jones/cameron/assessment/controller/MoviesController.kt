package jones.cameron.assessment.controller

import jones.cameron.assessment.exception.SearchException
import jones.cameron.assessment.service.MoviesService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MoviesController(val moviesService: MoviesService) {

    @CrossOrigin("http://localhost:8081")
    @RequestMapping("/movies")
    fun searchMovies(@RequestParam(value = "search", defaultValue = "") search: String): Any {
        if (search.isNullOrEmpty()) {
            throw SearchException("You must provide a search term")
        } else {
            return moviesService.getMovies(search)
        }
    }

    @ExceptionHandler(SearchException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(e: SearchException): String? {
        return e.message
    }

}