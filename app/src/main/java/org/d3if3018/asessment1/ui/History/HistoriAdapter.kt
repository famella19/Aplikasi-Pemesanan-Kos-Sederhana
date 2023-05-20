package org.d3if3018.asessment1.ui.History

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewParent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.databinding.ListHistoriBinding
import org.d3if3018.asessment1.db.PajakEntity
import org.d3if3018.asessment1.model.hitungPajak
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter : ListAdapter <PajakEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK){

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PajakEntity>() {
            override fun areItemsTheSame(oldData: PajakEntity, newData: PajakEntity): Boolean {
                return oldData.id == newData.id
            }

            override fun areContentsTheSame(oldData: PajakEntity, newData: PajakEntity): Boolean {
                return oldData == newData
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind (item: PajakEntity) = with(binding){
            val hasilPajak = item.hitungPajak()
            njkp.text = hasilPajak.njkp.toString()
            pajak.text = hasilPajak.pajak.toString()
            val colorRes = R.color.pink_700

            val circleBg = tanda.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context,colorRes))

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            njkp.text = root.context.getString(R.string.hasil_x, hasilPajak.njkp)
            pajak.text = root.context.getString(R.string.pajak_x, hasilPajak.pajak)
        }
    }
}
