package co.edu.uniandes.vinilos.view.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.databinding.ActivityDetailAlbumBinding

class DetailAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailAlbumBinding
    var idAlbum: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_album)

        idAlbum = intent.getIntExtra("ID_ALBUM", 0)
        Log.i("ID_ALBUM", idAlbum.toString())
    }
}