package co.edu.uniandes.vinilos.view.album

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.databinding.ActivityAlbumCreateBinding
import co.edu.uniandes.vinilos.viewmodel.AlbumViewModel
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import java.util.*

class AlbumCreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumCreateBinding
    private val albumViewModel: AlbumViewModel by viewModels()
    private val c = Calendar.getInstance()
    private lateinit var datePicker: DatePickerDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Create album"

        albumViewModel.albumCreated.observe(this, { alb ->
            if (alb != null && alb.id != 0) {
                toast("Album called " + binding.etName.text + " was created successfully")
                finish()
            } else {
                toast("Error creating album " + binding.etName.text)
            }
        })

        binding.etReleaseDate.setOnClickListener {
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            datePicker = DatePickerDialog(this, { _, mYear, mMonth, dayOfMonth ->
                binding.etReleaseDate.setText(getString(R.string.activity_album_create_date_pick, dayOfMonth, mMonth + 1, mYear))
            }, year, month, day)
            datePicker.show()
        }

        binding.btnCreate.setOnClickListener {
            if (binding.etName.text.toString() == "" || binding.etCover.text.toString() == "" ||
                binding.etDescription.text.toString() == "" || binding.etReleaseDate.text.toString() == "" ||
                    binding.spGenre.selectedItemId == 0L || binding.spLabel.selectedItemId == 0L) {
                it.snackbar("There are empty fields. Please fill all fields")
            } else {
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
                val releaseDate: Date = try {
                    sdf.parse(binding.etReleaseDate.text.toString())!!
                } catch (e: Exception) {
                    Date()
                }
                val album = Album(null, binding.etName.text.toString(), binding.etCover.text.toString(), releaseDate,
                    binding.etDescription.text.toString(), binding.spGenre.selectedItem.toString(), binding.spLabel.selectedItem.toString(), null, null)
                albumViewModel.insertAlbum(album)
            }
        }

        binding.btnCancel.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}