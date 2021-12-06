package co.edu.uniandes.vinilos.util

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Performer
import co.edu.uniandes.vinilos.data.model.Track
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

const val DATE_TIME_FORMAT_YYYY_MM_DD = "yyyy/MM/dd"

@BindingAdapter("app:imageUrl")
fun setImageByUrl(view: ImageView, url: String?) {
    url?.let {
        Picasso.get()
                .load(Uri.parse(it))
                .error(R.drawable.ic_round_broken_image)
                .into(view)
    }
}

@BindingAdapter("app:dateFormat")
fun setDateText(view: TextView, date: Date?) {
    val sdf = SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM_DD, Locale.getDefault())
    date?.let {
        view.text = sdf.format(it)
    }
}

@BindingAdapter("app:performersFromList")
fun setPerformers(view: TextView, list: List<Performer>?) {
    list?.let { performers ->
        var artist = ""
        performers.forEach {
            artist += it.name + "\n"
        }
        if (artist.isNotEmpty())
            view.text = artist.substring(0, artist.length - 1)
        else
            view.text = view.context.getString(R.string.activity_detail_album_no_info_performers)
    }
}

@BindingAdapter("app:tracksFromList")
fun setTracks(view: TextView, list: List<Track>?) {
    list?.let { tracks ->
        var songs = ""
        tracks.forEach {
            songs += "- " + it.name + ". " + it.duration + "\n"
        }
        if (songs.isNotEmpty())
            view.text = songs.substring(0, songs.length - 1)
        else
            view.text = view.context.getString(R.string.activity_detail_album_no_info_tracks)
    }
}