package co.edu.uniandes.vinilos.view.album

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Album
import co.edu.uniandes.vinilos.databinding.TemplateItemAlbumBinding

class AlbumAdapter : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    var onAlbumSelected: ((id: Int) -> Unit)? = null

    var data: List<Album> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun onClickAlbum(position: Int) {
        onAlbumSelected?.invoke(data[position].id!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_item_album, parent, false)
        return AlbumHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bind(data[position], position, this)
    }

    override fun getItemCount() = data.size

    class AlbumHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding: TemplateItemAlbumBinding = DataBindingUtil.bind(view)!!

        fun bind(album: Album, position: Int, handler: AlbumAdapter){
            binding.album = album
            binding.position = position
            binding.handler = handler
        }

    }

}