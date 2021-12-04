package co.edu.uniandes.vinilos.view.artist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Performer
import co.edu.uniandes.vinilos.databinding.TemplateItemArtistsBinding

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.ArtistHolder>() {

    var onArtistSelected: ((id: Int) -> Unit)? = null

    var data: List<Performer> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun onClickArtist(position: Int) {
        onArtistSelected?.invoke(data[position].id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_item_artists, parent, false)
        return ArtistHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.bind(data[position], position, this)
    }

    override fun getItemCount() = data.size

    class ArtistHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding: TemplateItemArtistsBinding = DataBindingUtil.bind(view)!!

        fun bind(performer: Performer, position: Int, handler: ArtistAdapter){
            binding.performer = performer
            binding.position = position
            binding.handler = handler
        }

    }

}