package co.edu.uniandes.vinilos.data.model

data class Collector(
    val id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val favoritePerformers: List<Performer>,
    val collectorAlbums: List<CollectorAlbum>
)

data class CollectorAlbum(
    val id: Int,
    val price: Int,
    val status: String,
    val album: Album,
)