package org.d3if3018.mobpro1

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import org.d3if3018.asessment1.JenisPajak
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.databinding.ListJenisPajakBinding

class MainAdapter (private val data : List<JenisPajak>) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: ListJenisPajakBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(jenis: JenisPajak) = with(binding) {
            jenisPajak.text = jenis.jenis
            deskripsi.text = jenis.deskripsi
            imageView.setImageResource(jenis.imageResId)

            root.setOnClickListener {
                val message = root.context.getString(R.string.message, jenis.jenis)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListJenisPajakBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}