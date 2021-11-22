package co.edu.uniandes.vinilos.view.collector

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import co.edu.uniandes.vinilos.R
import co.edu.uniandes.vinilos.data.model.Collector
import co.edu.uniandes.vinilos.databinding.TemplateItemCollectorBinding

class CollectorAdapter() : RecyclerView.Adapter<CollectorAdapter.CollectorHolder>() {

    var onCollectorSelected: ((id: Int) -> Unit)? = null

    var data: List<Collector> = listOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun onClickCollector(position: Int) {
        onCollectorSelected?.invoke(data[position].id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.template_item_collector, parent, false)
        return CollectorHolder(view)
    }

    override fun onBindViewHolder(holder: CollectorHolder, position: Int) {
        holder.bind(data[position], position, this)
    }

    override fun getItemCount() = data.size

    class CollectorHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding: TemplateItemCollectorBinding = DataBindingUtil.bind(view)!!

        fun bind(collector: Collector, position: Int, handler: CollectorAdapter){
            binding.collector = collector
            binding.position = position
            binding.handler = handler
        }

    }

}