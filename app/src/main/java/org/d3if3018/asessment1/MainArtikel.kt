package org.d3if3018.asessment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3018.asessment1.databinding.ActivityMainArtikelBinding
import org.d3if3018.asessment1.databinding.ListJenisPajakBinding
import org.d3if3018.mobpro1.MainAdapter

class MainArtikel : AppCompatActivity() {

    private  lateinit var binding: ActivityMainArtikelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = MainAdapter(getData())
            setHasFixedSize(true)
        }


    }

    private fun getData(): List<JenisPajak> {
        return listOf(
            JenisPajak("PBB", "Pajak Bumi Bangunan", R.drawable.ayam),
            JenisPajak("PBB", "Pajak Bumi Bangunan", R.drawable.ayam),
            JenisPajak("PBB", "Pajak Bumi Bangunan", R.drawable.ayam),
            JenisPajak("PBB", "Pajak Bumi Bangunan", R.drawable.ayam),
            JenisPajak("PBB", "Pajak Bumi Bangunan", R.drawable.ayam),
            JenisPajak("PBB", "Pajak Bumi Bangunan", R.drawable.ayam),
        )
    }
}