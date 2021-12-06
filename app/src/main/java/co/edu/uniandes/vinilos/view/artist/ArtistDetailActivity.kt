package co.edu.uniandes.vinilos.view.artist

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.databinding.ActivityArtistDetailBinding
import co.edu.uniandes.vinilos.viewmodel.ArtistViewModel

class ArtistDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArtistDetailBinding
    private val artistViewModel: ArtistViewModel by viewModels()
    private var idArtist: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_artist_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        idArtist = intent.getIntExtra("ID_ARTIST", 0)
        artistViewModel.performer.observe(this, { performer ->
            binding.performer = performer
            supportActionBar?.title = "Vinilos - ${performer.name}"
            if (performer == null)
                Toast.makeText(this, getString(R.string.activity_detail_artist_artist_not_exists), Toast.LENGTH_SHORT).show()
        })

        artistViewModel.getArtistById(idArtist)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}