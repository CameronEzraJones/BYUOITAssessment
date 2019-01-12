package jones.cameron.assessment.service

import com.google.gson.Gson
import jones.cameron.assessment.exception.SearchException
import jones.cameron.assessment.model.Movie
import jones.cameron.assessment.model.MovieDBResponse
import jones.cameron.assessment.util.URLHandler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.net.URL
import java.net.URLEncoder

@Service
class MoviesService {

    @Value("\${API_HOST}")
    lateinit var apiHost: String

    @Value("\${API_PATH}")
    lateinit var apiPath: String

    @Value("\${API_KEY}")
    lateinit var apiKey: String

    @Value("\${IMAGE_URL_BASE}")
    lateinit var imageUrlBase: String

    @Autowired
    lateinit var urlHandler: URLHandler

    public fun getMovies(search: String): List<Movie> {
        println("Get movies for search term $search")
        try {
            val url: String = constructURL(search)
            val response = urlHandler.getDataFromURL(url)
            val data: MovieDBResponse = Gson().fromJson(response, MovieDBResponse::class.java)
            val movieList: MutableList<Movie> = mutableListOf()
            for (i in 0..Math.min(9, data.results.size - 1)) {
                movieList.add(Movie(
                        movie_id = data.results[i].id,
                        title = data.results[i].title,
                        poster_image_url = imageUrlBase + data.results[i].poster_path,
                        popularity_summary = "${data.results[i].popularity} out of ${data.results[i].vote_count}"
                        ))
            }
            return movieList
        } catch (e: Exception) {
            throw SearchException(e.message)
        }
//        val data: Array<Movie> = gson.fromJson(response, Array<Movie>::class.java)
//        return data
    }

    private fun constructURL(search: String): String {
        val sb = StringBuilder()
        sb.append(apiHost)
                .append(apiPath)
                .append("?api_key=")
                .append(apiKey)
                .append("&query=")
                .append(URLEncoder.encode(search, "utf-8"))
                .append("&include_adult=false")
        return sb.toString()
    }
}