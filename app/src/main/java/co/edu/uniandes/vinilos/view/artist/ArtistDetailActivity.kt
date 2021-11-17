package co.edu.uniandes.vinilos.view.artist

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.databinding.ActivityArtistDetailBinding
import co.edu.uniandes.vinilos.viewmodel.ArtistViewModel

class ArtistDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistDetailBinding
    private val artistViewModel: ArtistViewModel by viewModels()
    var idArtist: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idArtist = intent.getIntExtra("ID_ARTIST", 0)
        artistViewModel.performer.observe(this, Observer { performer ->
            binding.performer = performer
            supportActionBar?.title = "Vinilos - ${performer.name}"
            if (performer == null)
                Toast.makeText(this, getString(R.string.activity_detail_album_album_not_exists), Toast.LENGTH_SHORT).show()
        })

        artistViewModel.getArtistById(idArtist)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}