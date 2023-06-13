package org.d3if3018.asessment1.ui.Artikel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ComputableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.databinding.ListArtikelBinding
import org.d3if3018.asessment1.model.Artikel
import org.d3if3018.asessment1.network.ArtikelApi

class ArtikelAdapter : RecyclerView.Adapter<ArtikelAdapter.ViewHolder>() {

    private val data = mutableListOf<Artikel>()

    fun updateData(newData: List<Artikel>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListArtikelBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(artikel: Artikel) = with(binding) {
            jenis.text = artikel.jenis
            subJenis.text = artikel.kepanjangan
            Glide.with(gambarPajak.context)
                .load(ArtikelApi.getArtikelUrl(artikel.image))
                .error(R.drawable.baseline_broken_image_24)
                .into(gambarPajak)

            root.setOnClickListener{
                val message = root.context.getString(R.string.message, artikel.kepanjangan)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListArtikelBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}