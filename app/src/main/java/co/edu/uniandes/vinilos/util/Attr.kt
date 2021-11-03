package co.edu.uniandes.vinilos.util

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun setImageByUrl(view: ImageView, url: String) {
    Picasso.get()
        .load(Uri.parse(url))
        .into(view)
}