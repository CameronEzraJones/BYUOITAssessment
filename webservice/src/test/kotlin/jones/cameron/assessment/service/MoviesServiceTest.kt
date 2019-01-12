package jones.cameron.assessment.service

import com.google.gson.Gson
import jones.cameron.assessment.model.Movie
import jones.cameron.assessment.util.URLHandler
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.MockitoAnnotations.initMocks
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets

@ExtendWith(SpringExtension::class)
@SpringBootTest
@TestPropertySource(locations = ["classpath:application-test.properties"])
class MoviesServiceTest {

    @Autowired
    @InjectMocks
    lateinit var moviesService: MoviesService

    @Mock
    lateinit var mockUrlHandler: URLHandler

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        println("setUp called with movie service ${moviesService.toString()}")
    }

    @AfterEach
    fun tearDown() {
        println("tearDown called with movie service ${moviesService.toString()}")
    }

    @Test
    fun simpleSearch() {
        val mockResponse: String = "{\n" +
                "  \"page\": 1,\n" +
                "  \"total_results\": 46,\n" +
                "  \"total_pages\": 3,\n" +
                "  \"results\": [\n" +
                "    {\n" +
                "      \"vote_count\": 17556,\n" +
                "      \"id\": 24428,\n" +
                "      \"video\": false,\n" +
                "      \"vote_average\": 7.6,\n" +
                "      \"title\": \"The Avengers\",\n" +
                "      \"popularity\": 48.77,\n" +
                "      \"poster_path\": \"\\/cezWGskPY5x7GaglTTRN4Fugfb8.jpg\",\n" +
                "      \"original_language\": \"en\",\n" +
                "      \"original_title\": \"The Avengers\",\n" +
                "      \"genre_ids\": [\n" +
                "        878,\n" +
                "        28,\n" +
                "        12\n" +
                "      ],\n" +
                "      \"backdrop_path\": \"\\/hbn46fQaRmlpBuUrEiFqv0GDL6Y.jpg\",\n" +
                "      \"adult\": false,\n" +
                "      \"overview\": \"When an unexpected enemy emerges and threatens global safety and security, Nick Fury, director of the international peacekeeping agency known as S.H.I.E.L.D., finds himself in need of a team to pull the world back from the brink of disaster. Spanning the globe, a daring recruitment effort begins!\",\n" +
                "      \"release_date\": \"2012-04-25\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"vote_count\": 10665,\n" +
                "      \"id\": 299536,\n" +
                "      \"video\": false,\n" +
                "      \"vote_average\": 8.3,\n" +
                "      \"title\": \"Avengers: Infinity War\",\n" +
                "      \"popularity\": 114.819,\n" +
                "      \"poster_path\": \"\\/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg\",\n" +
                "      \"original_language\": \"en\",\n" +
                "      \"original_title\": \"Avengers: Infinity War\",\n" +
                "      \"genre_ids\": [\n" +
                "        12,\n" +
                "        28,\n" +
                "        14\n" +
                "      ],\n" +
                "      \"backdrop_path\": \"\\/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg\",\n" +
                "      \"adult\": false,\n" +
                "      \"overview\": \"As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.\",\n" +
                "      \"release_date\": \"2018-04-25\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"vote_count\": 0,\n" +
                "      \"id\": 299534,\n" +
                "      \"video\": false,\n" +
                "      \"vote_average\": 0,\n" +
                "      \"title\": \"Avengers: Endgame\",\n" +
                "      \"popularity\": 28.254,\n" +
                "      \"poster_path\": \"\\/jWhqOBWUpZBq5MC8y34KNv7Avv3.jpg\",\n" +
                "      \"original_language\": \"en\",\n" +
                "      \"original_title\": \"Avengers: Endgame\",\n" +
                "      \"genre_ids\": [\n" +
                "        12,\n" +
                "        878,\n" +
                "        28\n" +
                "      ],\n" +
                "      \"backdrop_path\": \"\\/48bEcwPgFZzNl32CGhOv328keZl.jpg\",\n" +
                "      \"adult\": false,\n" +
                "      \"overview\": \"A culmination of 22 interconnected films and the finale of this epic journey across the length and breadth of the Marvel Cinematic Universe.  Our beloved heroes will truly understand how fragile this reality is and the sacrifices that must be made to uphold it.\",\n" +
                "      \"release_date\": \"2019-04-24\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"vote_count\": 11708,\n" +
                "      \"id\": 99861,\n" +
                "      \"video\": false,\n" +
                "      \"vote_average\": 7.3,\n" +
                "      \"title\": \"Avengers: Age of Ultron\",\n" +
                "      \"popularity\": 47.679,\n" +
                "      \"poster_path\": \"\\/t90Y3G8UGQp0f0DrP60wRu9gfrH.jpg\",\n" +
                "      \"original_language\": \"en\",\n" +
                "      \"original_title\": \"Avengers: Age of Ultron\",\n" +
                "      \"genre_ids\": [\n" +
                "        28,\n" +
                "        12,\n" +
                "        878\n" +
                "      ],\n" +
                "      \"backdrop_path\": \"\\/rFtsE7Lhlc2jRWF7SRAU0fvrveQ.jpg\",\n" +
                "      \"adult\": false,\n" +
                "      \"overview\": \"When Tony Stark tries to jumpstart a dormant peacekeeping program, things go awry and Earth’s Mightiest Heroes are put to the ultimate test as the fate of the planet hangs in the balance. As the villainous Ultron emerges, it is up to The Avengers to stop him from enacting his terrible plans, and soon uneasy alliances and unexpected action pave the way for an epic and unique global adventure.\",\n" +
                "      \"release_date\": \"2015-04-22\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        Mockito.`when`(mockUrlHandler.getDataFromURL("https://api.themoviedb.org/3/search/movie?api_key=2b112fba5c66d32308e46887ddf34520&query=Avengers&include_adult=false")).thenReturn(mockResponse)
        val movies: List<Movie> = moviesService.getMovies("Avengers")
        assert(movies.size == 4)
    }
}