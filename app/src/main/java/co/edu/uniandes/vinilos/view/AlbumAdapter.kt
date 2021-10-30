package co.edu.uniandes.vinilos.view

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.databinding.TemplateItemAlbumBinding
import com.squareup.picasso.Picasso

class AlbumAdapter() : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    var data: List<Album> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_item_album, parent, false)
        return AlbumHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class AlbumHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding: TemplateItemAlbumBinding = DataBindingUtil.bind(view)!!

        fun bind(album: Album){
            binding.album = album
            Picasso.get().load(Uri.parse(album.cover)).into(binding.ivAlbum)
        }

    }

}