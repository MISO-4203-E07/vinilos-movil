package co.edu.uniandes.vinilos

import co.edu.uniandes.vinilos.view.album.AlbumTest
import co.edu.uniandes.vinilos.view.album.ArtistTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AlbumTest::class,
    ArtistTest::class
)
class MainTestSuite
