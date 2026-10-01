import data.NewsRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.DisplayNews
import state.ReadCounterManager

fun main() = runBlocking {
    val repository = NewsRepository()
    val readCounter = ReadCounterManager()
    val kategoriPilihan = "Politik"

    println("Menampilkan berita kategori: $kategoriPilihan\n")

    repository.newsFlow()
        .filter { newsItem -> newsItem.category == kategoriPilihan }
        .map { newsItem ->
            DisplayNews(
                original = newsItem,
                displayText = "[${newsItem.category}] ${newsItem.title}"
            )
        }
        .take(3)
        .collect { displayNews ->
            println(displayNews.displayText)
            readCounter.markAsRead()
            println("   (Total dibaca: ${readCounter.readCount.value})")

            // BARU: ambil detail secara async, tidak memblokir collect berikutnya
            launch {
                val detail = repository.fetchNewsDetail(displayNews.original)
                println("   -> Detail: ${detail.content}")
            }
        }

    println("\nSelesai.")
}