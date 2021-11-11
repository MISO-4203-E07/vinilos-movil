package co.edu.uniandes.vinilos

import co.edu.uniandes.vinilos.view.album.AlbumActivityTest
import co.edu.uniandes.vinilos.view.album.DetailAlbumActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AlbumActivityTest::class,
    DetailAlbumActivityTest::class
)
class MainTestSuite