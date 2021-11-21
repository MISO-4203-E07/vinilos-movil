package co.edu.uniandes.vinilos

import co.edu.uniandes.vinilos.view.album.AlbumTest
import co.edu.uniandes.vinilos.view.artist.ArtistTest
import co.edu.uniandes.vinilos.view.collector.CollectorTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AlbumTest::class,
    ArtistTest::class,
    CollectorTest::class
)
class MainTestSuite
