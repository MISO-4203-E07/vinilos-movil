package co.edu.uniandes.vinilos.view.album

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import co.edu.uniandes.vinilos.data.model.Track
import co.edu.uniandes.vinilos.databinding.ActivityAddTrackAlbumBinding
import co.edu.uniandes.vinilos.viewmodel.AlbumViewModel
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class AddTrackAlbumActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTrackAlbumBinding
    private val albumViewModel: AlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTrackAlbumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Add track to album"
        val idAlbum = intent.getIntExtra("ID_ALBUM", 0)

        albumViewModel.trackAdded.observe(this, { song ->
            if (song != null && song.id != 0) {
                toast("Track called " + binding.etNameTrack.text + " was added successfully")
                finish()
            } else {
                toast("Error adding track " + binding.etNameTrack.text)
            }
        })

        binding.btnCreate.setOnClickListener {
            if (binding.etNameTrack.text.toString() == "" || binding.etMinutes.text.toString() == "" ||
                    binding.etSeconds.text.toString() == "0" || binding.etSeconds.text.toString() == "")
                it.snackbar("There are empty fields. Please fill all fields")
            else {
                val track = Track(null, binding.etNameTrack.text.toString(),
                binding.etMinutes.text.toString() + ":" + binding.etSeconds.text.toString(), null)
                albumViewModel.addTrackAlbum(idAlbum.toString(), track)
            }
        }

        binding.btnCancel.setOnClickListener { onBackPressed() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}