package data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import model.NewsDetail
import model.NewsItem
import kotlin.random.Random

class NewsRepository {

    private val newsBank = mapOf(
        "Teknologi" to listOf(
            "Peluncuran Chip AI Generasi Baru",
            "Startup Lokal Raih Pendanaan Seri A"
        ),
        "Olahraga" to listOf(
            "Timnas Menang Telak di Laga Tandang",
            "Rekor Baru Dipecahkan di Kejuaraan Dunia"
        ),
        "Politik" to listOf(
            "DPR Bahas RUU Baru Pekan Ini",
            "Menteri Tinjau Proyek Infrastruktur"
        )
    )

    fun newsFlow(): Flow<NewsItem> = flow {
        var idCounter = 0
        val categories = newsBank.keys.toList()

        while (true) {
            delay(2000L)
            idCounter++

            val category = categories.random()
            val title = newsBank.getValue(category).random()

            emit(
                NewsItem(
                    id = idCounter,
                    category = category,
                    title = title,
                    timestamp = System.currentTimeMillis()
                )
            )
        }
    }

    // BARU: suspend function untuk ambil detail berita secara async
    suspend fun fetchNewsDetail(newsItem: NewsItem): NewsDetail {
        delay(500L) // simulasi latency network
        val content = "Isi lengkap berita: ${newsItem.title} (kategori ${newsItem.category})"
        return NewsDetail(newsId = newsItem.id, content = content)
    }
}