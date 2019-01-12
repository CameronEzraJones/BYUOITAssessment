package jones.cameron.assessment.util

import org.springframework.stereotype.Component
import java.net.URL

@Component
class URLHandler {
    fun getDataFromURL(url: String): String {
        return URL(url)
                .openStream()
                .bufferedReader()
                .use { it.readText() }
    }
}