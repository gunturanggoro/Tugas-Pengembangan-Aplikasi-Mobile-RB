package model

data class NewsItem(
    val id: Int,
    val category: String,
    val title: String,
    val timestamp: Long
)

data class NewsDetail(
    val newsId: Int,
    val content: String
)

data class DisplayNews(
    val original: NewsItem,
    val displayText: String
)

class NewsFetchException(message: String) : Exception(message)