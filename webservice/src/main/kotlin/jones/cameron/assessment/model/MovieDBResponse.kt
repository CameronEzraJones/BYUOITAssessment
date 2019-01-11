package jones.cameron.assessment.model

data class MovieDBResponse(val page: Int, val total_results: Int, val results: Array<MovieDBResult>, val total_pages: Int)