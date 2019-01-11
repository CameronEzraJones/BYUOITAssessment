package jones.cameron.assessment.model

data class MovieDBResult(
        val poster_path: String,
        val adult: Boolean,
        val overview: String,
        val release_date: String,
        val genre_ids: Array<Int>,
        val id: Int,
        val original_title: String,
        val original_language: String,
        val title: String,
        val backdrop_path: String,
        val popularity: Number,
        val vote_count: Int,
        val video: Boolean,
        val vote_average: Number
)